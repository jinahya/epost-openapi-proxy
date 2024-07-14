package com.github.jinahya.epost.openapi.proxy.misc.jackson.databind;

import com.fasterxml.jackson.databind.ObjectReader;

import java.util.Objects;

public final class ObjectReaderUtils {

    public static <T, SOURCE> T readValue(final ObjectReader objectReader, final Class<SOURCE> sourceClass,
                                          final SOURCE sourceInstance) {
        for (var method : ObjectReader.class.getMethods()) {
            if (!method.getName().equals("readValue")) {
                continue;
            }
            final var parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            if (!parameterTypes[0].isAssignableFrom(sourceClass)) {
                continue;
            }
            try {
                @SuppressWarnings({"unchecked"})
                final var result = (T) method.invoke(objectReader, sourceInstance);
                return result;
            } catch (ReflectiveOperationException roe) {
                throw new RuntimeException("unable to createJSONStreamReader for " + sourceInstance, roe);
            }
        }
        throw new IllegalArgumentException("unable to createJSONStreamReader for " + sourceInstance);
    }

    private static <T, SOURCE> T readValueHelper(final ObjectReader objectReader, final Class<SOURCE> sourceClass,
                                                 final Object sourceInstance) {
        Objects.requireNonNull(sourceClass, "sourceClass is null");
        return readValue(objectReader, sourceClass, sourceClass.cast(sourceInstance));
    }

    public static <T> T readValue(final ObjectReader objectReader, final Object sourceInstance) {
        Objects.requireNonNull(sourceInstance, "sourceInstance is null");
        return readValueHelper(objectReader, sourceInstance.getClass(), sourceInstance);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ObjectReaderUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
