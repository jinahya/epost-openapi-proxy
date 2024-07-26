package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class CityEngListResponseTest
        extends AbstractSelfWrappingResponseTypeTest<CityEngListResponse> {

    CityEngListResponseTest() {
        super(CityEngListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<CityEngListResponse> __equals(
            SingleTypeEqualsVerifierApi<CityEngListResponse> verifierApi) {
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
                        CityEngListResponse.class,
                        new CityEngListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new CityEngListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("b"))
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getCityList_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                CityEngListResponse::unmarshalInstance
        );
        verifyValid(unmarshalled);
    }

    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getCityList_response0.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                CityEngListResponse::deserializeInstance
        );
        verifyValid(unmarshalled);
    }
}
