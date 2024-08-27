package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModelTest;

class DistrictTest
        extends AbstractWrappingModelTest<District, DistrictEngListResponse.DistrictEngList> {

    DistrictTest() {
        super(District.class, DistrictEngListResponse.DistrictEngList.class);
    }
}