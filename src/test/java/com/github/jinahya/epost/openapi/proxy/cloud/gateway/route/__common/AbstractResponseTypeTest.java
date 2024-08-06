package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * An abstract class for testing a specific subclass of {@link AbstractResponseType} class.
 *
 * @param <TYPE> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractResponseTypeTest<TYPE extends AbstractResponseType<TYPE>>
        extends AbstractTypeTest<TYPE> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected AbstractResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        final var instance1 = newTypeInstance();
        final var instance2 = newTypeInstance();
        instance1.setCmmMsgHeader(new CmmMsgHeader().requestMsgId("a"));
        instance2.setCmmMsgHeader(new CmmMsgHeader().requestMsgId("b"));
        assert !instance2.equals(instance1);
        return super.__equals(verifierApi)
                .withPrefabValues(
                        CmmMsgHeader.class,
                        new CmmMsgHeader().requestMsgId("a"),
                        new CmmMsgHeader().requestMsgId("b")
                )
                .withPrefabValues(Object.class, instance1, instance2)
                .withPrefabValues(AbstractResponseType.class, instance1, instance2)
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected TYPE instance1() {
        return newTypeInstance().cmmMsgHeader(new CmmMsgHeader().requestMsgId("a"));
    }

    protected TYPE instance2() {
        return newTypeInstance().cmmMsgHeader(new CmmMsgHeader().requestMsgId("a"));
    }
}
