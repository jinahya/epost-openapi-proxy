package com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdsearchallservice;

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
