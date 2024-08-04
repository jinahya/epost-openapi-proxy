package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractTypeUtils;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RoadAddressEngSearchListResponseTest
        extends AbstractPairedResponseTypeTest<RoadAddressEngSearchListResponse, RoadAddressEngSearchListRequest> {

    RoadAddressEngSearchListResponseTest() {
        super(RoadAddressEngSearchListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<RoadAddressEngSearchListResponse> __equals(
            final SingleTypeEqualsVerifierApi<RoadAddressEngSearchListResponse> verifierApi) {
        return super.__equals(verifierApi)
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getRoadAddressSearch_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(RoadAddressEngSearchListResponse.class, r)
        );
        verifyValid(unmarshalled);
    }

    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getRoadAddressSearch_response0.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.deserializeInstance(RoadAddressEngSearchListResponse.class, r).get()
        );
        verifyValid(deserialized);
    }
}
