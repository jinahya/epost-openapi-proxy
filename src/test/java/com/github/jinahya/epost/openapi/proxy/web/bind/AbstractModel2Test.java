package com.github.jinahya.epost.openapi.proxy.web.bind;

import java.util.Objects;

@SuppressWarnings({
        "java:S119"
})

public abstract class AbstractModel2Test<MODEL extends AbstractModel2> {

    protected AbstractModel2Test(final Class<MODEL> modelClass) {
        super();
        this.modelClass = Objects.requireNonNull(modelClass, "modelClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<MODEL> modelClass;
}