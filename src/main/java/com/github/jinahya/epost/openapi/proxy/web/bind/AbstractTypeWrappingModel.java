package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public abstract class AbstractTypeWrappingModel<
        SELF extends AbstractTypeWrappingModel<SELF, WRAPPED>,
        WRAPPED extends AbstractType<WRAPPED>>
        extends AbstractWrappingModel<SELF, WRAPPED> {

    @Serial
    private static final long serialVersionUID = 2185796969322484068L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    protected static <T extends AbstractTypeWrappingModel<T, WRAPPED>, WRAPPED extends AbstractType<WRAPPED>>
    T newInstance(final Supplier<? extends T> initializer, final WRAPPED wrapped) {
        final T instance = newInstance(initializer, wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty
    @Valid
    @NotNull
    protected WRAPPED wrapped;
}
