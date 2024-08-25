package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_deliv_area_cd_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractTypeUtils;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DelivAreaCdResponseTest
        extends AbstractPairedResponseTypeTest<DelivAreaCdResponse, DelivAreaCdRequest> {

    DelivAreaCdResponseTest() {
        super(DelivAreaCdResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<DelivAreaCdResponse> __equals(
            final SingleTypeEqualsVerifierApi<DelivAreaCdResponse> verifierApi) {
        return super.__equals(verifierApi);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getDelivAreaCd_response1.xml",
                "getDelivAreaCd_response2.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(DelivAreaCdResponse.class, r)
        );
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getDelivAreaCd_response1.json",
                "getDelivAreaCd_response2.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        // -------------------------------------------------------------------------------------------------------- when
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.deserializeInstance(DelivAreaCdResponse.class, r).get()
        );
        // -------------------------------------------------------------------------------------------------------- then
        verifyValid(deserialized);
    }
}
