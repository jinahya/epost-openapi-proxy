package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.springframework.core.ResolvableType;

public abstract class AbstractPairedRequestTypeTest<
        TYPE extends AbstractPairedRequestType<TYPE, RESPONSE>,
        RESPONSE extends AbstractPairedResponseType<RESPONSE, TYPE>>
        extends AbstractRequestTypeTest<TYPE> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected AbstractPairedRequestTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        return super.__equals(verifierApi)
                .withIgnoredFields("responseClass")
                .withPrefabValues(
                        AbstractPairedRequestType.class,
                        AbstractPairedRequestTypeTestUtils.prefabValue1(),
                        AbstractPairedRequestTypeTestUtils.prefabValue2()
                );
    }

    // ----------------------------------------------------------------------------------------------- responseTypeClass
    @SuppressWarnings({"unchecked"})
    protected Class<RESPONSE> responseTypeClass() {
        if (responseTypeClass == null) {
            responseTypeClass = (Class<RESPONSE>) ResolvableType
                    .forType(typeClass)
                    .as(AbstractPairedRequestType.class)
                    .resolveGeneric(1);
        }
        return responseTypeClass;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Class<RESPONSE> responseTypeClass;
}
