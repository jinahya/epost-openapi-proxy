package com.github.jinahya.epost.openapi.proxy._common;

/**
 * An abstract class for testing a specific subclass of {@link AbstractResponseType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractResponseTypeTest<TYPE extends AbstractResponseType>
        extends AbstractTypeTest<TYPE> {

    protected AbstractResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }
}
