package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.util.context.ReactorContextUtils;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import com.github.jinahya.epost.openapi.proxy.web.reactive.function.client.WebClientUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Optional;
import java.util.function.Consumer;

@Tag(name = __DownloadAreaCodeServiceApiConstants.TAG)
@RestController
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
@SuppressWarnings({
        "java:S101" // class _Download
})
class _DownloadAreaCodeServiceApiController
        extends _ApiController {

    // -----------------------------------------------------------------------------------------------------------------
    private Mono<AreaCodeInfoResponse> exchange(final String dwldSe) {
        return AreaCodeInfoRequest.of(dwldSe)
                .exchange(webClient());
    }

    private Publisher<? extends AreaCodeInfo> getAreaCodeInfoPublisher(final String dwldSe) {
        return exchange(dwldSe)
                .map(r -> AreaCodeInfo.newInstance(dwldSe, r.getFile()))
                .flatMap(v -> ReactorContextUtils.getUriComponentsBuilderFromRequestBaseUrl()
                        .map(b -> b.path(__DownloadAreaCodeServiceApiConstants.REQUEST_URI_DWLD_SE))
                        .map(b -> {
                            v.add(
                                    Link.of(b.cloneBuilder().build(dwldSe).toString())
                                            .withSelfRel()
                            );
                            v.add(
                                    Link.of(b.cloneBuilder()
                                                    .pathSegment(
                                                            __DownloadAreaCodeServiceApiConstants.PATH_SEGMENT_CONTENT)
                                                    .build(dwldSe)
                                                    .toString())
                                            .withRel(__DownloadAreaCodeServiceApiConstants.REL_CONTENT)
                            );
                            return v;
                        }))
                ;
    }

    @GetMapping(
            path = {
                    __DownloadAreaCodeServiceApiConstants.REQUEST_URI_DWLD_SE
            },
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<AreaCodeInfo> readAreaCodeInfo(
            final ServerWebExchange exchange,
            @PathVariable(__DownloadAreaCodeServiceApiConstants.PATH_NAME_DWLD_SE) final String dwldSe) {
        return Mono.fromDirect(getAreaCodeInfoPublisher(dwldSe));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Flux<DataBuffer> getFileDataPublisher(final String dwldSe,
                                                  final Consumer<? super String> consumer) {
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

//    // https://www.baeldung.com/spring-reactive-read-flux-into-inputstream
//    // https://manhtai.github.io/posts/flux-databuffer-to-inputstream/
//    private InputStream getFileDataStream(final String dwldSe, final Consumer<? super String> consumer)
//            throws IOException {
//        final var output = new PipedOutputStream();
//        final var input = new PipedInputStream(output);
//        DataBufferUtils
//                .write(getFileDataPublisher(dwldSe, consumer), output)
//                .subscribeOn(Schedulers.boundedElastic())
//                .doOnComplete(() -> {
//                    try {
//                        output.close();
//                    } catch (final IOException ioe) {
//                        throw new RuntimeException("failed to close " + output, ioe);
//                    }
//                })
//                .subscribe(DataBufferUtils.releaseConsumer());
//        return input;
//    }
//
//    private Flux<DataBuffer> getFileDataPublisher(final ServerWebExchange exchange, final String dwldSe,
//                                                  final UnaryOperator<String> operator) {
//        return exchange(dwldSe)
//                .map(AreaCodeInfoResponse::getFile)
//                .flatMapMany(f -> {
//                    final String filename;
//                    {
//                        final var uri = URI.create(f);
//                        final var path = FileSystems.getDefault().getPath(uri.getPath());
//                        filename = path.getFileName().toString();
//                    }
//                    exchange.getResponse().beforeCommit(() -> {
//                        exchange.getResponse().getHeaders().setContentDisposition(
//                                ContentDisposition.attachment().filename(operator.apply(filename)).build()
//                        );
//                        return Mono.empty();
//                    });
//                    return WebClientUtils.retrieveBodyToFlux(f, DataBuffer.class);
//                });
//    }

    @GetMapping(
            path = {
                    __DownloadAreaCodeServiceApiConstants.REQUEST_URI_CONTENT
            },
            produces = {
                    MediaType.APPLICATION_OCTET_STREAM_VALUE
            }
    )
    Flux<DataBuffer> readFileContent(
            final ServerWebExchange exchange,
            @PathVariable(__DownloadAreaCodeServiceApiConstants.PATH_NAME_DWLD_SE) final String dwldSe,
            @RequestParam(value = __DownloadAreaCodeServiceApiConstants.PARAM_ATTACHMENT, required = false)
            final Boolean attachment,
            @RequestParam(value = __DownloadAreaCodeServiceApiConstants.PARAM_FILENAME, required = false)
            final String filename) {
        return getFileDataPublisher(
                dwldSe,
                f -> {
                    // attachment true 이고 filename 혹은 f 가 present 할 경우,
                    // Content-Disposition: attachment; filename="v" 헤더를 붙인다.
                    Optional.ofNullable(attachment)
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
                                        r.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + x);
                                    }
                                });
                            });
                }
        );
    }

    @GetMapping(
            path = {
                    __DownloadAreaCodeServiceApiConstants.REQUEST_URI_DOWNLOAD
            },
            produces = {
                    MediaType.APPLICATION_OCTET_STREAM_VALUE
            }
    )
    Flux<DataBuffer> downloadFile(
            final ServerWebExchange exchange,
            @PathVariable(__DownloadAreaCodeServiceApiConstants.PATH_NAME_DWLD_SE) final String dwldSe,
            @RequestParam(value = __DownloadAreaCodeServiceApiConstants.PARAM_FILENAME, required = false)
            final String filename) {
        return getFileDataPublisher(
                dwldSe,
                f -> Optional.ofNullable(filename)
                        .map(String::strip)
                        .filter(v -> !v.isBlank())
                        .or(() -> Optional.ofNullable(f))
                        .map(v -> URLEncoder.encode(v, StandardCharsets.UTF_8))
                        .ifPresent(v -> {
                            beforeCommit(exchange.getResponse(), r -> {
                                r.getHeaders().setContentDisposition(
                                        ContentDisposition.attachment().filename(v).build()
                                );
                            });
                        })
        );
    }
}
