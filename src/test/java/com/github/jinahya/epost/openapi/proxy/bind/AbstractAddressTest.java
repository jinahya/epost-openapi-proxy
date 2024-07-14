package com.github.jinahya.epost.openapi.proxy.bind;

public abstract class AbstractAddressTest<TYPE extends AbstractAddress>
        extends AbstractTypeTest<TYPE> {

    protected AbstractAddressTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }
}
