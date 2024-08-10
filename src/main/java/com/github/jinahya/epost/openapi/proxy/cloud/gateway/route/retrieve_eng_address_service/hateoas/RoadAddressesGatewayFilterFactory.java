package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListRequest;
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
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
class RoadAddressesGatewayFilterFactory
        extends AbstractGatewayFilterFactory<RoadAddressesGatewayFilterFactory.Config> {

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
    RoadAddressesGatewayFilterFactory() {
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
        return (e, c) -> {
            log.debug("exchange.request.uri: {}", e.getRequest().getURI());
            final URL requestUrl;
            try {
                requestUrl = e.getRequest().getURI().toURL();
            } catch (final MalformedURLException murle) {
                throw new RuntimeException(murle);
            }
            final var baseUrl = UriComponentsBuilder
                    .fromUriString(requestUrl.getProtocol() + "://" + requestUrl.getHost() + ':' + requestUrl.getPort())
                    .build();
            final var webClient = WebClient.builder()
                    .baseUrl(baseUrl.toString())
                    .build();
            final var decorator = new ServerHttpResponseDecorator(e.getResponse()) {
                @Override
                public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body) {
                    // <totalPage> 를 넘어가는 값으로 ?currentPage 를 줘도
                    // 마지막 페이지를 준다.
                    // 오류신고 및 문의를 넣어놓긴 했는데....
                    // 이따구로 만든 외주사가 그 의미를 파악이나 할 수 있을런지 잘 모르겠다.
                    final var total = Collections.synchronizedList(new ArrayList<Integer>(1));
                    final var count = new AtomicInteger(0);
                    return super.writeWith(
                            Mono.just(
                                            RoadAddressEngSearchListRequest.of(
                                                    null,
                                                    State.stateName(e),
                                                    City.cityName(e),
                                                    null,
                                                    Road.roadName(e),
                                                    null,
                                                    32,
                                                    1
                                            )
                                    )
                                    .expand(r -> Mono.just(r.forNextPage()))
                                    .flatMapSequential(r -> r.exchange(webClient), 1)
                                    .doOnNext(r -> {
                                        if (total.isEmpty()) {
                                            total.add(r.getCmmMsgHeader().getTotalCount());
                                        }
                                    })
                                    .flatMap(r -> Flux.fromIterable(r.getRoadAddressEngSearchList())
                                            .map(RoadAddress::from)
                                            .map(RoadAddress::addLinks)
                                    )
                                    .doOnNext(v -> count.incrementAndGet())
                                    .takeWhile(v -> count.get() < total.getFirst())
                                    .map(ra -> new _Jackson2JsonEncoder(objectMapper)
                                            .encodeValue(
                                                    ra,
                                                    getDelegate().bufferFactory(),
                                                    ResolvableType.forType(RoadAddress.class),
                                                    null,
                                                    null
                                            )
                                            .ensureWritable(1).write((byte) 0x0A)
                                    )
                                    .doOnComplete(() -> {
                                        log.debug("count: {}", count.get());
                                    })
                    );
                }
            };
            e.getResponse().getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
            return c.filter(
                    e.mutate().response(decorator).build()
            );
        };
    }

    // ------------------------------------------------------------------------------------------------------- webClient

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    private ObjectMapper objectMapper;
}
