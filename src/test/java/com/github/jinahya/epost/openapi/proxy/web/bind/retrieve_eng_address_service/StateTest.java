package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModelTest;

class StateTest
        extends AbstractTypeWrappingModelTest<State, StateEngListResponse.StateEngList> {

    StateTest() {
        super(State.class, StateEngListResponse.StateEngList.class);
    }
}