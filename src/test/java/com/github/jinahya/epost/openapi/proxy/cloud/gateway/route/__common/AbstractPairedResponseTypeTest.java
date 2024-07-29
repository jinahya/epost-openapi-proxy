package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import org.springframework.core.ResolvableType;

public abstract class AbstractPairedResponseTypeTest<
        RESPONSE extends AbstractPairedResponseType<RESPONSE, REQUEST>,
        REQUEST extends AbstractPairedRequestType<REQUEST, RESPONSE>>
        extends AbstractResponseTypeTest<RESPONSE> {

    protected AbstractPairedResponseTypeTest(final Class<RESPONSE> typeClass) {
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
