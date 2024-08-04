package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/getStateList")
@Slf4j
class GetStateList_SpringBootIT
        extends _SpringBootIT {

    static StateEngListResponse exchange(final WebTestClient client, final StateEngListRequest request) {
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
                                .expectBody(StateEngListResponse.class)
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
        responseBody.getStateEngList().forEach(e -> {
            log.debug("roadEngFirstName: {}", e);
        });
        return responseBody;
    }

    static StateEngListResponse verify(final StateEngListResponse response, final Validator validator) {
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
        final var response = exchange(webTestClient(), request);
        assertThat(response).isNotNull().satisfies(r -> {
            assertValid(r);
            assertSucceeded(r);
            response.getStateEngList().forEach(e -> {
                log.debug("stateEngList: {}", e);
            });
        });
    }

    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @MethodSource({
            "getRequestStreamWithMediaTypes"
    })
    @ParameterizedTest
    void exchange__(final StateEngListRequest request) {
        final var response = request.serviceKey(serviceKey())
                .exchange(webClient())
                .block();
        assertThat(response).isNotNull().satisfies(r -> {
            assertValid(r);
            assertSucceeded(r);
            response.getStateEngList().forEach(e -> {
                log.debug("stateEngList: {}", e);
            });
        });
    }
}
