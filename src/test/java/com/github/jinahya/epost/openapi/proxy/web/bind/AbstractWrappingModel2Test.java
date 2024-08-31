package com.github.jinahya.epost.openapi.proxy.web.bind;

import java.util.Objects;

@SuppressWarnings({
        "java:S119"
})
public abstract class AbstractWrappingModel2Test<MODEL extends AbstractWrappingModel2<MODEL, WRAPPED>, WRAPPED>
        extends AbstractModel2Test<MODEL> {

    protected AbstractWrappingModel2Test(final Class<MODEL> modelClass, final Class<WRAPPED> wrappedClass) {
        super(modelClass);
        this.wrappedClass = Objects.requireNonNull(wrappedClass, "wrappedClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<WRAPPED> wrappedClass;
}