package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

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
    public static final String PARAMETER_DESCRIPTION_COUNT_PER_PAGE = "페이지당 출력 개수";

    public static final String PARAMETER_DESCRIPTION_CURRENT_PAGE = "출력될 페이지 번호";

    // -----------------------------------------------------------------------------------------------------------------
    private _Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
