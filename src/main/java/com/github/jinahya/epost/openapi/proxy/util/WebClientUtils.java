package com.github.jinahya.epost.openapi.proxy.util;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
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
    public static WebClient.ResponseSpec retrieve(final String url) {
        final var httpClient = HttpClient.create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .doOnConnected(c -> {
//                    c.addHandlerFirst(new WriteTimeoutHandler(WRITE_TIMEOUT_TIMEOUT, WRITE_TIMEOUT_UNIT));
//                    c.addHandlerFirst(new ReadTimeoutHandler(READ_TIMEOUT_TIMEOUT, READ_TIMEOUT_UNIT));
                })
                .followRedirect(true);
        final var clientConnector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder()
                .clientConnector(clientConnector)
                .baseUrl(url)
                .build()
                .get()
                .retrieve();
    }

    public static <T> Flux<T> retrieveBodyToFlux(final String url, final Class<T> elementClass) {
        return retrieve(url)
                .bodyToFlux(elementClass);
    }

    public static Mono<Void> download(final String url, final Path destination, final OpenOption... openOptions) {
        return DataBufferUtils.write(
                retrieveBodyToFlux(url, DataBuffer.class),
                destination,
                openOptions
        );
    }

    // https://stackoverflow.com/q/78832725/330457
    public static Mono<Void> download(final String url, final Consumer<? super Path> consumer,
                                      final Scheduler scheduler) {
        Objects.requireNonNull(consumer, "consumer is null");
        return Mono.usingWhen(
                Mono.fromCallable(() -> Files.createTempFile(null, null)).subscribeOn(Schedulers.boundedElastic()),
                p -> download(url, p, StandardOpenOption.WRITE)
                        .then(Mono.fromRunnable(() -> consumer.accept(p)).subscribeOn(Schedulers.boundedElastic())),
                p -> Mono.fromRunnable(() -> {
                    try {
                        Files.delete(p);
                    } catch (final IOException ioe) {
                        throw new RuntimeException("failed to delete " + p, ioe);
                    }
                }).subscribeOn(scheduler)
        ).then();
    }

    /**
     * Downloads specified URL to a temporary file, and accepts the file to specified consumer.
     *
     * @param url      the URL to download.
     * @param consumer the consumer accepts the file.
     * @return a mono of {@link Void}.
     */
    // https://stackoverflow.com/q/78832725/330457
    public static Mono<Void> download(final String url, final Consumer<? super Path> consumer) {
        return download(
                url,
                consumer,
                Schedulers.boundedElastic()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private WebClientUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
