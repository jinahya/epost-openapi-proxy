package com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_service.NewAddressListAreaCdRequest;
import com.mycompany.Application;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Import(
        value = {
                ValidationAutoConfiguration.class
        }
)
@ContextConfiguration(classes = {Application.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class GetNewAddressListAreaCdSearchAll_SpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    private static final String SYSTEM_PROPERTY_SERVICE_KEY = "serviceKey";

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(
                        "주월동 408-1",
                        2,
                        1
                ),
                Arguments.of(
                        "세종로 17",
                        2,
                        1
                ),
                Arguments.of(
                        "61725",
                        2,
                        1
                )
        );
    }

    private static Stream<Arguments> argumentsStreamWithMediaType() {
        return argumentsStream().flatMap(a -> {
            final var got = a.get();
            return Stream.of(
                    null,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_JSON
            ).map(m -> Arguments.of(got[0], got[1], got[2], m));
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @MethodSource({
            "argumentsStreamWithMediaType"
    })
    @ParameterizedTest
    void __(final String srchwrd, final int countPerPage, final int currentPage, final MediaType mediaType) {
        final var serviceKey = System.getProperty(SYSTEM_PROPERTY_SERVICE_KEY);
        log.debug("serviceKey: {}", serviceKey);
        assertThat(serviceKey).isNotBlank();
        final var requestSpec = webClient
                .get()
                .uri(b -> {
                    final var uri = b.path(
                                    _RetrieveNewAddressAreaCdSearchAllServiceConstants.REQUEST_URI_GET_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
                            .queryParam(NewAddressListAreaCdRequest.QUERY_PARAM_NAME_SERVICE_KEY, serviceKey)
                            .queryParam(NewAddressListAreaCdRequest.QUERY_PARAM_NAME_SRCHWRD, srchwrd)
                            .queryParam(NewAddressListAreaCdRequest.QUERY_PARAM_NAME_COUNT_PER_PAGE, countPerPage)
                            .queryParam(NewAddressListAreaCdRequest.QUERY_PARAM_NAME_CURRENT_PAGE, currentPage)
                            .build();
                    log.debug("uri: {}", uri.toASCIIString());
                    return uri;
                });
        if (mediaType != null) {
            requestSpec.accept(mediaType);
        }
        final var responseSpec = requestSpec
                .exchange()
                .expectStatus().isOk();
        if (mediaType != null) {
            responseSpec
                    .expectHeader()
                    .contentTypeCompatibleWith(mediaType);
        }
        final var responseBody = Optional.ofNullable(
                        responseSpec
                                .expectBody(NewAddressListAreaCdSearchAllResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(NewAddressListAreaCdSearchAllResponse::get)
                .orElseThrow();
        log.debug("responseBody: {}", responseBody);
        assertThat(responseBody).isNotNull().satisfies(r -> {
            assertThat(validator.validate(r)).isEmpty();
        });
        assertThat(responseBody.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        responseBody.getNewAddressListAreaCdSearchAll().forEach(e -> {
            log.debug("address: {}", e);
        });
        assertThat(responseBody.getNewAddressListAreaCdSearchAll()).satisfiesAnyOf(
                l -> assertThat(l).isEmpty(),
                l -> assertThat(l).isNotEmpty().hasSizeLessThanOrEqualTo(countPerPage).allSatisfy(e -> {
                    assertThat(validator.validate(e)).isEmpty();
                }));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private WebTestClient webClient;

    @Autowired
    private Validator validator;
}
