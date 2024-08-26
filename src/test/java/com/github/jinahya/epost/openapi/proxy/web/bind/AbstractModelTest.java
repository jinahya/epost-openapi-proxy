package com.github.jinahya.epost.openapi.proxy.web.bind;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@Slf4j
@SuppressWarnings({
        "java:S119"
})
public abstract class AbstractModelTest<MODEL extends AbstractModel<MODEL>> {

    protected AbstractModelTest(final Class<MODEL> modelClass) {
        super();
        this.modelClass = Objects.requireNonNull(modelClass, "modelClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("setXxx(getXxx())")
    @Test
    void __accessors() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        final var instance = newModelInstance();
        for (Class<?> clazz = modelClass; clazz.isAssignableFrom(AbstractModel.class); clazz = clazz.getSuperclass()) {
            final var info = Introspector.getBeanInfo(modelClass);
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

    // ------------------------------------------------------------------------------------------------------- typeClass

    /**
     * Creates a {@link Mockito#spy(Object) spy} of a new instance of {@link #modelClass} class.
     *
     * @return a {@link Mockito#spy(Object) spy} of a new instance of {@link #modelClass} class.
     */
    protected final MODEL newModelSpy() {
        return Mockito.spy(newModelInstance());
    }

    /**
     * Creates a new instance of {@link #modelClass} class.
     *
     * @return a new instance of {@link #modelClass} class.
     */
    protected final MODEL newModelInstance() {
        try {
            final var constructor = modelClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The model class to test.
     */
    protected final Class<MODEL> modelClass;
}