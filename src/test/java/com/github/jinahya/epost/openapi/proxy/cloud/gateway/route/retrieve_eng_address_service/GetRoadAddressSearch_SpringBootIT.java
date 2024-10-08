package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.PageInfo;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._RouteSpringBootIT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("/getRoadAddressSearch")
@Slf4j
class GetRoadAddressSearch_SpringBootIT
        extends _RouteSpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    static Stream<RoadAddressEngSearchListRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        RoadAddressEngSearchListRequest.of(
                                null,
                                "Jeollanam-do",
                                "Naju-si",
                                "D",
                                "Daeho-gil",
                                "45-40",
                                new PageInfo(2, 1)
                        )
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final RoadAddressEngSearchListRequest request) {
        final var response = exchange(request);
        assertValid(response);
        assertSucceeded(response);
    }
}
