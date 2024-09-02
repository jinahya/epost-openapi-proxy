package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiService;
import com.github.jinahya.epost.openapi.proxy.web.reactive.function.client.WebClientUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class ApiService
        extends _ApiService {

    // -----------------------------------------------------------------------------------------------------------------
    private Mono<AreaCodeInfoResponse> exchange(final String dwldSe) {
        return exchange(AreaCodeInfoRequest.of(dwldSe));
    }

    // -----------------------------------------------------------------------------------------------------------------
    Flux<AreaCodeInfoResponse> readAreaCodeInfo(final ServerWebExchange exchange) {
        return Flux.fromArray(AreaCodeInfoRequest.DwldSe.values())
                .map(AreaCodeInfoRequest.DwldSe::value)
                .flatMapSequential(this::exchange, 2);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Mono<AreaCodeInfoResponse> readAreaCodeInfo(
            final ServerWebExchange exchange, final AreaCodeInfoRequest.DwldSe dwldSe) {
        return exchange(AreaCodeInfoRequest.of(dwldSe.value()))
                .map(r -> r.cmmMsgHeader(null));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Flux<DataBuffer> getFileContentPublisher(final String dwldSe, final Consumer<? super String> consumer) {
        return exchange(dwldSe)
                .map(AreaCodeInfoResponse::getFile)
                .flatMapMany(f -> {
                    final String filename;
                    {
                        final var uri = URI.create(f);
                        final var path = FileSystems.getDefault().getPath(uri.getPath());
                        filename = path.getFileName().toString();
                    }
                    consumer.accept(filename);
                    return WebClientUtils.retrieveBodyToFlux(f, DataBuffer.class);
                });
    }

    @GetMapping(
            path = {
                    _ApiConstants.REQUEST_URI_FILE_CONTENT
            },
            produces = {
                    MediaType.APPLICATION_OCTET_STREAM_VALUE
            }
    )
    Flux<DataBuffer> readAreaCodeInfoFileContent(
            final ServerWebExchange exchange,
            @PathVariable(_ApiConstants.PATH_NAME_DWLD_SE) final String dwldSe,
            @RequestParam(value = _ApiConstants.PARAM_ATTACH, required = false) final Boolean attach,
            @RequestParam(value = _ApiConstants.PARAM_FILENAME, required = false) final String filename) {
        return getFileContentPublisher(
                dwldSe,
                f -> {
                    // attach 가 true 이고 filename 혹은 f 가 present 할 경우,
                    // Content-Disposition: attach; filename="v" 헤더를 붙인다.
                    Optional.ofNullable(attach)
                            .filter(Boolean::booleanValue)
                            .flatMap(a -> Optional.ofNullable(filename)
                                             .map(String::strip)
                                             .filter(v -> !v.isBlank())
                                             .or(() -> Optional.ofNullable(f))
//                                    .map(v -> URLEncoder.encode(v, StandardCharsets.UTF_8))
                            )
                            .ifPresent(v -> {
                                beforeCommit(exchange.getResponse(), r -> {
                                    // https://stackoverflow.com/a/20933751/330457
                                    // https://stackoverflow.com/q/93551/330457
                                    final var w = URLEncoder.encode(v, StandardCharsets.UTF_8);
                                    if (w.equals(v)) {
                                        r.getHeaders().setContentDisposition(
                                                ContentDisposition.attachment().filename(v).build()
                                        );
                                    } else {
                                        final var x = "filename: " + dwldSe + ".zip; filename*=utf-8''" + w;
                                        r.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attach; " + x);
                                    }
                                });
                            });
                }
        );
    }
}
