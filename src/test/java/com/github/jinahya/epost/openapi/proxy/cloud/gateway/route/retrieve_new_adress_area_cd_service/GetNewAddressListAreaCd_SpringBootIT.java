package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._RouteSpringBootIT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@DisplayName("/getNewAddressListAreaCd")
@Slf4j
class GetNewAddressListAreaCd_SpringBootIT
        extends _RouteSpringBootIT {

    static NewAddressListAreaCdResponse exchange(final WebTestClient client,
                                                 final NewAddressListAreaCdRequest request) {
        Objects.requireNonNull(client, "client is null");
        Objects.requireNonNull(request, "request is null");
        final var requestSpec = client
                .method(request.getHttpMethod())
                .uri(request::acceptUriConsumerAndBuild)
                .headers(request::acceptHeaders);
        // -------------------------------------------------------------------------------------------------------- when
        final var responseSpec = requestSpec.exchange();
        // -------------------------------------------------------------------------------------------------------- then
        responseSpec.expectStatus().isOk();
        final var responseBody = Optional.ofNullable(
                        responseSpec
                                .expectBody(NewAddressListAreaCdResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(AbstractResponseType::get)
                .orElseThrow();
        return responseBody;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<NewAddressListAreaCdRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        NewAddressListAreaCdRequest.of(
                                null,
                                NewAddressListAreaCdRequest.SearchSe.DONG,
                                "주월동 408-1",
                                2,
                                1
                        ),
                        NewAddressListAreaCdRequest.of(
                                null,
                                NewAddressListAreaCdRequest.SearchSe.ROAD,
                                "세종로 17",
                                2,
                                1
                        ),
                        NewAddressListAreaCdRequest.of(
                                null,
                                NewAddressListAreaCdRequest.SearchSe.POST,
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
    void __(final NewAddressListAreaCdRequest request) {
        final var response = exchange(request);
        assertValid(response);
        assertSucceeded(response);
        response.getNewAddressListAreaCdList().forEach(e -> {
            log.debug("newAddressListAreaCd: {}", e);
        });
    }
}
