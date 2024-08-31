package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel2Test;

class State2Test
        extends AbstractWrappingModel2Test<State2, StateEngListResponse.StateEngList> {

    State2Test() {
        super(State2.class, StateEngListResponse.StateEngList.class);
    }
}