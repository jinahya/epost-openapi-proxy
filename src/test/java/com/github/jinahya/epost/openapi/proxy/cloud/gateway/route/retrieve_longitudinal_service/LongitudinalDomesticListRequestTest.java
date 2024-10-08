package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_longitudinal_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestTypeTest;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class LongitudinalDomesticListRequestTest
        extends AbstractPairedRequestTypeTest<LongitudinalDomesticListRequest, LongitudinalDomesticListResponse> {

    LongitudinalDomesticListRequestTest() {
        super(LongitudinalDomesticListRequest.class, LongitudinalDomesticListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected SingleTypeEqualsVerifierApi<LongitudinalDomesticListRequest> __equals(
            final SingleTypeEqualsVerifierApi<LongitudinalDomesticListRequest> verifierApi) {
        return super.__equals(verifierApi);
    }
}
