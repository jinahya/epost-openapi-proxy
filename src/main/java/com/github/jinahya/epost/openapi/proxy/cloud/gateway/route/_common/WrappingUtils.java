package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

import com.github.jinahya.epost.openapi.proxy._misc.invoke.LookupUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

final class WrappingUtils {

    static final String NAME = "wrapped";

    // -----------------------------------------------------------------------------------------------------------------
    private static final Map<Class<?>, Field> FIELDS = new HashMap<>();

    private static Field field(final Class<?> clazz, final Object wrapper) {
        Objects.requireNonNull(clazz, "clazz is null");
        return FIELDS.computeIfAbsent(clazz, k -> {
            final var field = ReflectionUtils.findField(k, NAME);
            if (field == null) {
                throw new IllegalArgumentException("no '" + NAME + "' field in " + clazz);
            }
            if (!field.canAccess(wrapper)) {
                field.setAccessible(true);
            }
            return field;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final Map<Class<?>, VarHandle> HANDLES = new HashMap<>();

    private static VarHandle handle(final Class<?> clazz, final Object wrapper) {
        Objects.requireNonNull(clazz, "clazz is null");
        return HANDLES.computeIfAbsent(clazz, k -> {
            final var field = field(k, wrapper);
            try {
                return LookupUtils.privateLookup(k).unreflectVarHandle(field);
            } catch (final IllegalAccessException iae) {
                throw new RuntimeException("failed to unreflect" + field, iae);
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    static <T> T getWrapped(final Object wrapper) throws IllegalAccessException {
        Objects.requireNonNull(wrapper, "wrapper is null");
        if (true) {
            final var handle = handle(wrapper.getClass(), wrapper);
            @SuppressWarnings({"unchecked"})
            final var wrapped = (T) handle.get(wrapper);
            return wrapped;
        }
        final var field = field(wrapper.getClass(), wrapper);
        if (!field.canAccess(wrapper)) {
            field.setAccessible(true);
        }
        @SuppressWarnings({"unchecked"})
        final var wrapped = (T) field.get(wrapper);
        return wrapped;
    }

    static void setWrapped(final Object wrapper, final Object wrapped) throws IllegalAccessException {
        Objects.requireNonNull(wrapper, "wrapper is null");
        if (true) {
            final var handle = handle(wrapper.getClass(), wrapper);
            handle.set(wrapper, wrapped);
            return;
        }
        final var field = field(wrapper.getClass(), wrapper);
        if (!field.canAccess(wrapper)) {
            field.setAccessible(true);
        }
        field.set(wrapper, wrapped);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private WrappingUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
