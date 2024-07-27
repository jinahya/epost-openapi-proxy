package com.github.jinahya.epost.openapi.proxy._common;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * An abstract class for testing a specific subclass of {@link AbstractSelfWrappingResponseType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractSelfWrappingResponseTypeTest<TYPE extends AbstractSelfWrappingResponseType<TYPE>>
        extends AbstractResponseTypeTest<TYPE> {

    protected AbstractSelfWrappingResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        return super.__equals(verifierApi);
    }
}
