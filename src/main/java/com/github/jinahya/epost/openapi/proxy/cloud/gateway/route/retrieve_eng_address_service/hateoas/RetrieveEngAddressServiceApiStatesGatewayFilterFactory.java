package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

//import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.http.codec.json._Jackson2JsonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
class RetrieveEngAddressServiceApiStatesGatewayFilterFactory
        extends AbstractGatewayFilterFactory<RetrieveEngAddressServiceApiStatesGatewayFilterFactory.Config> {

    // -----------------------------------------------------------------------------------------------------------------
    static class Config {

    }

    // -----------------------------------------------------------------------------------------------------------------
    RetrieveEngAddressServiceApiStatesGatewayFilterFactory(final Jackson2ObjectMapperBuilder objectMapperBuilder) {
        super(Config.class);
        this.objectMapperBuilder = Objects.requireNonNull(objectMapperBuilder, "objectMapperBuilder is null");
        objectMapper = this.objectMapperBuilder
                .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // https://stackoverflow.com/a/45190717/330457
    // https://velog.io/@ljft183/SpringCloudGateway3
    @Override
    public GatewayFilter apply(final Config config) {
        return new OrderedGatewayFilter(
                (e, c) -> {
                    final var decorator = new ServerHttpResponseDecorator(e.getResponse()) {
                        @Override
                        public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
                            return super.writeWith(
                                    new Jaxb2XmlDecoder().decode(
                                                    Flux.from(body),
                                                    ResolvableType.forType(StateEngListResponse.class),
                                                    null,
                                                    null
                                            )
                                            .flatMap(selr -> Flux.fromIterable(
                                                    ((StateEngListResponse) selr).getStateEngList()))
                                            .map(State::of)
                                            .map(State::addLinks)
                                            .map(s -> new _Jackson2JsonEncoder(objectMapper).encodeValue(
                                                    s,
                                                    getDelegate().bufferFactory(),
                                                    ResolvableType.forType(State.class),
                                                    null,
                                                    null
                                            ))
                                            .map(db -> db.ensureWritable(1).write((byte) 0x0A))
                                            .doOnNext(db -> {
                                                log.debug("db: {}", db);
                                            })
                                            .doOnComplete(() -> {
                                                log.debug("completed");
                                            })
                            );
                        }
                    };
                    e.getResponse().getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
//            e.getResponse().beforeCommit(() -> {
//                e.getResponse().getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
//                return Mono.empty();
//            });
                    return c.filter(
                            e.mutate().response(decorator).build()
                    );
                },
                Ordered.HIGHEST_PRECEDENCE
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Jackson2ObjectMapperBuilder objectMapperBuilder;

    private final ObjectMapper objectMapper;
}
