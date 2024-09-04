package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.context.ReactiveWebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@SuppressWarnings({
        "java:S119" // <UPPER ...>
})
// https://github.com/spring-projects/spring-boot/issues/41862
public abstract class _ApiService {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @EventListener
//    protected
    void onApplicationEvent(final ReactiveWebServerInitializedEvent event) {
        webServer = event.getWebServer();
    }

    // ------------------------------------------------------------------------------------------------------- webServer

    // ------------------------------------------------------------------------------------------------------- webClient
    protected WebClient webClient() {
        if (webClient == null) {
            final var builder = WebClient.builder();
//            if (exchangeFunction != null) {
//                builder.exchangeFunction(exchangeFunction);
//            }
            final var port = Optional.ofNullable(webServer).map(WebServer::getPort).orElse(8080);
            webClient = builder
                    .baseUrl("http://localhost:" + port)
                    .build();
        }
        return webClient;
    }

    protected <RESPONSE extends AbstractResponseType<RESPONSE>>
    Mono<RESPONSE> exchange(final AbstractRequestType<?> requestInstance, final Class<RESPONSE> responseType) {
        Objects.requireNonNull(requestInstance, "requestInstance is null");
        Objects.requireNonNull(responseType, "responseType is null");
        return requestInstance.exchange(webClient(), responseType);
    }

    protected <
            REQUEST extends AbstractPairedRequestType<REQUEST, RESPONSE>,
            RESPONSE extends AbstractPairedResponseType<RESPONSE, REQUEST>>
    Mono<RESPONSE> exchange(final REQUEST requestInstance) {
        Objects.requireNonNull(requestInstance, "requestInstance is null");
        return requestInstance.exchange(webClient());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private WebServer webServer;

//    @LocalExchangeFunction
//    @Autowired(required = false)
//    private ExchangeFunction exchangeFunction;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private WebClient webClient;
}
