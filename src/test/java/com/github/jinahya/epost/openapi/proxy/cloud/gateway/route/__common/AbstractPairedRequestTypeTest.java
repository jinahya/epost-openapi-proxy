package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.springframework.core.ResolvableType;

public abstract class AbstractPairedRequestTypeTest<
        REQUEST extends AbstractPairedRequestType<REQUEST, RESPONSE>,
        RESPONSE extends AbstractPairedResponseType<RESPONSE, REQUEST>>
        extends AbstractRequestTypeTest<REQUEST> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected AbstractPairedRequestTypeTest(final Class<REQUEST> typeClass) {
        super(typeClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected SingleTypeEqualsVerifierApi<REQUEST> __equals(SingleTypeEqualsVerifierApi<REQUEST> verifierApi) {
        return super.__equals(verifierApi)
                .withIgnoredFields("responseClass");
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
