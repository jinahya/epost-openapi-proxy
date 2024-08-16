package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractPairedRequestTypeTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class NewAddressListAreaCdRequestTest
        extends AbstractPairedRequestTypeTest<NewAddressListAreaCdRequest, NewAddressListAreaCdResponse> {

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class SearchSeTest {

        @DisplayName("no duplicates in #text()")
        @Test
        void _NoDuplicates_Texts() {
            assertThat(
                    Arrays.stream(NewAddressListAreaCdRequest.SearchSe.values())
                            .map(NewAddressListAreaCdRequest.SearchSe::text)
                            .allMatch(new HashSet<>()::add)
            )
                    .isTrue();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    NewAddressListAreaCdRequestTest() {
        super(NewAddressListAreaCdRequest.class);
    }
}
