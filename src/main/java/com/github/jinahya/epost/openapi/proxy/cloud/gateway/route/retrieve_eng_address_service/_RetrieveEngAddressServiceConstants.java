package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

/**
 * Constants for the {@value #ROUTE_ID} route.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class _RetrieveEngAddressServiceConstants {

    /**
     * The {@code id} of the route to the {@value #REQUEST_URI}. The value is {@value}.
     */
    public static final String ROUTE_ID = "retrieve_eng_address_service";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String SERVICE_ID = "retrieveEngAddressService";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI = "/postal/" + SERVICE_ID + '/' + SERVICE_ID;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PATH_GET_STATE_LIST = "getStateList";

    public static final String REQUEST_URI_GET_STATE_LIST = REQUEST_URI + '/' + PATH_GET_STATE_LIST;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the operation of {@value}.
     */
    public static final String PATH_GET_CITY_LIST = "getCityList";

    public static final String REQUEST_URI_GET_CITY_LIST = REQUEST_URI + '/' + PATH_GET_CITY_LIST;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI_GET_ROAD_FIRST_NAME_LIST = REQUEST_URI + "/getRoadFirstNameList";

    public static final String REQUEST_URI_GET_ROAD_NAME_LIST = REQUEST_URI + "/getRoadNameList";

    public static final String REQUEST_URI_GET_ROAD_ADDRESS_SEARCH = REQUEST_URI + "/getRoadAddressSearch";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI_GET_DISTRICT_FIRST_NAME_LIST = REQUEST_URI + "/getDistrictFirstNameList";

    public static final String REQUEST_URI_GET_DISTRICT_NAME_LIST = REQUEST_URI + "/getDistrictNameList";

    public static final String REQUEST_URI_GET_LAND_ADDRESS_SEARCH = REQUEST_URI + "/getLandAddressSearch";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A parameter name of {@value}.
     */
    public static final String PARAM_STATE_ENG_NAME = "stateEngName";

    /**
     * A parameter name of {@value}.
     */
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
