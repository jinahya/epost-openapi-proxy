package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import org.springframework.core.ResolvableType;

public abstract class AbstractPairedResponseTypeTest<
        TYPE extends AbstractPairedResponseType<TYPE, REQUEST>,
        REQUEST extends AbstractRequestType<REQUEST>>
        extends AbstractResponseTypeTest<TYPE> {

    protected AbstractPairedResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    protected Class<REQUEST> requestTypeClass() {
        if (requestTypeClass == null) {
            requestTypeClass = (Class<REQUEST>) ResolvableType
                    .forType(typeClass)
                    .as(AbstractPairedResponseType.class)
                    .resolveGeneric(1);
        }
        return requestTypeClass;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Class<REQUEST> requestTypeClass;
}
