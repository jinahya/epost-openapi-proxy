package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractTypeUtils;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LandEngListResponseTest
        extends AbstractResponseTypeTest<LandAddressEngSearchListResponse> {

    LandEngListResponseTest() {
        super(LandAddressEngSearchListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<LandAddressEngSearchListResponse> __equals(
            SingleTypeEqualsVerifierApi<LandAddressEngSearchListResponse> verifierApi) {
        return super.__equals(verifierApi)
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getLandAddressSearch_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(LandAddressEngSearchListResponse.class, r)
        );
        verifyValid(unmarshalled);
    }

    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getLandAddressSearch_response0.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.deserializeInstance(LandAddressEngSearchListResponse.class, r)
        );
        verifyValid(unmarshalled);
    }
}
