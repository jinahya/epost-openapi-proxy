package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractPairedResponseTypeTestUtils.AbstractPairedResponseTypeImpl;
import lombok.EqualsAndHashCode;

import java.io.Serial;

public final class AbstractPairedRequestTypeTestUtils {

    /**
     * @see AbstractPairedResponseTypeImpl
     */
    @EqualsAndHashCode(callSuper = true)
    static class AbstractPairedRequestTypeImpl
            extends AbstractPairedRequestType<
            AbstractPairedRequestTypeImpl,
            AbstractPairedResponseTypeImpl> {

        @Serial
        private static final long serialVersionUID = -6161773370452148491L;

        AbstractPairedRequestTypeImpl() {
            super(AbstractPairedResponseTypeImpl.class);
        }
    }

    public static AbstractPairedRequestType<?, ?> prefabValue1() {
        final var value = new AbstractPairedRequestTypeImpl();
        value.setServiceKey("a");
        return value;
    }

    public static AbstractPairedRequestType<?, ?> prefabValue2() {
        final var value = new AbstractPairedRequestTypeImpl();
        value.setServiceKey("b");
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AbstractPairedRequestTypeTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
