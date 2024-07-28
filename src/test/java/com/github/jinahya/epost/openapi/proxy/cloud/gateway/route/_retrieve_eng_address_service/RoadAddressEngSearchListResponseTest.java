package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeUtils;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RoadAddressEngSearchListResponseTest
        extends AbstractSelfWrappingResponseTypeTest<RoadAddressEngSearchListResponse> {

    RoadAddressEngSearchListResponseTest() {
        super(RoadAddressEngSearchListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<RoadAddressEngSearchListResponse> __equals(
            final SingleTypeEqualsVerifierApi<RoadAddressEngSearchListResponse> verifierApi) {
        return super.__equals(verifierApi)
//                .withPrefabValues(
//                        CmmMsgHeader.class,
//                        new CmmMsgHeader().requestMsgId("a"),
//                        new CmmMsgHeader().requestMsgId("b")
//                )
//                .withPrefabValues(
//                        List.class,
//                        List.of(new Object()),
//                        List.of(new Object())
//                )
//                .withPrefabValues(
//                        Map.class,
//                        Map.of("a", new Object()),
//                        Map.of("b", new Object())
//                )
//                .withPrefabValues(
//                        RoadAddressEngSearchListResponse.class,
//                        new RoadAddressEngSearchListResponse().cmmMsgHeader(
//                                new CmmMsgHeader().requestMsgId("a")),
//                        new RoadAddressEngSearchListResponse().cmmMsgHeader(
//                                new CmmMsgHeader().requestMsgId("b"))
//                )
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
