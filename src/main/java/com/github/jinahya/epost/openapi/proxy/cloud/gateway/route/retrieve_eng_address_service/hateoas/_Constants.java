package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;

class _Constants {

    static final String REQUEST_URI = "/api/" + _RetrieveEngAddressServiceConstants.SERVICE_ID;

    static final String REQUEST_URI_STATES = REQUEST_URI + "/states";

    static final String REQUEST_URI_CITIES = REQUEST_URI_STATES + "/cities";

    // -----------------------------------------------------------------------------------------------------------------
    static final String REL_STATE = "state";

    static final String REL_CITIES = "cities";

    // -----------------------------------------------------------------------------------------------------------------
    private _Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
