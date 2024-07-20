package com.github.jinahya.epost.openapi.proxy._common;

/**
 * An abstract class for testing a specific subclass of {@link AbstractSelfWrappingResponseType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractSelfWrappingResponseTypeTest<TYPE extends AbstractSelfWrappingResponseType<TYPE>>
        extends AbstractTypeTest<TYPE> {

    protected AbstractSelfWrappingResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }
}
