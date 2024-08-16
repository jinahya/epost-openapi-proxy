package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._RouteSpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractRequestTypeTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("/getNewAddressListAreaCdSearchAll")
@Slf4j
class GetNewAddressListAreaCdSearchAll_SpringBootIT
        extends _RouteSpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<NewAddressListAreaCdSearchAllRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        NewAddressListAreaCdSearchAllRequest.of(
                                null,
                                "주월동 408-1",
                                2,
                                1
                        ),
                        NewAddressListAreaCdSearchAllRequest.of(
                                null,
                                "세종로 17",
                                2,
                                1
                        ),
                        NewAddressListAreaCdSearchAllRequest.of(
                                null,
                                "61725",
                                2,
                                1
                        )
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final NewAddressListAreaCdSearchAllRequest request) {
        final var response = exchange(request);
        assertValid(response);
        assertSucceeded(response);
        response.getNewAddressListAreaCdSearchAll().forEach(e -> {
            log.debug("newAddressListAreaCdSearchAll: {}", e);
        });
    }
}
