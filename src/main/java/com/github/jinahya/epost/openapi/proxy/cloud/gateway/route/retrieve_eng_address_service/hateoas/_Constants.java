package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;

class _Constants {

    // -----------------------------------------------------------------------------------------------------------------
    static final String REL_STATE = "state";

    static final String REL_STATES = "states";

    static final String REL_CITIES = "cities";

    static final String REL_ROADS = "roads";

    static final String REL_LANDS = "lands";

    static final String REL_ADDRESSES = "addresses";

    // -----------------------------------------------------------------------------------------------------------------
    static final String REQUEST_URI = "/api/" + _RetrieveEngAddressServiceConstants.SERVICE_ID;

    static final String REQUEST_URI_STATES = REQUEST_URI + '/' + REL_STATES;

    static final String PATH_NAME_STATE_NAME = "stateName";

    // -----------------------------------------------------------------------------------------------------------------
    static final String REQUEST_URI_CITIES = REQUEST_URI_STATES + "/{" + PATH_NAME_STATE_NAME + "}/" + REL_CITIES;

    static final String PATH_NAME_CITY_NAME = "cityName";

    static final String REQUEST_URI_ROADS = REQUEST_URI_CITIES + "/{" + PATH_NAME_CITY_NAME + "}/" + REL_ROADS;

    // -----------------------------------------------------------------------------------------------------------------
    static final String PATH_NAME_ROAD_NAME = "roadName";

    static final String REQUEST_URI_ROAD_ADDRESSES =
            REQUEST_URI_ROADS + "/{" + PATH_NAME_ROAD_NAME + "}/" + REL_ADDRESSES;

    static final String REQUEST_URI_LANDS = REQUEST_URI_CITIES + "/{" + PATH_NAME_CITY_NAME + "}/" + REL_LANDS;

    // -----------------------------------------------------------------------------------------------------------------
    static final String PATH_NAME_LAND_NAME = "landName";

    static final String REQUEST_URI_LAND_ADDRESSES =
            REQUEST_URI_LANDS + "/{" + PATH_NAME_LAND_NAME + "}/" + REL_ADDRESSES;

    // -----------------------------------------------------------------------------------------------------------------
    private _Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
