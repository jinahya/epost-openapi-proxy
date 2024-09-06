package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind.__WebBindConstants;

@SuppressWarnings({
        "java:S125"
})
final class _RetrieveEngAddressServiceApiConstants {

    // -----------------------------------------------------------------------------------------------------------------
    static final String REL_STATES = "states";

    static final String REL_STATE = "state";

    static final String REL_CITIES = "cities";

    static final String REL_CITY = "city";

    static final String REL_ROADS = "roads";

    static final String REL_ROAD = "road";

    static final String REL_LANDS = "lands";

    static final String REL_LAND = "land";

    static final String REL_DISTRICTS = "districts";

    static final String REL_DISTRICT = "district";

    static final String REL_ADDRESSES = "addresses";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI = __WebBindConstants.REQUEST_URI_API
            + '/' + _RetrieveEngAddressServiceConstants.SERVICE_ID;

    // --------------------------------------------------------------------------------------------------------- /states
    public static final String PATH_STATES = REL_STATES;

    public static final String REQUEST_URI_STATES = REQUEST_URI + '/' + PATH_STATES;

    public static final String PATH_NAME_STATE_NAME = "stateName";

    public static final String PATH_VALUE_STATE_NAME = ".+";

    public static final String PATH_TEMPLATE_STATE_NAME =
            '{' + PATH_NAME_STATE_NAME + ':' + PATH_VALUE_STATE_NAME + '}';

    public static final String REQUEST_URI_STATE = REQUEST_URI_STATES + '/' + PATH_TEMPLATE_STATE_NAME;

    // --------------------------------------------------------------------------------------------------------- /cities
    public static final String REQUEST_URI_CITIES = REQUEST_URI_STATE + '/' + REL_CITIES;

    public static final String PATH_NAME_CITY_NAME = "cityName";

    public static final String PATH_VALUE_CITY_NAME = ".+";

    public static final String PATH_TEMPLATE_CITY_NAME = '{' + PATH_NAME_CITY_NAME + ':' + PATH_VALUE_CITY_NAME + '}';

    public static final String REQUEST_URI_CITY = REQUEST_URI_CITIES + '/' + PATH_TEMPLATE_CITY_NAME;

    // ----------------------------------------------------------------- /states/{stateName}/cities/{cityName}/districts
    public static final String REQUEST_URI_DISTRICTS =
            REQUEST_URI_CITIES + "/{" + PATH_NAME_CITY_NAME + "}/" + REL_DISTRICTS;

    // -------------------------------------------------- /states/{stateName}/cities/{cityName}/districts/{districtName}
    public static final String PATH_NAME_DISTRICT_NAME = "districtName";

    public static final String PATH_VALUE_DISTRICT_NAME = ".+";

    public static final String PATH_TEMPlATE_DISTRICT_NAME =
            '{' + PATH_NAME_DISTRICT_NAME + ':' + PATH_VALUE_DISTRICT_NAME + '}';

    public static final String REQUEST_URI_DISTRICT = REQUEST_URI_DISTRICTS + '/' + PATH_TEMPlATE_DISTRICT_NAME;

    // ---------------------------------------- /states/{stateName}/cities/{cityName}/districts/{districtName}/addresses
    public static final String REQUEST_URI_DISTRICT_ADDRESSES = REQUEST_URI_DISTRICT + '/' + REL_ADDRESSES;

    public static final String PATH_NAME_LAND_NAME = "landName";

    static final String REQUEST_URI_LANDS = REQUEST_URI_CITIES + "/{" + PATH_NAME_CITY_NAME + "}/" + REL_LANDS;

    static final String REQUEST_URI_LAND_ADDRESSES =
            REQUEST_URI_LANDS + "/{" + PATH_NAME_LAND_NAME + "}/" + REL_ADDRESSES;

    // --------------------------------------------------------------------- /states/{stateName}/cities/{cityName}/roads
    public static final String REQUEST_URI_ROADS = REQUEST_URI_CITY + '/' + REL_ROADS;

    // ---------------------------------------------------------- /states/{stateName}/cities/{cityName}/roads/{roadName}
    public static final String PATH_NAME_ROAD_NAME = "roadName";

    public static final String PATH_VALUE_ROAD_NAME = ".+";

    public static final String PATH_TEMPLATE_ROAD_NAME = '{' + PATH_NAME_ROAD_NAME + ':' + PATH_VALUE_ROAD_NAME + '}';

    public static final String REQUEST_URI_ROAD = REQUEST_URI_ROADS + '/' + PATH_TEMPLATE_ROAD_NAME;

    // ------------------------------------------------ /states/{stateName}/cities/{cityName}/roads/{roadName}/addresses
    public static final String REQUEST_URI_ROAD_ADDRESSES =
            REQUEST_URI_ROADS + "/{" + PATH_NAME_ROAD_NAME + "}/" + REL_ADDRESSES;

    // -----------------------------------------------------------------------------------------------------------------
    static final String TAG = "RetrieveEngAddressService";

    // -----------------------------------------------------------------------------------------------------------------
    private _RetrieveEngAddressServiceApiConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
