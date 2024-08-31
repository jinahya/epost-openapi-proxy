package com.github.jinahya.epost.openapi.proxy.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public final class BeanTestUtils {

    public <T> void testProperties(final Class<T> type, final T instance)
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

    public <T> void testProperties(final Class<T> type, final Supplier<? extends T> instance)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        testProperties(type, instance.get());
    }

    private BeanTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
