package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import com.github.jinahya.epost.openapi.proxy.web.reactive.function.client.WebClientUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.file.FileSystems;

@Tag(name = __DownloadAreaCodeServiceApiConstants.TAG)
@RestController
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class _DownloadAreaCodeServiceApiController
        extends _ApiController {

    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping(
            path = {
                    __DownloadAreaCodeServiceApiConstants.REQUEST_URI_DWLD_SE
            },
            produces = {
                    MediaType.APPLICATION_OCTET_STREAM_VALUE
            }
    )
    Mono<Void> readAreaCodeInfo(
            final ServerWebExchange exchange,
            @PathVariable(__DownloadAreaCodeServiceApiConstants.PATH_NAME_DWLD_SE) final int dwldse) {
        return exchange.getResponse().writeWith(
                AreaCodeInfoRequest.of(dwldse)
                        .exchange(webClient())
                        .map(AreaCodeInfoResponse::getFile)
                        .flatMapMany(f -> {
                            final String filename;
                            {
                                final var uri = URI.create(f);
                                final var path = FileSystems.getDefault().getPath(uri.getPath());
                                filename = path.getFileName().toString();
                            }
                            exchange.getResponse().beforeCommit(() -> {
                                exchange.getResponse().getHeaders().setContentDisposition(
                                        ContentDisposition.attachment().filename(filename).build()
                                );
                                return Mono.empty();
                            });
                            return WebClientUtils.retrieveBodyToFlux(f, DataBuffer.class);
                        })
        );
    }
}
