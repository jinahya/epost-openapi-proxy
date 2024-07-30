package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/getRoadAddressSearch")
//@Import(
//        value = {
//                ValidationAutoConfiguration.class
//        }
//)
//@ContextConfiguration(
//        classes = {
//                Application.class
//        }
//)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class GetRoadAddressSearch_SpringBootIT
        extends _SpringBootIT {

    static RoadAddressEngSearchListResponse exchange(final WebTestClient client,
                                                     final RoadAddressEngSearchListRequest request) {
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
                                .expectBody(RoadAddressEngSearchListResponse.class)
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
        responseBody.getRoadAddressEngSearchList().forEach(e -> {
            log.debug("roadEngFirstName: {}", e);
        });
        return responseBody;
    }

    static RoadAddressEngSearchListResponse verify(final RoadAddressEngSearchListResponse response,
                                                   final Validator validator) {
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
    void __(final RoadAddressEngSearchListRequest request) {
        final var response = exchange(webTestClient(), request);
        verify(response, validator());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @Test
    void __() {
        new StateEngListRequest()
                .serviceKey(serviceKey())
                .exchange(webClient())
                .<StateEngListResponse>handle(this::handle)
                .flatMapMany(selr -> Flux.fromStream(selr.getStateEngList().stream().map(e -> e.parent(selr)).limit(2)))
                .map(CityEngListRequest::from)
                .flatMap(cityEngListRequest -> cityEngListRequest.exchange(webClient(), CityEngListResponse.class))
                .blockLast();
    }
}
