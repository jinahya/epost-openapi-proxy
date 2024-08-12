package com.github.jinahya.epost.openapi.proxy.web.readtive.funcion.client;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoUtils;
import io.micrometer.common.lang.NonNull;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class WebClientUtils {

    private static final int CONNECT_TIMEOUT_MILLIS = Math.toIntExact(TimeUnit.SECONDS.toMillis(4L));

    // -----------------------------------------------------------------------------------------------------------------
    private static final long WRITE_TIMEOUT_TIMEOUT = 4L;

    private static final TimeUnit WRITE_TIMEOUT_UNIT = TimeUnit.SECONDS;

    // -----------------------------------------------------------------------------------------------------------------
    private static final long READ_TIMEOUT_TIMEOUT = 8L;

    private static final TimeUnit READ_TIMEOUT_UNIT = TimeUnit.SECONDS;

    // -----------------------------------------------------------------------------------------------------------------
    public static WebClient.ResponseSpec retrieve(final String baseUrl) {
        Objects.requireNonNull(baseUrl, "baseUrl is null");
        final var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .doOnConnected(c -> {
//                    c.addHandlerFirst(new WriteTimeoutHandler(WRITE_TIMEOUT_TIMEOUT, WRITE_TIMEOUT_UNIT));
//                    c.addHandlerFirst(new ReadTimeoutHandler(READ_TIMEOUT_TIMEOUT, READ_TIMEOUT_UNIT));
                })
                .followRedirect(true);
        final var clientConnector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder()
                .clientConnector(clientConnector)
                .baseUrl(baseUrl)
                .build()
                .get()
                .retrieve();
    }

    public static <T> Flux<T> retrieveBodyToFlux(final String baseUrl, final Class<T> elementClass) {
        Objects.requireNonNull(elementClass, "elementClass is null");
        return retrieve(baseUrl)
                .bodyToFlux(elementClass);
    }

    public static Mono<Void> download(final String baseUrl, final Path destination, final OpenOption... openOptions) {
        Objects.requireNonNull(destination, "destination is null");
        Objects.requireNonNull(openOptions, "openOptions is null");
        return DataBufferUtils.write(
                retrieveBodyToFlux(baseUrl, DataBuffer.class),
                destination,
                openOptions
        );
    }

    // https://stackoverflow.com/q/78832725/330457
    public static Mono<Void> download(final String baseUrl, final Consumer<? super Path> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        return Mono.usingWhen(
                Mono.fromCallable(() -> Files.createTempFile(null, null)).subscribeOn(Schedulers.boundedElastic()),
                p -> download(baseUrl, p, StandardOpenOption.WRITE)
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

    // https://stackoverflow.com/q/78832725/330457
    public static Mono<Void> download(final String baseUrl,
                                      final @NonNull BiConsumer<? super String, ? super Map<String, String>> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        return download(
                baseUrl,
                p -> {
                    try (var stream = new FileInputStream(p.toFile())) {
                        AreaCodeInfoUtils.extract(stream, consumer);
                    } catch (final IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                }
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private WebClientUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
