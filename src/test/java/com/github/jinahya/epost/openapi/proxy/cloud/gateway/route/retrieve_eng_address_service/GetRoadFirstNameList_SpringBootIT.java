package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._RouteSpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractRequestTypeTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("/getRoadFirstNameList")
@Slf4j
class GetRoadFirstNameList_SpringBootIT
        extends _RouteSpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    static Stream<Arguments> getArgumentsStream() {
        return GetCityList_SpringBootIT.getArgumentsStream()
                .flatMap(a -> {
                    final var got = a.get();
                    return Stream.of("Naju-si")
                            .map(cen -> Arguments.of(got[0], cen));
                });
    }

    private static Stream<RoadEngFirstNameListRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        RoadEngFirstNameListRequest.of(null, "Jeollanam-do", "Naju-si")
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final RoadEngFirstNameListRequest request) {
        final var response = exchange(request);
        assertValid(response);
        assertSucceeded(response);
        response.getRoadEngFirstNameList().forEach(e -> {
            log.debug("roadEngFirstName: {}", e);
        });
    }
}
