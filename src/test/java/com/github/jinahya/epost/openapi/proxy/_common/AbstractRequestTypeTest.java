package com.github.jinahya.epost.openapi.proxy._common;

/**
 * An abstract class for testing a specific subclass of {@link AbstractRequestType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractRequestTypeTest<TYPE extends AbstractRequestType>
        extends AbstractTypeTest<TYPE> {

    protected AbstractRequestTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }
}
