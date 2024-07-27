package com.github.jinahya.epost.openapi.proxy._common;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * An abstract class for testing a specific subclass of {@link AbstractResponseType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractResponseTypeTest<TYPE extends AbstractResponseType<?>>
        extends AbstractTypeTest<TYPE> {

    protected AbstractResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        final var instance1 = newTypeInstance();
        final var instance2 = newTypeInstance();
        instance1.cmmMsgHeader(new CmmMsgHeader().requestMsgId("a"));
        instance2.cmmMsgHeader(new CmmMsgHeader().requestMsgId("b"));
        assert !instance2.equals(instance1);
        return super.__equals(verifierApi)
                .withPrefabValues(
                        CmmMsgHeader.class,
                        new CmmMsgHeader().requestMsgId("a"),
                        new CmmMsgHeader().requestMsgId("b")
                )
                .withPrefabValues(typeClass, instance1, instance2)
                ;
    }
}
