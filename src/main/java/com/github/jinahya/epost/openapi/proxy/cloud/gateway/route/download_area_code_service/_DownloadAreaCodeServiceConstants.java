package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common._Constants;

public final class _DownloadAreaCodeServiceConstants {

    public static final String SERVICE_NAME = "downloadAreaCodeService";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_URI =
            _Constants.REQUEST_URI_POSTAL
                    + '/' + SERVICE_NAME
                    + '/' + SERVICE_NAME;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PATH_GET_AREA_CODE_INFO = "getAreaCodeInfo";

    public static final String REQUEST_URI_GET_AREA_CODE_INFO = REQUEST_URI + '/' + PATH_GET_AREA_CODE_INFO;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PARAM_NAME_DWLDSE = "dwldSe";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private _DownloadAreaCodeServiceConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
