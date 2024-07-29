package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractSelfWrappingResponseTypeTest;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DistrictEngFirstNameListResponseTest
        extends AbstractSelfWrappingResponseTypeTest<DistrictEngFirstNameListResponse> {

    DistrictEngFirstNameListResponseTest() {
        super(DistrictEngFirstNameListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<DistrictEngFirstNameListResponse> __equals(
            SingleTypeEqualsVerifierApi<DistrictEngFirstNameListResponse> verifierApi) {
        return super.__equals(verifierApi)
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getDistrictFirstNameList_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                DistrictEngFirstNameListResponse::unmarshalInstance
        );
        verifyValid(unmarshalled);
    }

    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getDistrictFirstNameList_response0.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                DistrictEngFirstNameListResponse::deserializeInstance
        );
        verifyValid(unmarshalled);
    }
}
