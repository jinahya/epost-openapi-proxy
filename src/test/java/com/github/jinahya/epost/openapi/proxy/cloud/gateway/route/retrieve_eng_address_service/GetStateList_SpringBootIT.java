package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseType;
import com.mycompany.Application;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/getNewAddressListAreaCd")
@Import(
        value = {
                ValidationAutoConfiguration.class
        }
)
@ContextConfiguration(
        classes = {
                Application.class
        }
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class GetStateList_SpringBootIT {

    static Stream<String> getStateEngNameStream() {
        return Stream.of(
                "Jeollanam-do"
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<MediaType> getMediaTypeStream() {
        return Stream.of(
                null,
                MediaType.APPLICATION_XML,
                MediaType.APPLICATION_JSON
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getMediaTypeStream"
    })
    @ParameterizedTest
    void __(final MediaType mediaType) {
        final var requestSpec = webClient
                .get()
                .uri(b -> {
                    final var uri = b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST)
                            .build();
                    log.debug("uri: {}", uri.toASCIIString());
                    return uri;
                });
        if (mediaType != null) {
            requestSpec.accept(mediaType);
        }
        requestSpec.header(HttpHeaders.CACHE_CONTROL, "no-cache");
        // -------------------------------------------------------------------------------------------------------- when
        final var responseSpec = requestSpec.exchange();
        // -------------------------------------------------------------------------------------------------------- then
        responseSpec.expectStatus().isOk();
        if (mediaType != null) {
            responseSpec
                    .expectHeader()
                    .contentTypeCompatibleWith(mediaType);
        }
        final var responseBody = Optional.ofNullable(
                        responseSpec
                                .expectBody(StateEngListResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(AbstractSelfWrappingResponseType::get)
                .orElseThrow();
        assertThat(responseBody).isNotNull().satisfies(r -> {
            assertThat(validator.validate(r)).isEmpty();
        });
        assertThat(responseBody.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        responseBody.getStateEngList().forEach(e -> {
            log.debug("address: {}", e);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private WebTestClient webClient;

    @Autowired
    private Validator validator;
}
