package com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_service;

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
class GetNewAddressListAreaCd_SpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    private static final String SYSTEM_PROPERTY_SERVICE_KEY = "serviceKey";

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(
                        GetNewAddressListAreaCdRequest.SearchSe.dong,
                        "주월동 408-1",
                        2,
                        1
                ),
                Arguments.of(
                        GetNewAddressListAreaCdRequest.SearchSe.road,
                        "세종로 17",
                        2,
                        1
                ),
                Arguments.of(
                        GetNewAddressListAreaCdRequest.SearchSe.post,
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
            ).map(m -> Arguments.of(got[0], got[1], got[2], got[3], m));
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @MethodSource({
            "argumentsStreamWithMediaType"
    })
    @ParameterizedTest
    void __(final GetNewAddressListAreaCdRequest.SearchSe searchSe, final String srchwrd, final int countPerPage,
            final int currentPage, final MediaType mediaType) {
        final var serviceKey = System.getProperty(SYSTEM_PROPERTY_SERVICE_KEY);
        assertThat(serviceKey)
                .as("-DserviceKey")
                .isNotBlank();
        final var requestSpec = webClient
                .get()
                .uri(b -> {
                    final var uri = b.path(Constants.requestUri())
                            .queryParam(GetNewAddressListAreaCdRequest.QUERY_PARAM_NAME_SERVICE_KEY, serviceKey)
                            .queryParam(GetNewAddressListAreaCdRequest.QUERY_PARAM_NAME_SEARCH_SE, searchSe.name())
                            .queryParam(GetNewAddressListAreaCdRequest.QUERY_PARAM_NAME_SRCHWRD, srchwrd)
                            .queryParam(GetNewAddressListAreaCdRequest.QUERY_PARAM_NAME_COUNT_PER_PAGE, countPerPage)
                            .queryParam(GetNewAddressListAreaCdRequest.QUERY_PARAM_NAME_CURRENT_PAGE, currentPage)
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
        if (false && mediaType != null) {
            responseSpec
                    .expectHeader()
                    .contentTypeCompatibleWith(mediaType);
        }
        final var responseBody = Optional.ofNullable(
                        responseSpec
                                .expectBody(GetNewAddressListAreaCdResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(GetNewAddressListAreaCdResponse::get)
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
        responseBody.getNewAddressListAreaCdList().forEach(e -> {
            log.debug("address: {}", e);
        });
        assertThat(responseBody.getNewAddressListAreaCdList()).satisfiesAnyOf(
                l -> assertThat(l).isEmpty(),
                l -> assertThat(l).isNotEmpty().hasSizeLessThanOrEqualTo(countPerPage).allSatisfy(e -> {
                    switch (searchSe) {
                        case dong:
                            assertThat(e.getRnAdres()).contains(srchwrd);
                            break;
                        case road:
                            assertThat(e.getLnmAdres()).contains(srchwrd);
                            break;
                        default:
                            assertThat(searchSe).isSameAs(GetNewAddressListAreaCdRequest.SearchSe.post);
                            assertThat(e.getZipNo()).isEqualTo(srchwrd);
                            break;
                    }
                }));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private WebTestClient webClient;

    @Autowired
    private Validator validator;
}
