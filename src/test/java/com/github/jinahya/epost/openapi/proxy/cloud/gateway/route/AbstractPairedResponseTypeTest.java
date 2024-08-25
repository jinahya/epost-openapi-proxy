package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.springframework.core.ResolvableType;

public abstract class AbstractPairedResponseTypeTest<
        TYPE extends AbstractPairedResponseType<TYPE, REQUEST>,
        REQUEST extends AbstractPairedRequestType<REQUEST, TYPE>>
        extends AbstractResponseTypeTest<TYPE> {

    protected AbstractPairedResponseTypeTest(final Class<TYPE> typeClass) {
        super(typeClass);
    }

    @Override
    protected SingleTypeEqualsVerifierApi<TYPE> __equals(final SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        return super.__equals(verifierApi)
                .withIgnoredFields("requestClass")
                .withPrefabValues(
                        AbstractPairedResponseType.class,
                        AbstractPairedResponseTypeTestUtils.prefabValue1(),
                        AbstractPairedResponseTypeTestUtils.prefabValue2()
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    protected Class<REQUEST> requestTypeClass() {
        if (requestTypeClass == null) {
            requestTypeClass = (Class<REQUEST>) ResolvableType
                    .forType(typeClass)
                    .as(AbstractPairedResponseType.class)
                    .resolveGeneric(1);
        }
        return requestTypeClass;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Class<REQUEST> requestTypeClass;
}
