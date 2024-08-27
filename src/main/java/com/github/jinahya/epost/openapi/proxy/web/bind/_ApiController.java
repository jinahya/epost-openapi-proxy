package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.ReactiveWebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.HypermediaMappingInformation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
// https://github.com/spring-projects/spring-boot/issues/41862
public abstract class _ApiController {
//        implements ApplicationListener<ReactiveWebServerInitializedEvent> {

    // -----------------------------------------------------------------------------------------------------------------
    private static final Map<Class<?>, ResolvableType> RESOLVABLE_TYPES = new ConcurrentHashMap<>();

    private static ResolvableType getResolvableTypeOf(final Class<?> type) {
        return RESOLVABLE_TYPES.computeIfAbsent(type, ResolvableType::forClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected static String baseUrl(final ServerHttpRequest request) {
        Objects.requireNonNull(request, "request is null");
        try {
            final var url = request.getURI().toURL();
            return url.getProtocol() + "://" + url.getAuthority();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException("failed to get url from " + request, murle);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected static String getAuthorityFrom(final ServerHttpRequest request) {
        Objects.requireNonNull(request, "request is null");
        try {
            return request.getURI().toURL().getAuthority();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException("failed to get authority from " + request, murle);
        }
    }

    protected static void beforeCommit(final ServerHttpResponse response,
                                       final Consumer<? super ServerHttpResponse> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        response.beforeCommit(() -> {
            consumer.accept(response);
            return Mono.empty();
        });
    }

    protected static void beforeCommit(
            final ServerWebExchange exchange,
            final BiConsumer<? super ServerWebExchange, ? super ServerHttpResponse> consumer) {
        beforeCommit(exchange.getResponse(), r -> consumer.accept(exchange, r));
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @PostConstruct
    private void doOnPostConstruct() {
        objectMapper = objectMapperBuilder
                .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
//                .modulesToInstall(Jackson2HalModule.class)
                .build()
//                .registerModule(new Jackson2HalModule())
        ;
        hypermediaTypes.forEach(hypermediaType -> {
            objectMapper = hypermediaType.configureObjectMapper(objectMapper);
            MimeType[] mimeTypes = hypermediaType.getMediaTypes().toArray(new MimeType[0]);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @EventListener
    protected void onApplicationEvent(final ReactiveWebServerInitializedEvent event) {
        webServer = event.getWebServer();
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected <T> Mono<Void> writeNdjsonResponseWith(final ServerHttpResponse response, Flux<T> flux,
                                                     final Class<T> type) {
        response.beforeCommit(() -> Mono.fromRunnable(() -> {
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
        }));
        final var encoder = new Jackson2JsonEncoder(objectMapper);
        return response.writeWith(
                flux.map(s -> encoder.encodeValue(
                                s,
                                response.bufferFactory(),
                                getResolvableTypeOf(type),
                                null,
                                null
                        ))
                        .map(db -> db.ensureWritable(1).write((byte) 0x0A))
        );
    }

    protected <T> Mono<Void> writeHalJsonResponseWith(final ServerHttpResponse response, Mono<T> mono,
                                                      final Class<T> type) {
        response.beforeCommit(() -> Mono.fromRunnable(() -> {
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE);
        }));
        final var encoder = new Jackson2JsonEncoder(objectMapper);
        return response.writeWith(
                mono.map(s -> encoder.encodeValue(
                        s,
                        response.bufferFactory(),
                        getResolvableTypeOf(type),
                        null,
                        null
                ))
        );
    }

    protected <T> Mono<Void> writeHalJsonResponseWith(final ServerHttpResponse response, T item,
                                                      final Class<T> type) {
        response.beforeCommit(() -> Mono.fromRunnable(() -> {
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE);
        }));
        final var encoder = new Jackson2JsonEncoder(objectMapper);
        final DataBuffer buffer = encoder.encodeValue(
                item,
                response.bufferFactory(),
                getResolvableTypeOf(type),
                null,
                null
        );
        return response.writeWith(
                Mono.just(buffer)
        );
    }

    protected <T> Mono<Void> writeHalJsonResponseWith(final ServerHttpResponse response, EntityModel<T> item,
                                                      final Class<T> type) {
        response.beforeCommit(() -> Mono.fromRunnable(() -> {
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE);
        }));
        final var encoder = new Jackson2JsonEncoder(objectMapper);
        final DataBuffer buffer = encoder.encodeValue(
                item,
                response.bufferFactory(),
                getResolvableTypeOf(type),
                null,
                null
        );
        return response.writeWith(
                Mono.just(buffer)
        );
    }

    // ------------------------------------------------------------------------------------------------------- webClient
    protected WebClient webClient() {
        assert webServer != null;
        if (webClient == null) {
            webClient = WebClient.builder()
                    .baseUrl("http://localhost:" + webServer.getPort())
                    .build();
        }
        return webClient;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private ObjectMapper objectMapper;

    // -----------------------------------------------------------------------------------------------------------------
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    WebServer webServer;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    WebClient webClient;

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private List<HypermediaMappingInformation> hypermediaTypes;
}
