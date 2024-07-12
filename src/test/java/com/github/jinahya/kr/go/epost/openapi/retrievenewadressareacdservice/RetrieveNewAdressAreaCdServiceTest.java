package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class RetrieveNewAdressAreaCdServiceTest {

    private static final String SYSTEM_PROPERTY_SERVICE_KEY = "serviceKey";

    private static NewAddressListRequest.SearchSe[] SEARCH_SE_VALUES = NewAddressListRequest.SearchSe.values();

    private static NewAddressListRequest.SearchSe randomSearchSe() {
        return SEARCH_SE_VALUES[ThreadLocalRandom.current().nextInt(SEARCH_SE_VALUES.length)];
    }

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of(
                        NewAddressListRequest.SearchSe.dong,
                        "주월동 408-1",
                        10,
                        1
                )
        );
    }

    @Test
    void _a_() {

    }

    //    @EnabledIfSystemProperty(named = SYSTEM_PROPERTY_SERVICE_KEY, matches = ".+")
//    @EnabledIfSystemProperty(named = SYSTEM_PROPERTY_SERVICE_KEY, matches = ".+")
//    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @DisabledIf("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "'] == null}")
    @MethodSource({
            "argumentsStream"
    })
    @ParameterizedTest
    void __(final NewAddressListRequest.SearchSe searchSe, final String srchwrd, final int countPerPage,
            final int currentPage) {
        final var result = webClient
                .get()
                .uri(b -> {
                    final var uri = b.path(Constants.uri())
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_SERVICE_KEY,
                                        System.getProperty(SYSTEM_PROPERTY_SERVICE_KEY))
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_SERACH_SE, searchSe.name())
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_SRCHWRD, srchwrd)
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_COUNT_PER_PAGE, countPerPage)
                            .queryParam(NewAddressListRequest.QUERY_PARAM_NAME_CURRENT_PAGE, currentPage)
                            .build();
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
//            assertThat(h.isSucceeded()).isTrue();
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private WebTestClient webClient;
}
