package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

public final class _RetrieveEngAddressServiceConstants {

    public static final String ROUTE_ID = "_retrieve_eng_address_service";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String SERVICE_ID = "retrieveEngAddressService";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String REQUEST_URI = "/postal/" + SERVICE_ID + '/' + SERVICE_ID;

    public static final String REQUEST_URI_GET_STATE_LIST = REQUEST_URI + "/getStateList";

    public static final String REQUEST_URI_GET_CITY_LIST = REQUEST_URI + "/getCityList";

    public static final String REQUEST_URI_GET_ROAD_FIRST_NAME_LIST = REQUEST_URI + "/getRoadFirstNameList";

    public static final String REQUEST_URI_GET_ROAD_NAME_LIST = REQUEST_URI + "/getRoadNameList";

    public static final String REQUEST_URI_GET_ROAD_ADDRESS_SEARCH = REQUEST_URI + "/getRoadAddressSearch";

    public static final String REQUEST_URI_GET_DISTRICT_FIRST_NAME_LIST = REQUEST_URI + "/getDistrictFirstNameList";

    public static final String REQUEST_URI_GET_DISTRICT_NAME_LIST = REQUEST_URI + "/getDistrictNameList";

    public static final String REQUEST_URI_GET_LAND_ADDRESS_SEARCH = REQUEST_URI + "/getLandAddressSearch";

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated
    public static final String PARAM_STATE_ENG_NM = "stateEngNm";

    public static final String PARAM_STATE_ENG_NAME = "stateEngName";

    public static final String PARAM_CITY_ENG_NAME = "cityEngName";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PARAM_ROAD_ENG_FIRST_NAME = "roadEngFirstName";

    public static final String PARAM_ROAD_ENG_NAME = "roadEngName";

    public static final String PARAM_KEYWORD = "keyword";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PARAM_DISTRICT_ENG_FIRST_NAME = "districtEngFirstName";

    public static final String PARAM_DISTRICT_ENG_NAME = "districtEngName";

    // -----------------------------------------------------------------------------------------------------------------
    private _RetrieveEngAddressServiceConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}