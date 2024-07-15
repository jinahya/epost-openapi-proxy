package com.github.jinahya.epost.openapi.proxy.misc.lang;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

// TODO: remove; not used!
public final class ReflectionUtils {

    public static Method findMethod(final Class<?> clazz, final Function<? super Class<?>, ? extends Method[]> function,
                                    Predicate<? super Method> predicate)
            throws NoSuchMethodException {
        Objects.requireNonNull(clazz, "clazz is null");
        Objects.requireNonNull(function, "function is null");
        Objects.requireNonNull(predicate, "predicate is null");
        for (var c = clazz; c != null; c = c.getSuperclass()) {
            for (var method : function.apply(c)) {
                if (predicate.test(method)) {
                    return method;
                }
            }
        }
        throw new NoSuchMethodException("unable to find method " + clazz.getName());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ReflectionUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
