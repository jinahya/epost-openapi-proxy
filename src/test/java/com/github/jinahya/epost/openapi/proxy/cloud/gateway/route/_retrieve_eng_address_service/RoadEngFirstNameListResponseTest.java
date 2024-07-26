package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeUtils;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class RoadEngFirstNameListResponseTest
        extends AbstractSelfWrappingResponseTypeTest<RoadEngFirstNameListResponse> {

    RoadEngFirstNameListResponseTest() {
        super(RoadEngFirstNameListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<RoadEngFirstNameListResponse> __equals(
            SingleTypeEqualsVerifierApi<RoadEngFirstNameListResponse> verifierApi) {
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
                        RoadEngFirstNameListResponse.class,
                        new RoadEngFirstNameListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new RoadEngFirstNameListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("b"))
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getRoadFirstNameList_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(RoadEngFirstNameListResponse.class, r)
        );
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getRoadFirstNameList_response0.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.deserializeInstance(RoadEngFirstNameListResponse.class, r).get()
        );
        verifyValid(deserialized);
    }
}
