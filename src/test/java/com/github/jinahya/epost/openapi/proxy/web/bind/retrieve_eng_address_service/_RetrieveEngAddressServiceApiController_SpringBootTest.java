package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootTest;
import com.github.jinahya.epost.openapi.proxy.web.server.ReactiveRequestContextFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Import({
        ReactiveRequestContextFilter.class
})
class _RetrieveEngAddressServiceApiController_SpringBootTest
        extends _ApiController_SpringBootTest<_RetrieveEngAddressServiceApiController> {

    @DisplayName("GET /.../states")
    @Test
    void readStates__() {
//        mutateControllerInstanceWebClient(wc -> {
//            return wc.mutate().exchangeFunction(new ExchangeFunction() {
//                @Override
//                public Mono<ClientResponse> exchange(final ClientRequest request) {
//                    final var resource = new UrlResource(
//                            getClass().getResource(
//                                    "/com/github/jinahya/epost/openapi/proxy/cloud/gateway/route/retrieve_eng_address_service/getStateList_response0.xml")
//                    );
//                    return Mono.just(
//                            ClientResponse.create(HttpStatus.OK)
//                                    .body(DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 1048576))
//                                    .build()
//                    );
//                }
//            }).build();
//        });
//        final var mutatedWebTestClient = webTestClient().mutateWith(new WebTestClientConfigurer() {
//            @Override
//            public void afterConfigurerAdded(WebTestClient.Builder builder, WebHttpHandlerBuilder httpHandlerBuilder,
//                                             ClientHttpConnector connector) {
//                builder.exchangeStrategies(ExchangeStrategies.builder().build());
//            }
//        });

//        _RetrieveEngAddressServiceApiController_SpringBootIT.readStates(webTestClient(), null);
        final var exchangeFunction = new ExchangeFunction() {
            @Override
            public Mono<ClientResponse> exchange(final ClientRequest request) {
                final var resource = new UrlResource(
                        getClass().getResource(
                                "/com/github/jinahya/epost/openapi/proxy/cloud/gateway/route/retrieve_eng_address_service/getStateList_response0.xml")
                );
                return Mono.just(
                        ClientResponse.create(HttpStatus.OK)
                                .body(DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 1048576))
                                .build()
                );
            }
        };
        setControllerInstanceWebClient(
                WebClient.builder()
                        .exchangeFunction(exchangeFunction)
                        .build()
        );
//        // https://stackoverflow.com/a/68937805/330457
//        controllerInstance().readStates(null)
//                .blockLast();
    }
}
