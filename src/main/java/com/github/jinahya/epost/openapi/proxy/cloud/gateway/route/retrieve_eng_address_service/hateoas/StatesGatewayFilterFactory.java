package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

//import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.http.codec.json._Jackson2JsonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.LinkBuilderFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
class StatesGatewayFilterFactory
        extends AbstractGatewayFilterFactory<StatesGatewayFilterFactory.Config> {

    // -----------------------------------------------------------------------------------------------------------------
    public static class Config {

    }

    // -----------------------------------------------------------------------------------------------------------------
    StatesGatewayFilterFactory(final Jackson2ObjectMapperBuilder objectMapperBuilder, final EntityLinks entityLinks) {
        super(Config.class);
        this.objectMapperBuilder = Objects.requireNonNull(objectMapperBuilder, "objectMapperBuilder is null");
        objectMapper = this.objectMapperBuilder
                .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
                .build();
        this.entityLinks = Objects.requireNonNull(entityLinks, "entityLinks is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // https://stackoverflow.com/a/45190717/330457
    // https://velog.io/@ljft183/SpringCloudGateway3
    @Override
    public GatewayFilter apply(final Config config) {
        return (exchange, chain) -> {
            final var responseDecorator = new ServerHttpResponseDecorator(exchange.getResponse()) {
                @Override
                public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
                    return super.writeWith(
                            new Jaxb2XmlDecoder(MimeType.valueOf(MediaType.APPLICATION_XML_VALUE))
                                    .decode(
                                            Flux.from(body),
                                            ResolvableType.forType(StateEngListResponse.class),
                                            null,
                                            null
                                    )
                                    .flatMap(selr -> Flux.fromIterable(((StateEngListResponse) selr).getStateEngList()))
                                    .map(State::from)
                                    .map(s -> {
                                        final var link = Link.of("/orders/{id}/customer")
                                                .expand(1)
                                                .withRel("customer");
                                        s.add(link);
                                        final var requestUri = exchange.getRequest().getURI();
                                        log.debug("requestUri: {}", requestUri);
                                        return s;
                                    })
                                    .map(sel -> {
                                        return new _Jackson2JsonEncoder(objectMapper)
                                                .encodeValue(
                                                        sel,
                                                        getDelegate().bufferFactory(),
                                                        ResolvableType.forType(State.class),
                                                        null,
                                                        null
                                                );
                                    })
                                    .map(db -> db.ensureWritable(1).write((byte) 0x0A))
                    );
                }
            };
            exchange.getResponse().getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
            return chain.filter(
                    exchange.mutate().response(responseDecorator).build()
            );
        };
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Jackson2ObjectMapperBuilder objectMapperBuilder;

    private final ObjectMapper objectMapper;

    private final EntityLinks entityLinks;

    @Autowired
    private LinkBuilderFactory linkBuilderFactory;
}
