package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModelTest;

class RoadAddressTest
        extends AbstractTypeWrappingModelTest<RoadAddress, RoadAddressEngSearchListResponse.RoadAddressEngSearchList> {

    RoadAddressTest() {
        super(RoadAddress.class, RoadAddressEngSearchListResponse.RoadAddressEngSearchList.class);
    }
}