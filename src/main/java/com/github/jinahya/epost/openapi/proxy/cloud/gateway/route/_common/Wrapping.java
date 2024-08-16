package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

import java.util.Optional;

// TODO: remove; unused
public interface Wrapping<SELF extends Wrapping<SELF, WRAPPED>, WRAPPED> {

    interface Self<T extends Self<T>>
            extends Wrapping<T, T> {

        @SuppressWarnings({"unchecked"})
        default T get() {
            return Optional.ofNullable(getWrapped()).orElse((T) this);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    default WRAPPED getWrapped() {
        try {
            return WrappingUtils.getWrapped(this);
        } catch (final IllegalAccessException iae) {
            throw new RuntimeException("failed to get wrapped from " + this, iae);
        }
    }

    default void setWrapped(final WRAPPED wrapped) {
        try {
            WrappingUtils.setWrapped(this, wrapped);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException("failed to set wrapper to " + this, iae);
        }
    }
}
