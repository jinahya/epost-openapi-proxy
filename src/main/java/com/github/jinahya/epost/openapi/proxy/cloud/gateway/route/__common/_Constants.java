package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

public final class _Constants {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String BASE_URL_DEVELOPMENT = "http://openapi.epost.go.kr";

    public static final String BASE_URL_PRODUCTION = BASE_URL_DEVELOPMENT;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PARAM_SERVICE_KEY = "serviceKey";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_PARAM_COUNT_PER_PAGE = "countPerPage";

    public static final String REQUEST_PARAM_CURRENT_PAGE = "currentPage";

    // -----------------------------------------------------------------------------------------------------------------
    private _Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
