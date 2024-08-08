package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

//import jakarta.xml.bind.annotation.XmlType;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.xml.XmlEventDecoder;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.stream.events.XMLEvent;

@Component
@Slf4j
class _States2GatewayFilterFactory
        extends AbstractGatewayFilterFactory<_States2GatewayFilterFactory.Config> {

    // -----------------------------------------------------------------------------------------------------------------
    public static class Config {

    }

    // -----------------------------------------------------------------------------------------------------------------
    _States2GatewayFilterFactory() {
        super(Config.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // https://stackoverflow.com/a/45190717/330457
    // https://velog.io/@ljft183/SpringCloudGateway3
    @Override
    public GatewayFilter apply(final Config config) {
        return (exchange, chain) -> {
//;            final var request = exchange.getRequest();
//            final var response = exchange.getResponse();
//            final var bufferFactory = exchange.getResponse().bufferFactory();
            final var responseDecorator = new ServerHttpResponseDecorator(exchange.getResponse()) {
                @Override
                public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
                    return super.writeWith(
                                    new XmlEventDecoder().decode(Flux.from(body),
                                                                 ResolvableType.forType(XMLEvent.class), null, null)
                                            .doOnNext(xmlt -> {
                                                log.debug("xmlt: {}", xmlt);
                                            }))
                            .map(xmlt -> xmlt.togetDelegate().bufferFactory().);
                            .map(xmlt -> getDelegate().bufferFactory().);
                }
            };
            return chain.filter(exchange.mutate().response(responseDecorator).build());
//            return Mono.empty();
        };
    }

    // -----------------------------------------------------------------------------------------------------------------
//    private final ModifyResponseBodyGatewayFilterFactory modifyResponseBodyFilterFactory;
//    private final Jaxb2XmlDecoder jaxb2XmlDecoder;

//    private final XmlEventDecoder xmlEventDecoder;
}
