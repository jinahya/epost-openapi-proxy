package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestTypeTest;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class AreaCodeInfoRequestTest
        extends AbstractPairedRequestTypeTest<AreaCodeInfoRequest, AreaCodeInfoResponse> {

    AreaCodeInfoRequestTest() {
        super(AreaCodeInfoRequest.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<AreaCodeInfoRequest> __equals(
            final SingleTypeEqualsVerifierApi<AreaCodeInfoRequest> verifierApi) {
        return super.__equals(verifierApi);
    }
}
