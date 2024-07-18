package com.github.jinahya.epost.openapi.proxy.download_area_code_service;

final class Constants {

    private static final String REQUEST_URI_GET_AREA_CODE_INFO = """
            /postal
            /downloadAreaCodeService
            /downloadAreaCodeService
            /getAreaCodeInfo""";

    public static String requestUriGetAreaCodeInfo() {
        return REQUEST_URI_GET_AREA_CODE_INFO.replaceAll("\\n", "");
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
