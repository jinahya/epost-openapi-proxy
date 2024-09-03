package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestTypeTest;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class RoadAddressEngSearchListRequestTest
        extends AbstractPairedRequestTypeTest<RoadAddressEngSearchListRequest, RoadAddressEngSearchListResponse> {

    RoadAddressEngSearchListRequestTest() {
        super(RoadAddressEngSearchListRequest.class, RoadAddressEngSearchListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<RoadAddressEngSearchListRequest> __equals(
            final SingleTypeEqualsVerifierApi<RoadAddressEngSearchListRequest> verifierApi) {
        return super.__equals(verifierApi);
    }
}
