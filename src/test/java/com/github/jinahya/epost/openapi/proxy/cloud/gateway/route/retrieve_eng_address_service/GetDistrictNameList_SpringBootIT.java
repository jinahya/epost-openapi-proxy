package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._RouteSpringBootIT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("/getDistrictNameList")
@Slf4j
class GetDistrictNameList_SpringBootIT
        extends _RouteSpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<DistrictEngListRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        DistrictEngListRequest.of(null, "Jeollanam-do", "Naju-si", "D")
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final DistrictEngListRequest request) {
        final var response = exchange(request);
        assertValid(response);
        assertSucceeded(response);
        response.getDistrictEngList().forEach(e -> {
            log.debug("districtEngList: {}", e);
        });
    }
}
