package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

//import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;
import com.github.jinahya.epost.openapi.proxy.http.codec.json._Jackson2JsonEncoder;
import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;

@Component
@Slf4j
class RoadsGatewayFilterFactory
        extends AbstractGatewayFilterFactory<RoadsGatewayFilterFactory.Config> {

    // -----------------------------------------------------------------------------------------------------------------
    @Setter
    @Getter
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    static class Config
            extends CitiesGatewayFilterFactory.Config {

    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    RoadsGatewayFilterFactory() {
        super(Config.class);
    }

    @PostConstruct
    private void onPostConstruct() {
        objectMapper = objectMapperBuilder
                .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // https://stackoverflow.com/a/45190717/330457
    // https://velog.io/@ljft183/SpringCloudGateway3
    @Override
    public GatewayFilter apply(final Config config) {
        return (exchange, chain) -> {
            log.debug("exchange.request.uri: {}", exchange.getRequest().getURI());
            final URL requestUrl;
            try {
                requestUrl = exchange.getRequest().getURI().toURL();
            } catch (final MalformedURLException murle) {
                throw new RuntimeException(murle);
            }
            final var baseUrl = UriComponentsBuilder
                    .fromUriString(requestUrl.getProtocol() + "://" + requestUrl.getHost() + ':' + requestUrl.getPort())
                    .build();
            final var webClient = WebClient.builder()
                    .baseUrl(baseUrl.toString())
                    .build();
            final var responseDecorator = new ServerHttpResponseDecorator(exchange.getResponse()) {
                @Override
                public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
                    return super.writeWith(
                            new Jaxb2XmlDecoder(MimeType.valueOf(MediaType.APPLICATION_XML_VALUE))
                                    .decode(
                                            Flux.from(body),
                                            ResolvableType.forType(RoadEngFirstNameListResponse.class),
                                            null,
                                            null
                                    )
                                    .doOnNext(d -> {
                                        log.debug("decoded: {}", d);
                                    })
                                    .flatMap(r -> ((RoadEngFirstNameListResponse) r).retrieveRoadEngList(
                                            exchange.getRequest().getQueryParams().getFirst(
                                                    _RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME),
                                            exchange.getRequest().getQueryParams().getFirst(
                                                    _RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME),
                                            webClient))
                                    .map(rel -> Road.from(exchange, rel))
                                    .map(Road::addLinks)
                                    .map(r -> {
                                        return new _Jackson2JsonEncoder(objectMapper)
                                                .encodeValue(
                                                        r,
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

    // ------------------------------------------------------------------------------------------------------- webClient

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    private ObjectMapper objectMapper;
}
