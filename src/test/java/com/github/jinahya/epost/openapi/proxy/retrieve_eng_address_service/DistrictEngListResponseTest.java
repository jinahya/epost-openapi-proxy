package com.github.jinahya.epost.openapi.proxy.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class DistrictEngListResponseTest
        extends AbstractSelfWrappingResponseTypeTest<DistrictEngListResponse> {

    DistrictEngListResponseTest() {
        super(DistrictEngListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<DistrictEngListResponse> __equals(
            SingleTypeEqualsVerifierApi<DistrictEngListResponse> verifierApi) {
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
                        DistrictEngListResponse.class,
                        new DistrictEngListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new DistrictEngListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("b"))
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getDistrictNameList_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                DistrictEngListResponse::unmarshalInstance
        );
        verifyValid(unmarshalled);
    }

    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getDistrictNameList_response0.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                DistrictEngListResponse::deserializeInstance
        );
        verifyValid(unmarshalled);
    }
}
