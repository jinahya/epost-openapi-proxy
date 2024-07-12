package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

final class Constants {

    private static final String URI = """
            /postal
            /retrieveNewAdressAreaCdService
            /retrieveNewAdressAreaCdService
            /getNewAddressListAreaCd""";

    public static final String uri() {
        return URI.replaceAll("\\n", "");
    }

    private Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
