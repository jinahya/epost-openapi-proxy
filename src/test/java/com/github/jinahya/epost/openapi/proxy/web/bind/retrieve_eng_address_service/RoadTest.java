package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModelTest;

class RoadTest
        extends AbstractTypeWrappingModelTest<Road, RoadEngListResponse.RoadEngList> {

    RoadTest() {
        super(Road.class, RoadEngListResponse.RoadEngList.class);
    }
}