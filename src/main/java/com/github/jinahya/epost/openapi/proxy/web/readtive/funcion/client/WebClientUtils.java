package com.github.jinahya.epost.openapi.proxy.web.readtive.funcion.client;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Objects;
import java.util.function.Consumer;

public final class WebClientUtils {

    public static WebClient.ResponseSpec retrieve(final String baseUrl, final Duration responseTimeout) {
        Objects.requireNonNull(baseUrl, "baseUrl is null");
        Objects.requireNonNull(responseTimeout, "responseTimeout is null");
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
        Objects.requireNonNull(elementClass, "elementClass is null");
        return retrieve(baseUrl, responseTimeout)
                .bodyToFlux(elementClass);
    }

    public static Mono<Void> download(final String baseUrl, final Duration responseTimeout,
                                      final Path destination, final OpenOption... openOptions) {
        Objects.requireNonNull(destination, "destination is null");
        Objects.requireNonNull(openOptions, "openOptions is null");
        return DataBufferUtils.write(
                retrieveBodyToFlux(baseUrl, responseTimeout, DataBuffer.class),
                destination,
                openOptions
        );
    }

    // https://stackoverflow.com/q/78832725/330457
    public static Mono<Void> download(final String baseUrl, final Duration responseTimeout,
                                      final Consumer<? super Path> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        return Mono.usingWhen(
                Mono.fromCallable(() -> Files.createTempFile(null, null)).subscribeOn(Schedulers.boundedElastic()),
                p -> download(baseUrl, responseTimeout, p, StandardOpenOption.WRITE)
                        .then(Mono.fromRunnable(() -> consumer.accept(p)).subscribeOn(Schedulers.boundedElastic())),
                p -> Mono.fromRunnable(() -> {
                    try {
                        Files.delete(p);
                    } catch (final IOException ioe) {
                        throw new RuntimeException("failed to delete " + p, ioe);
                    }
                }).subscribeOn(Schedulers.boundedElastic())
        ).then();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private WebClientUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
