package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.http.codec.json._Jackson2JsonEncoder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
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
class RetrieveEngAddressServiceApiCitiesGatewayFilterFactory
        extends AbstractGatewayFilterFactory<RetrieveEngAddressServiceApiCitiesGatewayFilterFactory.Config> {

    // -----------------------------------------------------------------------------------------------------------------
    @Setter
    @Getter
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    static class Config
            extends RetrieveEngAddressServiceApiStatesGatewayFilterFactory.Config {

    }

    // -----------------------------------------------------------------------------------------------------------------
    RetrieveEngAddressServiceApiCitiesGatewayFilterFactory(final Jackson2ObjectMapperBuilder objectMapperBuilder) {
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
        return (e, c) -> {
            final var decorator = new ServerHttpResponseDecorator(e.getResponse()) {
                @Override
                public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
                    return super.writeWith(
                            new Jaxb2XmlDecoder().decode(
                                            Flux.from(body),
                                            ResolvableType.forType(CityEngListResponse.class),
                                            null,
                                            null
                                    )
                                    .flatMap(celr -> Flux.fromIterable(((CityEngListResponse) celr).getCityEngList()))
                                    .map(cel -> City.from(e, cel))
                                    .map(City::addLinks)
                                    .map(sel -> new _Jackson2JsonEncoder(objectMapper).encodeValue(
                                            sel,
                                            getDelegate().bufferFactory(),
                                            ResolvableType.forType(State.class),
                                            null,
                                            null
                                    ))
                                    .map(db -> db.ensureWritable(1).write((byte) 0x0A))
                    );
                }
            };
            e.getResponse().getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
            return c.filter(
                    e.mutate().response(decorator).build()
            );
        };
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Jackson2ObjectMapperBuilder objectMapperBuilder;

    private final ObjectMapper objectMapper;
}
