package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._NoOp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ResolvableType;
import reactor.core.publisher.Mono;

import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractTypeUtils.unmarshalNoNamespacedInstance;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

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

    // ------------------------------------------------------------------------------------------------- serviceInstance
    // https://stackoverflow.com/a/69214093/330457
//    protected <REQUEST extends AbstractRequestType<REQUEST>, RESPONSE extends AbstractResponseType<RESPONSE>>
//    void stubExchangeResponseFor(final Class<REQUEST> requestClass, final Class<RESPONSE> responseClass,
//                                 final String resName) {
//        when(serviceInstance().exchangeResponseFor(notNull(requestClass), responseClass)).thenAnswer(i -> {
//            try (var r = _NoOp.class.getResourceAsStream(resName)) {
//                return Mono.just(unmarshalNoNamespacedInstance(responseClass, r));
//            }
//        });
//    }
//
//    protected <REQUEST extends AbstractPairedRequestType<REQUEST, RESPONSE>,
//            RESPONSE extends AbstractPairedResponseType<RESPONSE, REQUEST>>
//    void stubExchangeResponse(final Class<REQUEST> requestClass, final Class<RESPONSE> responseClass,
//                              final String resName) {
//        when(serviceInstance().exchangeResponse(notNull(requestClass))).thenAnswer(i -> {
//            try (var r = _NoOp.class.getResourceAsStream(resName)) {
//                return Mono.just(unmarshalNoNamespacedInstance(responseClass, r));
//            }
//        });
//    }

    // -----------------------------------------------------------------------------------------------------------------
    @MockBean
//    @SpyBean
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private SERVICE serviceInstance;
}