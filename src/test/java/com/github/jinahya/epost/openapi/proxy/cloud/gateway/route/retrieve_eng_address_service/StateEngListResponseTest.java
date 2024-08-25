package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseElementTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractTypeUtils;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class StateEngListResponseTest
        extends AbstractPairedResponseTypeTest<StateEngListResponse, StateEngListRequest> {

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class StateEngListTest
//            extends AbstractTypeTest<StateEngListResponse.StateEngList> {
            extends AbstractResponseElementTypeTest<StateEngListResponse.StateEngList, StateEngListResponse> {

        StateEngListTest() {
            super(StateEngListResponse.StateEngList.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<StateEngListResponse.StateEngList> __equals(
                final SingleTypeEqualsVerifierApi<StateEngListResponse.StateEngList> verifierApi) {
            return super.__equals(verifierApi)
                    .withIgnoredFields("parent")
                    .withPrefabValues(
                            AbstractResponseType.class,
                            AbstractResponseTypeTestUtils.prefabValue1(),
                            AbstractResponseTypeTestUtils.prefabValue2())
                    ;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    StateEngListResponseTest() {
        super(StateEngListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<StateEngListResponse> __equals(
            final SingleTypeEqualsVerifierApi<StateEngListResponse> verifierApi) {
        return super.__equals(verifierApi)
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getStateList_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(StateEngListResponse.class, r)
        );
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "getStateList_response0.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        // -------------------------------------------------------------------------------------------------------- when
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.deserializeInstance(StateEngListResponse.class, r).get()
        );
        // -------------------------------------------------------------------------------------------------------- then
        verifyValid(deserialized);
    }
}
