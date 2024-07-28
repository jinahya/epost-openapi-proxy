package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractAddressTest;
import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeUtils;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Slf4j
class NewAddressListAreaCdSearchAllResponseTest
        extends AbstractSelfWrappingResponseTypeTest<NewAddressListAreaCdSearchAllResponse> {

    @Nested
    class NewAddressListAreaCdSearchAllTest
            extends AbstractAddressTest<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll> {

        NewAddressListAreaCdSearchAllTest() {
            super(NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll>
        __equals(
                final SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll> verifierApi) {
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
            SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse> verifierApi) {
        return super.__equals(verifierApi);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "response1.xml",
                "response2.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(NewAddressListAreaCdSearchAllResponse.class, r)
        );
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "response1.json",
                "response2.json",
                "response1_.json",
                "response2_.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.deserializeInstance(NewAddressListAreaCdSearchAllResponse.class, r)
        ).get();
        verifyValid(deserialized);
    }
}
