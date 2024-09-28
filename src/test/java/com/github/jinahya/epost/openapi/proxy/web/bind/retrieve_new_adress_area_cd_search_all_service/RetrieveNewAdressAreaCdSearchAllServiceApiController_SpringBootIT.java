package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.hateoas.server.core.TypeReferences;

import java.util.stream.Stream;

import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service._RetrieveNewAdressAreCdSearchAllServiceApiConstants.REQUEST_PARAM_SRCHWRD;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service._RetrieveNewAdressAreCdSearchAllServiceApiConstants.REQUEST_URI_SEARCH;
import static org.assertj.core.api.Assertions.assertThat;

class RetrieveNewAdressAreaCdSearchAllServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<
        RetrieveNewAdressAreaCdSearchAllServiceApiController, RetrieveNewAdressAreaCdSearchAllServiceApiService> {

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({
            "java:S1114" // unused
    })
    private static Stream<Arguments> getSearchArgumentsStream() {
        return Stream.of(
                Arguments.of("세종로 17", 23),
                Arguments.of("주월동 408-1", 1),
                Arguments.of("61725", 26)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"getSearchArgumentsStream"})
    @ParameterizedTest
    void __(final String srchwrd, final int size) {
        final var result = readList(
                NewAddressListAreaCdSearchAll.class,
                b -> b.path(REQUEST_URI_SEARCH)
                        .queryParam(REQUEST_PARAM_SRCHWRD, srchwrd)
                        .build(),
                null,
                new TypeReferences.EntityModelType<>() {
                }

        );
        assertThat(result).hasSize(size);
    }
}
