package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import org.springframework.core.ResolvableType;

public abstract class AbstractPairedRequestTypeTest<
        TYPE extends AbstractPairedRequestType<TYPE, RESPONSE>,
        RESPONSE extends AbstractResponseType<RESPONSE>>
        extends AbstractRequestTypeTest<TYPE> {

    protected AbstractPairedRequestTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    protected Class<RESPONSE> responseTypeClass() {
        if (responseTypeClass == null) {
            responseTypeClass = (Class<RESPONSE>) ResolvableType
                    .forType(typeClass)
                    .as(AbstractPairedRequestType.class)
                    .resolveGeneric(1);
        }
        return responseTypeClass;
    }

    private Class<RESPONSE> responseTypeClass;
}
