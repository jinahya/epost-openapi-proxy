package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractSelfWrappingResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.CmmMsgHeader;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class LandEngListResponseTest
        extends AbstractSelfWrappingResponseTypeTest<LandAddressEngSearchListResponse> {

    LandEngListResponseTest() {
        super(LandAddressEngSearchListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<LandAddressEngSearchListResponse> __equals(
            SingleTypeEqualsVerifierApi<LandAddressEngSearchListResponse> verifierApi) {
        return super.__equals(verifierApi)
                .withPrefabValues(
                        CmmMsgHeader.class,
                        new CmmMsgHeader().requestMsgId("a"),
                        new CmmMsgHeader().requestMsgId("b")
                )
                .withPrefabValues(
                        List.class,
                        List.of(new Object()),
                        List.of(new Object())
                )
                .withPrefabValues(
                        Map.class,
                        Map.of("a", new Object()),
                        Map.of("b", new Object())
                )
                .withPrefabValues(
                        LandAddressEngSearchListResponse.class,
                        new LandAddressEngSearchListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new LandAddressEngSearchListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("b"))
                );
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
                LandAddressEngSearchListResponse::unmarshalInstance
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
                LandAddressEngSearchListResponse::deserializeInstance
        );
        verifyValid(unmarshalled);
    }
}
