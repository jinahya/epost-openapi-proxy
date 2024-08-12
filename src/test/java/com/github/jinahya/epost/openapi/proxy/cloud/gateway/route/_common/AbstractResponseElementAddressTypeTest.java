package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.springframework.core.ResolvableType;

import java.util.Objects;

public abstract class AbstractResponseElementAddressTypeTest<
        TYPE extends AbstractResponseElementAddressType<TYPE, PARENT>,
        PARENT extends AbstractResponseType<PARENT>>
        extends AbstractAddressTypeTest<TYPE> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected AbstractResponseElementAddressTypeTest(final Class<TYPE> typeClass, final Class<PARENT> parentClass) {
        super(typeClass);
        this.parentClass = Objects.requireNonNull(parentClass, "parentClass is null");
    }

    @SuppressWarnings({"unchecked"})
    protected AbstractResponseElementAddressTypeTest(final Class<TYPE> typeClass) {
        this(
                typeClass,
                (Class<PARENT>) ResolvableType
                        .forType(typeClass)
                        .as(AbstractResponseElementAddressType.class)
                        .getGeneric(1)
                        .resolve()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected SingleTypeEqualsVerifierApi<TYPE> __equals(SingleTypeEqualsVerifierApi<TYPE> verifierApi) {
        return super.__equals(verifierApi)
                .withIgnoredFields("parent")
                .withPrefabValues(
                        AbstractResponseType.class,
                        AbstractResponseTypeTestUtils.prefabValue1(),
                        AbstractResponseTypeTestUtils.prefabValue2()
                );
    }

    // ----------------------------------------------------------------------------------------------------- parentClass

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<PARENT> parentClass;
}
