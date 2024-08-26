package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModelTest;

class StateTest
        extends AbstractWrappingModelTest<State, StateEngListResponse.StateEngList> {

    StateTest() {
        super(State.class, StateEngListResponse.StateEngList.class);
    }
}