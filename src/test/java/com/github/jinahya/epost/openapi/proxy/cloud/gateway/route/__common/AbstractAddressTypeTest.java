package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

public abstract class AbstractAddressTypeTest<TYPE extends AbstractAddressType<TYPE>>
        extends AbstractTypeTest<TYPE> {

    protected AbstractAddressTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }
}
