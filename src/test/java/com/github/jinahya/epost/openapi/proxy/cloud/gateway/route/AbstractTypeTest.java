package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import io.vavr.CheckedFunction1;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract class for testing a specific subclass of {@link AbstractType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractTypeTest<TYPE extends AbstractType<TYPE>> {

    // -----------------------------------------------------------------------------------------------------------------
    private static ValidatorFactory VALIDATOR_FACTORY = null;

    @BeforeAll
    static void openValidatorFactory() {
        VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    }

    @AfterAll
    static void closeValidatorFactory() {
        VALIDATOR_FACTORY.close();
    }

    protected static <R> R applyValidator(final Function<? super Validator, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        final var validator = VALIDATOR_FACTORY.getValidator();
        return function.apply(validator);
    }

    protected static <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?>... groups) {
        Objects.requireNonNull(object, "object is null");
        Objects.requireNonNull(groups, "groups is null");
        return applyValidator(v -> v.validate(object, groups));

    }

    protected static void verifyValid(final Object object, final Class<?>... groups) {
        assertThat(validate(object, groups))
                .as("constraint violations on %1$s with %2$s", object, groups)
                .isEmpty();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing specified type class.
     *
     * @param typeClass the type class to test.
     */
    protected AbstractTypeTest(final Class<TYPE> typeClass) {
        super();
        this.typeClass = Objects.requireNonNull(typeClass, "typeClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected <R> R applyResource(final String resName, final Function<? super URL, ? extends R> function) {
        final var resource = typeClass.getResource(resName);
        assertThat(resource)
                .as("resource stream for '%1$s", resName)
                .isNotNull();
        return function.apply(resource);
    }

    protected <R> R applyResourceAsStream(final String resName,
                                          final Function<? super InputStream, ? extends R> function)
            throws IOException {
        try (var resource = typeClass.getResourceAsStream(resName)) {
            assertThat(resource)
                    .as("resource stream for '%1$s", resName)
                    .isNotNull();
            return function.apply(resource);
        }
    }

    protected <R> R applyResourceAsStreamChecked(final String resName,
                                                 final CheckedFunction1<? super InputStream, ? extends R> function)
            throws Throwable {
        try (var resource = typeClass.getResourceAsStream(resName)) {
            assertThat(resource)
                    .as("resource stream for '%1$s", resName)
                    .isNotNull();
            return function.apply(resource);
        }
    }

    protected TYPE unmarshalNoNamespacedInstanceFromResource(final String resName)
            throws Throwable {
        return applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.unmarshalNoNamespacedInstance(typeClass, r)
        );
    }

    protected TYPE deserializeInstanceFromResource(final String resName)
            throws Throwable {
        return applyResourceAsStreamChecked(
                resName,
                r -> AbstractTypeUtils.deserializeInstance(typeClass, r)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("equals")
    @Test
    void __equals() {
        __equals(
                EqualsVerifier.simple().forClass(typeClass)
        ).verify();
    }

    protected SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        return verifierApi
                .withIgnoredFields(
                        "unknownAttributes",
                        "unknownElements",
                        "unknownProperties"
                )
                .withPrefabValues(
                        List.class,
                        List.of(new Object()),
                        List.of(new Object())
                )
                .withPrefabValues(
                        Map.class,
                        Map.of("a", new Object()),
                        Map.of("b", new Object())
                )
                ;
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
    protected final TYPE newTypeSpy() {
        return Mockito.spy(newTypeInstance());
    }

    protected final TYPE newTypeInstance() {
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
    protected final Class<TYPE> typeClass;
}
