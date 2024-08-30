package com.github.jinahya.epost.openapi.proxy.web.bind;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.context.ReactiveWebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

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
    protected static void beforeCommit(final ServerHttpResponse response,
                                       final Consumer<? super ServerHttpResponse> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        response.beforeCommit(() -> {
            consumer.accept(response);
            return Mono.empty();
        });
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @EventListener
    protected void onApplicationEvent(final ReactiveWebServerInitializedEvent event) {
        webServer = event.getWebServer();
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

    // for mutating in the @SpringBootTest
    void mutateWebClient(final UnaryOperator<WebClient> operator) {
        webClient = Objects.requireNonNull(operator, "operator is null").apply(webClient());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    WebServer webServer;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    WebClient webClient;
}
