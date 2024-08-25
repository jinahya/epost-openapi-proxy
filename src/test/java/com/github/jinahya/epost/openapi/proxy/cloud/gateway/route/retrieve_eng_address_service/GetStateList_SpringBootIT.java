package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._RouteSpringBootIT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("/getStateList")
@Slf4j
class GetStateList_SpringBootIT
        extends _RouteSpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<StateEngListRequest> getRequestStreamWithMediaTypes() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        new StateEngListRequest()
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStreamWithMediaTypes"
    })
    @ParameterizedTest
    void __(final StateEngListRequest request) {
        final var response = exchange(request);
        assertValid(response);
        assertSucceeded(response);
        response.getStateEngList().forEach(e -> {
            log.debug("stateEngList: {}", e);
        });
    }
}
