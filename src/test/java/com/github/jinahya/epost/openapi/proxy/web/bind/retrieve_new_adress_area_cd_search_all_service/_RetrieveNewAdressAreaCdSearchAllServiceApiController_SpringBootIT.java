package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class _RetrieveNewAdressAreaCdSearchAllServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<_RetrieveNewAdressAreaCdSearchAllServiceApiController> {

    // -----------------------------------------------------------------------------------------------------------------
    static List<Address> search(final WebTestClient client, final String srchwrd) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveNewAdressAreCdSearchAllServiceApiConstants.REQUEST_URI_SEARCH)
                                .queryParam(__RetrieveNewAdressAreCdSearchAllServiceApiConstants.REQUEST_PARAM_SRCHWRD,
                                            srchwrd)
                                .build())
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                        .expectBodyList(Address.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    private static Stream<Arguments> getSearchArgumentsStream() {
        return Stream.of(
                Arguments.of("세종로 17", 23),
                Arguments.of("주월동 408-1", 1),
                Arguments.of("61725", 26)
        );
    }

    @MethodSource({"getSearchArgumentsStream"})
    @ParameterizedTest
    void __(final String srchwrd, final int size) {
        final var result = search(webTestClient(), srchwrd);
        assertThat(result).hasSize(size);
    }
}
