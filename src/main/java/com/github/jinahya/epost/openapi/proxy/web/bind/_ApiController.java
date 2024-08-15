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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @PostConstruct
    private void doOnPostConstruct() {
        objectMapper = objectMapperBuilder
                .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
                .build();
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
//            response.getHeaders().remove(HttpHeaders.CONTENT_TYPE);
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
}
