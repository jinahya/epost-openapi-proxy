package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractType;

import java.util.Objects;

@SuppressWarnings({
        "java:S119" // <MODEL ...>
})
public abstract class AbstractWrappingModelTest<
        MODEL extends AbstractWrappingModel<MODEL, WRAPPED>,
        WRAPPED extends AbstractType<WRAPPED>>
        extends AbstractModelTest<MODEL> {

    protected AbstractWrappingModelTest(final Class<MODEL> modelClass, final Class<WRAPPED> wrappedClass) {
        super(modelClass);
        this.wrappedClass = Objects.requireNonNull(wrappedClass, "wrappedClass is null");
    }

    // ---------------------------------------------------------------------------------------------------- wrappedClass

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<WRAPPED> wrappedClass;
}