package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/getNewAddressListAreaCd")
@Slf4j
class GetCityList_SpringBootIT
        extends _SpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    static CityEngListResponse exchange(final WebTestClient client, final CityEngListRequest request) {
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
                                .expectBody(CityEngListResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(AbstractSelfWrappingResponseType::get)
                .orElseThrow();
        assertThat(responseBody.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        responseBody.getCityEngList().forEach(e -> {
            log.debug("roadEngFirstName: {}", e);
        });
        return responseBody;
    }

    static CityEngListResponse verify(final CityEngListResponse response, final Validator validator) {
        assertThat(response).isNotNull().satisfies(r -> {
            assertThat(validator.validate(r)).isEmpty();
        });
        assertThat(response.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        return response;
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Stream<Arguments> getArgumentsStream() {
        return Stream.of(
                Arguments.of("Jeollanam-do")
        );
    }

    private static Stream<CityEngListRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        CityEngListRequest.of(null, "Jeollanam-do")
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final CityEngListRequest request) {
        final var response = exchange(webTestClient(), request);
        verify(response, validator());
        response.getCityEngList().forEach(e -> {
            log.debug("cityEngList: {}", e);
        });
    }
}
