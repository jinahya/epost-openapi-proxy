package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_longitudinal_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractTypeUtils;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LongitudinalDomesticListResponseTest
        extends AbstractPairedResponseTypeTest<LongitudinalDomesticListResponse, LongitudinalDomesticListRequest> {

    LongitudinalDomesticListResponseTest() {
        super(LongitudinalDomesticListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<LongitudinalDomesticListResponse> __equals(
            final SingleTypeEqualsVerifierApi<LongitudinalDomesticListResponse> verifierApi) {
        return super.__equals(verifierApi)
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "getLongitudinalDomesticList_response0.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(LongitudinalDomesticListResponse.class, r)
        );
        verifyValid(unmarshalled);
    }
}
