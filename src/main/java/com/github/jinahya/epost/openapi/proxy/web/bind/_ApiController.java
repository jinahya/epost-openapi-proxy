package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jinahya.epost.openapi.proxy.http.codec.json._Jackson2JsonEncoder;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.ReactiveWebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL, stacks = WebStack.WEBFLUX)
//@Import({JacksonAutoConfiguration.class})
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Scope("application")
@Slf4j
public abstract class _ApiController {

    protected _ApiController() {
        super();
    }

    @PostConstruct
    private void doOnPostConstruct() {
        objectMapper = objectMapperBuilder
                .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
                .build();
        log.debug("this: {}", this);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @EventListener
    void onApplicationEvent(final ReactiveWebServerInitializedEvent event) {
        log.debug("event: {}", event);
        webServer = event.getWebServer();
        log.debug("webServer: {}", webServer);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected <T> Mono<Void> writeNdjsonResponseWith(final ServerHttpResponse response, Flux<T> flux, final Class<T> type) {
        log.debug("webServer: {}", webServer);
        response.beforeCommit(() -> Mono.fromRunnable(() -> {
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
        }));
        final var encoder = new _Jackson2JsonEncoder(objectMapper());
        return response.writeWith(
                flux.map(s -> encoder.encodeValue(
                                s,
                                response.bufferFactory(),
                                ResolvableType.forType(type),
                                null,
                                null
                        ))
                        .map(db -> db.ensureWritable(1).write((byte) 0x0A))
        );
    }

//    // --------------------------------------------------------------------------------------------- objectMapperBuilder
//    private Jackson2ObjectMapperBuilder objectMapperBuilder() {
//        if (objectMapperBuilder == null) {
//            objectMapperBuilder = new Jackson2ObjectMapperBuilder()
//                    .featuresToDisable(SerializationFeature.INDENT_OUTPUT);
//        }
//        return objectMapperBuilder;
//    }
//
//    // ------------------------------------------------------------------------------------------------------- objectMapper
//    protected ObjectMapper objectMapper() {
//        if (objectMapper == null) {
//            objectMapper = objectMapperBuilder().build();
//        }
//        return objectMapper;
//    }

    // ------------------------------------------------------------------------------------------------------- webClient
    protected WebClient webClient() {
        if (webClient == null) {
            webClient = WebClient.builder()
//                    .baseUrl("http://localhost:" + webServerPort)
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

    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private ObjectMapper objectMapper;

    // -----------------------------------------------------------------------------------------------------------------
    private WebServer webServer;

    @Setter(AccessLevel.NONE)
    private WebClient webClient;
}
