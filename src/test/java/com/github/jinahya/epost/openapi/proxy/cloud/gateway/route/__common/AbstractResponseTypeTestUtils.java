package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import lombok.EqualsAndHashCode;

import java.io.Serial;

public final class AbstractResponseTypeTestUtils {

    @EqualsAndHashCode(callSuper = true)
    private static class AbstractResponseTypeImpl
            extends AbstractResponseType<AbstractResponseTypeImpl> {

        @Serial
        private static final long serialVersionUID = 552357251707240165L;
    }

    public static AbstractResponseType<?> prefabValue1() {
        final var value = new AbstractResponseTypeImpl();
        value.setCmmMsgHeader(new CmmMsgHeader().requestMsgId("a"));
        return value;
    }

    public static AbstractResponseType<?> prefabValue2() {
        final var value = new AbstractResponseTypeImpl();
        value.setCmmMsgHeader(new CmmMsgHeader().requestMsgId("b"));
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AbstractResponseTypeTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
