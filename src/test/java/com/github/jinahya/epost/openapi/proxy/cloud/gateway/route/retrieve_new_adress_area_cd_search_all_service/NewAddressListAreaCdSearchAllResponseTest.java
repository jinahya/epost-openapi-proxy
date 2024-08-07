package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseElementAddressTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Slf4j
class NewAddressListAreaCdSearchAllResponseTest
        extends AbstractPairedResponseTypeTest<
        NewAddressListAreaCdSearchAllResponse,
        NewAddressListAreaCdSearchAllRequest> {

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class NewAddressListAreaCdSearchAllTest
            extends AbstractResponseElementAddressTypeTest<
            NewAddressListAreaCdSearchAll,
            NewAddressListAreaCdSearchAllResponse> {

        NewAddressListAreaCdSearchAllTest() {
            super(NewAddressListAreaCdSearchAll.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAll> __equals(
                final SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAll> verifierApi) {
            return super.__equals(verifierApi);
        }
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    NewAddressListAreaCdSearchAllResponseTest() {
        super(NewAddressListAreaCdSearchAllResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse> __equals(
            final SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse> verifierApi) {
        return super.__equals(verifierApi);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getNewAddressListAreaCdSearchAll_response1.xml",
                "getNewAddressListAreaCdSearchAll_response2.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = unmarshalNoNamespacedInstanceFromResource(resName);
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getNewAddressListAreaCdSearchAll_response1.json",
                "getNewAddressListAreaCdSearchAll_response2.json",
                "getNewAddressListAreaCdSearchAll_response1_.json",
                "getNewAddressListAreaCdSearchAll_response2_.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var deserialized = deserializeInstanceFromResource(resName).get();
        verifyValid(deserialized);
    }
}
