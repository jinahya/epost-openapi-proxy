package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common._Constants;

public final class _RetrieveNewAdressAreaCdSearchAllServiceConstants {

    /**
     * The service name of {@value}.
     */
    public static final String SERVICE_NAME = "retrieveNewAdressAreaCdSearchAllService";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The operation of {@value}.
     */
    public static final String OPERATION_GET_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL = "getNewAddressListAreaCdSearchAll";

    /**
     * The result URI for {@value #OPERATION_GET_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL} operation. The value is {@value}.
     */
    public static final String REQUEST_URI_GET_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL =
            _Constants.REQUEST_URI_POSTAL
                    + '/' + SERVICE_NAME
                    + '/' + SERVICE_NAME
                    + '/' + OPERATION_GET_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_PARAM_SRCHWRD = "srchwrd";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private _RetrieveNewAdressAreaCdSearchAllServiceConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
