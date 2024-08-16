package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

@DisplayName("CmmMsgHeader")
@Nested
public final class CmmMsgHeaderTestUtils {

    public static CmmMsgHeader prefabValue1() {
        final var instance = new CmmMsgHeader();
        instance.requestMsgId("a");
        return instance;
    }

    public static CmmMsgHeader prefabValue2() {
        final var instance = new CmmMsgHeader();
        instance.requestMsgId("b");
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private CmmMsgHeaderTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
