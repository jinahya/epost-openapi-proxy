package com.github.jinahya.epost.openapi.proxy.web.bind;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ResolvableType;

@SuppressWarnings({
        "java:S119" // <CONTROLLER ...>
})
public abstract class _ApiController_SpringBootTest<
        CONTROLLER extends _ApiController<SERVICE>,
        SERVICE extends _ApiService>
        extends __ApiController_SpringBootTest<CONTROLLER> {

    // ------------------------------------------------------------------------------------------------------ CONTROLLER

    // --------------------------------------------------------------------------------------------------------- SERVICE
    @SuppressWarnings({
            "unchecked"
    })
    protected Class<SERVICE> serviceClass() {
        return (Class<SERVICE>) ResolvableType
                .forType(getClass())
                .as(_ApiController_SpringBootTest.class)
                .getGeneric(1)
                .resolve();
    }


    // -----------------------------------------------------------------------------------------------------------------
    @MockBean
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private SERVICE serviceInstance;
}