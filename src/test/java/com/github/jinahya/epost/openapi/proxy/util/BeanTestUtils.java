package com.github.jinahya.epost.openapi.proxy.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.function.Supplier;

public final class BeanTestUtils {

    public static <T> void testProperties(final Class<T> type, final T instance)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (Class<?> clazz = type; clazz != null; clazz = clazz.getSuperclass()) {
            final var info = Introspector.getBeanInfo(type);
            for (var descriptor : info.getPropertyDescriptors()) {
                final var reader = descriptor.getReadMethod();
                if (reader == null) {
                    continue;
                }
                if (!reader.canAccess(instance)) {
                    reader.setAccessible(true);
                }
                final var value = reader.invoke(instance);
                final var writer = descriptor.getWriteMethod();
                if (writer == null) {
                    continue;
                }
                if (!writer.canAccess(instance)) {
                    writer.setAccessible(true);
                }
                writer.invoke(instance, value);
            }
        }
    }

    public static <T> void testProperties(final Class<T> type, final Supplier<? extends T> instance)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        testProperties(type, instance.get());
    }

    public static <T> void testProperties(final Class<T> type)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Objects.requireNonNull(type, "type is null");
        testProperties(type, (Supplier<? extends T>) () -> {
            final Constructor<T> constructor;
            try {
                constructor = type.getDeclaredConstructor();
                if (!constructor.canAccess(null)) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException("failed to instantiate " + type, roe);
            }
        });
    }

    private BeanTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
