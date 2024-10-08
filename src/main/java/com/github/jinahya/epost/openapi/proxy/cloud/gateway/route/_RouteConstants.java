package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

@SuppressWarnings({
        "java:S101" // class _Route...
})
public final class _RouteConstants {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String BASE_URL_DEVELOPMENT = "http://openapi.epost.go.kr";

    public static final String BASE_URL_PRODUCTION = BASE_URL_DEVELOPMENT;

    /**
     * The request URI of {@value}.
     */
    public static final String REQUEST_URI_POSTAL = "/postal";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PARAM_SERVICE_KEY = "serviceKey";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REQUEST_PARAM_COUNT_PER_PAGE = "countPerPage";

    public static final String REQUEST_PARAM_CURRENT_PAGE = "currentPage";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PARAMETER_DESCRIPTION_COUNT_PER_PAGE = "페이지당 출력 개수";

    public static final String PARAMETER_DESCRIPTION_CURRENT_PAGE = "출력될 페이지 번호";

    // -----------------------------------------------------------------------------------------------------------------
    private _RouteConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
