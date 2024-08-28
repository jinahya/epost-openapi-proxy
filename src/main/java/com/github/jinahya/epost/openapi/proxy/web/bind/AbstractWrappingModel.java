package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.function.Supplier;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings({
        "java:S119" // <SELF ...>
})
public abstract class AbstractWrappingModel<SELF extends AbstractWrappingModel<SELF, WRAPPED>, WRAPPED>
        extends AbstractModel<SELF> {

    @Serial
    private static final long serialVersionUID = -1037381213496779313L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    protected static <T extends AbstractWrappingModel<T, WRAPPED>, WRAPPED>
    T newInstance(final Supplier<? extends T> initializer, final WRAPPED wrapped) {
        final T instance = newInstance(initializer);
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty(required = false)
    @Valid
    protected WRAPPED wrapped;
}
