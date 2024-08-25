package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import org.springframework.core.ResolvableType;

public abstract class AbstractResponseElementTypeTest<
        TYPE extends AbstractResponseElementType<TYPE, PARENT>,
        PARENT extends AbstractResponseType<PARENT>>
        extends AbstractTypeTest<TYPE> {

    @SuppressWarnings({"unchecked"})
    protected AbstractResponseElementTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
        parentClass = (Class<PARENT>) ResolvableType.forType(super.typeClass)
                .as(AbstractResponseElementType.class)
                .getGeneric(1)
                .resolve();
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<PARENT> parentClass;
}
