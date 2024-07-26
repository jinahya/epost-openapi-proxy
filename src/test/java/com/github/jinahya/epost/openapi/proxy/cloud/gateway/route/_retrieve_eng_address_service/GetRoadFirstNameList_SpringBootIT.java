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
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/getNewAddressListAreaCd")
@Slf4j
class GetRoadFirstNameList_SpringBootIT
        extends _SpringBootIT {

    static RoadEngFirstNameListResponse exchange(final WebTestClient testClient,
                                                 final RoadEngFirstNameListRequest request,
                                                 final @Nullable String cacheControl) {
        Objects.requireNonNull(testClient, "testClient is null");
        Objects.requireNonNull(request, "request is null");
        Objects.requireNonNull(cacheControl, "cacheControl is null");
        final var requestSpec = testClient.get().uri(b -> request.set(b).build());
        Optional.ofNullable(request.getAccept()).ifPresent(requestSpec::accept);
        Optional.ofNullable(cacheControl).ifPresent(cc -> {
            requestSpec.header(HttpHeaders.CACHE_CONTROL, cc);
        });
        // -------------------------------------------------------------------------------------------------------- when
        final var responseSpec = requestSpec.exchange();
        // -------------------------------------------------------------------------------------------------------- then
        responseSpec.expectStatus().isOk();
        Optional.ofNullable(request.getAccept()).ifPresent(a -> {
            responseSpec.expectHeader().contentTypeCompatibleWith(a);
        });
        final var responseBody = Optional.ofNullable(
                        responseSpec
                                .expectBody(RoadEngFirstNameListResponse.class)
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
        return responseBody;
    }

    static RoadEngFirstNameListResponse verify(final RoadEngFirstNameListResponse response, final Validator validator) {
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
                        RoadEngFirstNameListRequest
                                .builder()
                                .stateEngName("Jeollanam-do")
                                .cityEngName("Naju-si")
                                .build()
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final RoadEngFirstNameListRequest request) {
        final var response = exchange(webTestClient(), request, "no-cache");
        verify(response, validator());
        response.getRoadEngFirstNameList().forEach(e -> {
            log.debug("roadEngFirstName: {}", e);
        });
    }
}
