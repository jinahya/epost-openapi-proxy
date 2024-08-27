package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestTypeTestUtils.AbstractPairedRequestTypeImpl;
import lombok.EqualsAndHashCode;

import java.io.Serial;

public final class AbstractTypeTestUtils {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * .
     *
     * @see AbstractPairedRequestTypeImpl
     */
    @EqualsAndHashCode(callSuper = true)
    static class AbstractTypeImpl
            extends AbstractType<AbstractTypeImpl> {

        @Serial
        private static final long serialVersionUID = -4079086730255098683L;

        AbstractTypeImpl(int i) {
            super();
            this.i = i;
        }

        private final int i;
    }

    public static AbstractType<?> prefabValue1() {
        final var value = new AbstractTypeImpl(1);
        return value;
    }

    public static AbstractType<?> prefabValue2() {
        final var value = new AbstractTypeImpl(2);
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AbstractTypeTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
