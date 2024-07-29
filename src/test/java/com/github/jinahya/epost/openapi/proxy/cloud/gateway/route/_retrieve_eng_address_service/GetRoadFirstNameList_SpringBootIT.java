package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
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

@DisplayName("/getRoadFirstNameList")
@Slf4j
class GetRoadFirstNameList_SpringBootIT
        extends _SpringBootIT {

    static RoadEngFirstNameListResponse exchange(final WebTestClient client,
                                                 final RoadEngFirstNameListRequest request) {
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
                                .expectBody(RoadEngFirstNameListResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(AbstractResponseType::get)
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
        final var response = exchange(webTestClient(), request);
        verify(response, validator());
        response.getRoadEngFirstNameList().forEach(e -> {
            log.debug("roadEngFirstName: {}", e);
        });
    }
}
