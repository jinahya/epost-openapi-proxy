package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;

public class _RetrieveEngAddressServiceApiConstants {

    // -----------------------------------------------------------------------------------------------------------------
    static final String REL_STATE = "state";

    static final String REL_STATES = "states";

    static final String REL_CITIES = "cities";

    static final String REL_ROADS = "roads";

    static final String REL_LANDS = "lands";

    static final String REL_DISTRICTS = "districts";

    static final String REL_ADDRESSES = "addresses";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI = "/api/" + _RetrieveEngAddressServiceConstants.SERVICE_ID;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PATH_STATES = REL_STATES;

    public static final String REQUEST_URI_STATES = REQUEST_URI + '/' + PATH_STATES;

    public static final String PATH_NAME_STATE_NAME = "stateName";

    public static final String PATH_VALUE_STATE_NAME = ".+";

    public static final String PATH_TEMPLATE_STATE_NAME =
            '{' + PATH_NAME_STATE_NAME + ':' + PATH_VALUE_STATE_NAME + '}';

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI_CITIES =
//            REQUEST_URI_STATES + "/{" + PATH_NAME_STATE_NAME + "}/" + REL_CITIES;
            REQUEST_URI_STATES + '/' + PATH_TEMPLATE_STATE_NAME + '/' + REL_CITIES;

    public static final String PATH_NAME_CITY_NAME = "cityName";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI_ROADS =
            REQUEST_URI_CITIES + "/{" + PATH_NAME_CITY_NAME + "}/" + REL_ROADS;

    public static final String PATH_NAME_ROAD_NAME = "roadName";

    public static final String REQUEST_URI_ROAD_ADDRESSES =
            REQUEST_URI_ROADS + "/{" + PATH_NAME_ROAD_NAME + "}/" + REL_ADDRESSES;

    static final String REQUEST_URI_LANDS = REQUEST_URI_CITIES + "/{" + PATH_NAME_CITY_NAME + "}/" + REL_LANDS;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI_DISTRICTS =
            REQUEST_URI_CITIES + "/{" + PATH_NAME_CITY_NAME + "}/" + REL_DISTRICTS;

    public static final String PATH_NAME_DISTRICT_NAME = "districtName";

    public static final String REQUEST_URI_DISTRICT_ADDRESSES =
            REQUEST_URI_DISTRICTS + "/{" + PATH_NAME_DISTRICT_NAME + "}/" + REL_ADDRESSES;

    public static final String PATH_NAME_LAND_NAME = "landName";

    static final String REQUEST_URI_LAND_ADDRESSES =
            REQUEST_URI_LANDS + "/{" + PATH_NAME_LAND_NAME + "}/" + REL_ADDRESSES;

    // -----------------------------------------------------------------------------------------------------------------
    private _RetrieveEngAddressServiceApiConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
