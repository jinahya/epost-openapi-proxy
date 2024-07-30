package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import java.util.Optional;

public interface Wrapping<T extends Wrapping<T, U>, U> {

    interface Self<T extends Self<T>>
            extends Wrapping<T, T> {

        @SuppressWarnings({"unchecked"})
        default T get() {
            return Optional.ofNullable(getWrapped()).orElse((T) this);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    default U getWrapped() {
        try {
            return WrappingUtils.getWrapped(this);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException("failed to get wrapped from " + this, iae);
        }
    }

    default void setWrapped(final U wrapped) {
        try {
            WrappingUtils.setWrapped(this, wrapped);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException("failed to set wrapper to " + this, iae);
        }
    }
}
