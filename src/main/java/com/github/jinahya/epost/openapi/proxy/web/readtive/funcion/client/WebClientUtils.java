package com.github.jinahya.epost.openapi.proxy.web.readtive.funcion.client;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.function.Consumer;

public final class WebClientUtils {

    public static WebClient.ResponseSpec retrieve(final String baseUrl, final Duration responseTimeout) {
        final var httpClient = HttpClient.create()
                .followRedirect(true)
                .responseTimeout(responseTimeout);
        final var clientConnector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder()
                .clientConnector(clientConnector)
                .baseUrl(baseUrl)
                .build()
                .get()
                .retrieve();
    }

    public static <T> Flux<T> retrieveBodyToFlux(final String baseUrl, final Duration responseTimeout,
                                                 final Class<T> elementClass) {
        return retrieve(baseUrl, responseTimeout)
                .bodyToFlux(elementClass);
    }

    public static Mono<Void> download(final String baseUrl, final Duration responseTimeout,
                                      final Path destination, final OpenOption... openOptions) {
        return DataBufferUtils.write(
                retrieveBodyToFlux(baseUrl, responseTimeout, DataBuffer.class),
                destination,
                openOptions
        );
    }

    // https://stackoverflow.com/q/78832725/330457
    public static Mono<Void> download(final String baseUrl, final Duration responseTimeout,
                                      final Consumer<? super Path> consumer) {
        return Mono.usingWhen(
                        Mono.fromCallable(() -> Files.createTempFile(null, null)).subscribeOn(Schedulers.boundedElastic()),
                        p -> download(baseUrl, responseTimeout, p, StandardOpenOption.WRITE)
                                .then(Mono.just(p))
                                .doOnNext(consumer).subscribeOn(Schedulers.boundedElastic()),
                        p -> Mono.fromCallable(() -> {
                            Files.delete(p);
                            return null;
                        }).publishOn(Schedulers.boundedElastic()).then()
                )
                .then();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private WebClientUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
