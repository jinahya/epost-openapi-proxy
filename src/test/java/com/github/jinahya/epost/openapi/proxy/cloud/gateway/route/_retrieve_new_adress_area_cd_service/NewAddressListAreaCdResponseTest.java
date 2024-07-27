package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_new_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeTest;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@Slf4j
class NewAddressListAreaCdResponseTest
        extends AbstractSelfWrappingResponseTypeTest<NewAddressListAreaCdResponse> {

    @DisplayName("NewAddressListAreaCd")
    @Nested
    class NewAddressListAreaCdTest
            extends AbstractTypeTest<NewAddressListAreaCdResponse.NewAddressListAreaCd> {

        NewAddressListAreaCdTest() {
            super(NewAddressListAreaCdResponse.NewAddressListAreaCd.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse.NewAddressListAreaCd> __equals(
                final SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse.NewAddressListAreaCd> verifierApi) {
            return super.__equals(verifierApi);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    NewAddressListAreaCdResponseTest() {
        super(NewAddressListAreaCdResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse> __equals(
            final SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse> verifierApi) {
        return super.__equals(verifierApi);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getNewAddressListAreaCd_response1.xml",
                "getNewAddressListAreaCd_response2.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                NewAddressListAreaCdResponse::unmarshalInstance
        );
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getNewAddressListAreaCd_response1.json",
                "getNewAddressListAreaCd_response2.json",
                "getNewAddressListAreaCd_response1_.json",
                "getNewAddressListAreaCd_response2_.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                NewAddressListAreaCdResponse::deserializeInstance
        ).get();
        verifyValid(deserialized);
    }
}
