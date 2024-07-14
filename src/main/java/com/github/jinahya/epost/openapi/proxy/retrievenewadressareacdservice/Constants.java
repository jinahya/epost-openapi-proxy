package com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdservice;

final class Constants {

    private static final String REQUEST_URI = """
            /postal
            /retrieveNewAdressAreaCdService
            /retrieveNewAdressAreaCdService
            /getNewAddressListAreaCd""";

    public static String requestUri() {
        return REQUEST_URI.replaceAll("\\n", "");
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
