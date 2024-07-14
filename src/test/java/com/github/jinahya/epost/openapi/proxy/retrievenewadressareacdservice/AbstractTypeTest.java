package com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdservice;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract class for testing a specific subclass of {@link AbstractType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
abstract class AbstractTypeTest<TYPE extends AbstractType> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing specified type class.
     *
     * @param typeClass the type class to test.
     */
    AbstractTypeTest(final Class<TYPE> typeClass) {
        super();
        this.typeClass = Objects.requireNonNull(typeClass, "typeClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("equals")
    @Test
    void __equals() {
        __equals(
                EqualsVerifier.simple().forClass(typeClass)
        ).verify();
    }

    SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        return verifierApi
                .withIgnoredFields(
                        "unknownAttributes",
                        "unknownElements",
                        "unknownProperties"
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("toString()String shouldn't be blank")
    @Test
    void _NotBlank_toString() {
        final TYPE instance = newTypeInstance();
        final String string = instance.toString();
        assertThat(string).isNotBlank();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("setXxx(getXxx())")
    @Test
    void __accessors() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        final var instance = newTypeInstance();
        for (Class<?> clazz = typeClass; clazz.isAssignableFrom(AbstractType.class); clazz = clazz.getSuperclass()) {
            final var info = Introspector.getBeanInfo(typeClass);
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
    final TYPE newTypeSpy() {
        return Mockito.spy(newTypeInstance());
    }

    final TYPE newTypeInstance() {
        try {
            final var constructor = typeClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<TYPE> typeClass;
}
