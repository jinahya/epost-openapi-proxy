package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestTypeTestUtils.AbstractPairedRequestTypeImpl;
import lombok.EqualsAndHashCode;

import java.io.Serial;

public final class AbstractPairedResponseTypeTestUtils {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * .
     *
     * @see AbstractPairedRequestTypeImpl
     */
    @EqualsAndHashCode(callSuper = true)
    static class AbstractPairedResponseTypeImpl
            extends AbstractPairedResponseType<AbstractPairedResponseTypeImpl, AbstractPairedRequestTypeImpl> {

        @Serial
        private static final long serialVersionUID = 8321987003031323673L;

        AbstractPairedResponseTypeImpl() {
            super(AbstractPairedRequestTypeImpl.class);
        }
    }

    public static AbstractPairedResponseType<?, ?> prefabValue1() {
        final var value = new AbstractPairedResponseTypeImpl();
        value.setCmmMsgHeader(CmmMsgHeaderTestUtils.prefabValue1());
        return value;
    }

    public static AbstractPairedResponseType<?, ?> prefabValue2() {
        final var value = new AbstractPairedResponseTypeImpl();
        value.setCmmMsgHeader(CmmMsgHeaderTestUtils.prefabValue2());
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AbstractPairedResponseTypeTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
