package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

import java.util.Objects;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class AbstractPairedRequestTypeTest<
        TYPE extends AbstractPairedRequestType<TYPE, RESPONSE>,
        RESPONSE extends AbstractPairedResponseType<RESPONSE, TYPE>>
        extends AbstractRequestTypeTest<TYPE> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected AbstractPairedRequestTypeTest(final Class<TYPE> typeClass, final Class<RESPONSE> responseTypeClass) {
        super(typeClass);
        this.responseTypeClass = Objects.requireNonNull(responseTypeClass, "responseTypeClass is null");
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

    // -----------------------------------------------------------------------------------------------------------------
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private final Class<RESPONSE> responseTypeClass;
}
