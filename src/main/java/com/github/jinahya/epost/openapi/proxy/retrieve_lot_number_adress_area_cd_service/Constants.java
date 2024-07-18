package com.github.jinahya.epost.openapi.proxy.retrieve_lot_number_adress_area_cd_service;

final class Constants {

    private static final String REQUEST_URI = """
            /postal
            /retrieveNewAdressAreaCdSearchAllService
            /retrieveNewAdressAreaCdSearchAllService
            /getNewAddressListAreaCdSearchAll""";

    public static String requestUri() {
        return REQUEST_URI.replaceAll("\\n", "");
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
