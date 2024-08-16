package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

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

    /**
     * Creates a new instance for testing specified type class.
     *
     * @param typeClass the type class to test.
     */
    protected AbstractResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        return super.__equals(verifierApi)
                .withPrefabValues(
                        CmmMsgHeader.class,
                        CmmMsgHeaderTestUtils.prefabValue1(),
                        CmmMsgHeaderTestUtils.prefabValue2()
                )
                .withPrefabValues(
                        AbstractResponseType.class,
                        AbstractResponseTypeTestUtils.prefabValue1(),
                        AbstractResponseTypeTestUtils.prefabValue2()
                )
                ;
    }
}
