package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

public abstract class AbstractAddressTest<TYPE extends AbstractAddress<TYPE>>
        extends AbstractTypeTest<TYPE> {

    protected AbstractAddressTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }
}
