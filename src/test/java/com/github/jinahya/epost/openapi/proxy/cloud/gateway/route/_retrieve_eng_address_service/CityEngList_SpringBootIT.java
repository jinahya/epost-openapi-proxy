package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
class CityEngList_SpringBootIT
        extends _SpringBootIT {

//    static Mono<CityEngListResponse> exchange(final StateEngListRequest stateEngListRequest,
//                                              final StateEngListResponse.StateEngList stateEngList,
//                                              final String stateEngName,
//                                              final WebClient webClient) {
//        return new CityEngListRequest()
//                .serviceKey(serviceKey)
//                .stateEngName(stateEngName)
//                .exchangeAndGet(webClient, CityEngListResponse.class);
//    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @Test
    void __() {
        final var cityEngRequest = new CityEngListRequest()
                .serviceKey(serviceKey())
                .stateEngName("Jeollanam-do");
        final var cityEngResponse = cityEngRequest.exchangeAndGet(webClient(), CityEngListResponse.class)
                .<CityEngListResponse>handle(this::handle)
                .block();
        assertValid(cityEngResponse);
    }
}
