package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
class StateEngList_SpringBootIT
        extends _SpringBootIT {

    static Mono<StateEngListResponse> exchange(final String serviceKey, final WebClient webClient) {
        return new StateEngListRequest().serviceKey(serviceKey)
                .exchangeAndGet(webClient, StateEngListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @Test
    void __() {
        final var stateEngRequest = serviceKey(new StateEngListRequest());
        final var stateEngResponse = stateEngRequest.exchangeAndGet(webClient(), StateEngListResponse.class)
                .<StateEngListResponse>handle(this::handle)
                .block();
        assertValid(stateEngResponse);
    }
}
