package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.DisabledIf;

@Slf4j
class StateEngList_SpringBootIT
        extends _SpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @Test
    void __() {
        final var stateEngRequest = serviceKey(new StateEngListRequest());
        final var stateEngResponse = stateEngRequest.exchange(webClient(), StateEngListResponse.class)
                .map(AbstractResponseType::get)
                .<StateEngListResponse>handle(this::handle)
                .block();
        assertValid(stateEngResponse);
    }
}
