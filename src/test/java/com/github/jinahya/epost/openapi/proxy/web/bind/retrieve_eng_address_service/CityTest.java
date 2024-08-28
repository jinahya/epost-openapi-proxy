package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModelTest;

class CityTest
        extends AbstractTypeWrappingModelTest<City, CityEngListResponse.CityEngList> {

    CityTest() {
        super(City.class, CityEngListResponse.CityEngList.class);
    }
}