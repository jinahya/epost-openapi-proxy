package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/getNewAddressListAreaCd")
@Slf4j
class GetRoadNameList_SpringBootIT
        extends _SpringBootIT {

    @Autowired
    private WebTestClient webTestClient;

    static RoadEngListResponse exchange(final WebTestClient testClient, final RoadEngListRequest request,
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
        final var response = Optional.ofNullable(
                        responseSpec
                                .expectBody(RoadEngListResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(AbstractSelfWrappingResponseType::get)
                .orElseThrow();
        assertThat(response.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        return response;
    }

    static RoadEngListResponse verify(final RoadEngListResponse response, final Validator validator) {
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
    private static Stream<RoadEngListRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                Stream.of(
                        RoadEngListRequest.builder()
                                .stateEngName("Jeollanam-do")
                                .cityEngName("Naju-si")
                                .roadEngFirstName("D")
                                .build()
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final RoadEngListRequest request) {
        final var response = exchange(webTestClient(), request, "no-cache");
        verify(response, validator());
        response.getRoadEngList().forEach(e -> {
            log.debug("roadEngList: {}", e);
        });
    }
}
