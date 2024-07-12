package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class RetrieveNewAdressAreaCdServiceTest {

    private static final String SYSTEM_PROPERTY_SERVICE_KEY = "serviceKey";

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(
                        NewAddressListRequest.SearchSe.dong,
                        "주월동 408-1",
                        2,
                        1
                ),
                Arguments.of(
                        NewAddressListRequest.SearchSe.road,
                        "세종로 17",
                        2,
                        1
                ),
                Arguments.of(
                        NewAddressListRequest.SearchSe.post,
                        "61725",
                        2,
                        1
                )
        );
    }

    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @MethodSource({
            "argumentsStream"
    })
    @ParameterizedTest
    void __(final NewAddressListRequest.SearchSe searchSe, final String srchwrd, final int countPerPage,
            final int currentPage) {
        final var serviceKey = System.getProperty(SYSTEM_PROPERTY_SERVICE_KEY);
        assertThat(serviceKey).isNotBlank();
        final var result = webClient
                .get()
                .uri(b -> {
                    final var uri = b.path(Constants.uri())
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_SERVICE_KEY, serviceKey)
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_SERACH_SE, searchSe.name())
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_SRCHWRD, srchwrd)
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_COUNT_PER_PAGE, countPerPage)
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_CURRENT_PAGE, currentPage)
                            .build();
                    log.debug("uri: {}", uri.toASCIIString());
                    return uri;
                })
                .accept(MediaType.APPLICATION_XML)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NewAddressListResponse.class)
                .returnResult()
                .getResponseBody();
        log.debug("body: {}", result);
        assertThat(result).isNotNull();
        assertThat(result.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
        });
        result.getNewAddressListAreaCdList().forEach(e -> {
            log.debug("address: {}", e);
        });
        assertThat(result.getNewAddressListAreaCdList()).satisfiesAnyOf(
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
                            assertThat(searchSe).isSameAs(NewAddressListRequest.SearchSe.post);
                            assertThat(e.getZipNo()).isEqualTo(srchwrd);
                            break;
                    }
                })
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private WebTestClient webClient;
}
