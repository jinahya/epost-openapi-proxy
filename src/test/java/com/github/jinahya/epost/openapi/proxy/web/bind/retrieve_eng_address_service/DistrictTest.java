package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModelTest;

class DistrictTest
        extends AbstractTypeWrappingModelTest<District, DistrictEngListResponse.DistrictEngList> {

    DistrictTest() {
        super(District.class, DistrictEngListResponse.DistrictEngList.class);
    }
}