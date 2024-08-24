package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service._RetrieveNewAdressAreaCdSearchAllServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind.__WebBindConstants;

final class __RetrieveNewAdressAreCdSearchAllServiceApiConstants {

    // -----------------------------------------------------------------------------------------------------------------
    static final String TAG = "RetrieveNewAdressAreaCdSearchAllService";

    // -----------------------------------------------------------------------------------------------------------------
    static final String REQUEST_URI =
            __WebBindConstants.REQUEST_URI_API
                    + '/' + _RetrieveNewAdressAreaCdSearchAllServiceConstants.SERVICE_NAME;

    // -----------------------------------------------------------------------------------------------------------------
    static final String REQUEST_URI_SEARCH = REQUEST_URI + "/search";

    // -----------------------------------------------------------------------------------------------------------------
    static final String REQUEST_PARAM_SRCHWRD =
            _RetrieveNewAdressAreaCdSearchAllServiceConstants.REQUEST_PARAM_SRCHWRD;

    // -----------------------------------------------------------------------------------------------------------------
    private __RetrieveNewAdressAreCdSearchAllServiceApiConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
