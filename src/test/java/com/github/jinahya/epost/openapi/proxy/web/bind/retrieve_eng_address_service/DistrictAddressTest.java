package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModelTest;

class DistrictAddressTest
        extends AbstractWrappingModelTest<DistrictAddress, LandAddressEngSearchListResponse.LandAddressEngSearchList> {

    DistrictAddressTest() {
        super(DistrictAddress.class, LandAddressEngSearchListResponse.LandAddressEngSearchList.class);
    }
}