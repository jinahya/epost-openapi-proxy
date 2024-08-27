package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.util.context.ReactorContextUtils;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import com.github.jinahya.epost.openapi.proxy.web.bind.__WebBindMediaTypes;
import com.github.jinahya.epost.openapi.proxy.web.reactive.function.client.WebClientUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileSystems;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.springframework.core.io.buffer.DataBufferUtils.releaseConsumer;

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
                                                            __DownloadAreaCodeServiceApiConstants.PATH_SEGMENT_FILE)
                                                    .build(dwldSe)
                                                    .toString())
                                            .withRel(__DownloadAreaCodeServiceApiConstants.REL_FILE)
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
    private Flux<DataBuffer> getFileDataPublisher(final ServerWebExchange exchange, final String dwldSe,
                                                  final UnaryOperator<String> operator) {
        return exchange(dwldSe)
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
                                ContentDisposition.attachment().filename(operator.apply(filename)).build()
                        );
                        return Mono.empty();
                    });
                    return WebClientUtils.retrieveBodyToFlux(f, DataBuffer.class);
                });
    }

    // https://manhtai.github.io/posts/flux-databuffer-to-inputstream/
    private <R> Mono<R> applyFileData(final ServerWebExchange exchange, final String dwldSe,
                                      final UnaryOperator<String> operator,
                                      final Function<? super ReadableByteChannel, ? extends R> function) {
        return Mono.using(
                Pipe::open,
                p -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> function.apply(p.source())))
                        .doFirst(() -> DataBufferUtils
                                .write(getFileDataPublisher(exchange, dwldSe, operator), p.sink())
                                .subscribeOn(Schedulers.boundedElastic())
                                .doFinally(s -> {
                                    try {
                                        p.sink().close();
                                    } catch (final IOException ioe) {
                                        log.error("failed to close pipe.sink", ioe);
                                        throw new RuntimeException(ioe);
                                    }
                                })
                                .subscribe(releaseConsumer())),
                p -> {
                    log.debug("p.source.open: {}", p.source().isOpen());
                    try {
                        p.source().close();
                    } catch (final IOException ioe) {
                        log.error("failed to close pipe.source", ioe);
                        throw new RuntimeException(ioe);
                    }
                });
    }

    @GetMapping(
            path = {
                    __DownloadAreaCodeServiceApiConstants.REQUEST_URI_FILE
            },
            produces = {
                    MediaType.APPLICATION_OCTET_STREAM_VALUE
            }
    )
    Flux<DataBuffer> downloadFile(
            final ServerWebExchange exchange,
            @PathVariable(__DownloadAreaCodeServiceApiConstants.PATH_NAME_DWLD_SE) final String dwldSe) {
        return getFileDataPublisher(
                exchange,
                dwldSe,
                UnaryOperator.identity()
        );
    }

    @GetMapping(
            path = {
                    __DownloadAreaCodeServiceApiConstants.REQUEST_URI_FILE
            },
            produces = {
                    __WebBindMediaTypes.APPLICATION_VND_OASIS_OPENDOCUMENT_SPREADSHEET_VALUE
            }
    )
    Flux<DataBuffer> downloadFileOds(
            final ServerWebExchange exchange,
            @PathVariable(__DownloadAreaCodeServiceApiConstants.PATH_NAME_DWLD_SE) final String dwldSe) {
        return getFileDataPublisher(
                exchange,
                dwldSe,
                f -> f + '.' + __WebBindMediaTypes.FILE_TYPE_ODS
        );
    }

    @GetMapping(
            path = {
                    __DownloadAreaCodeServiceApiConstants.REQUEST_URI_FILE
            },
            produces = {
                    __WebBindMediaTypes.APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_SPREADSHEET_SHEET_VALUE
            }
    )
    Flux<DataBuffer> downloadFileXlsx(
            final ServerWebExchange exchange,
            @PathVariable(__DownloadAreaCodeServiceApiConstants.PATH_NAME_DWLD_SE) final String dwldSe) {
        return getFileDataPublisher(
                exchange,
                dwldSe,
                f -> f + '.' + __WebBindMediaTypes.FILE_TYPE_XLSX
        );
    }
}
