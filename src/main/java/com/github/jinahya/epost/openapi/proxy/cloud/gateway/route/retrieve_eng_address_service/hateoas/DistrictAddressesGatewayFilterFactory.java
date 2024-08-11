package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse;
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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
class DistrictAddressesGatewayFilterFactory
        extends AbstractGatewayFilterFactory<DistrictAddressesGatewayFilterFactory.Config> {

    private static final int COUNT_PER_PAGE = 32; // yaml

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
    DistrictAddressesGatewayFilterFactory() {
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
            final var request = e.getRequest();
            final var response = e.getResponse();
            final URL requestUrl;
            try {
                requestUrl = request.getURI().toURL();
            } catch (final MalformedURLException murle) {
                throw new RuntimeException(murle);
            }
            final var baseUrl = UriComponentsBuilder
                    .fromUriString(
                            requestUrl.getProtocol() + "://" + requestUrl.getHost() + ':' + requestUrl.getPort())
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
                    final var totalPage = new AtomicInteger();
                    final var firstPage = new Jaxb2XmlDecoder().decode(
                                    Flux.from(body),
                                    ResolvableType.forType(LandAddressEngSearchListResponse.class),
                                    null,
                                    null
                            )
                            .map(v -> (LandAddressEngSearchListResponse) v)
                            .switchOnFirst((s, f) -> {
                                final var cmmMsgHeader = s.get().getCmmMsgHeader();
                                totalPage.set(cmmMsgHeader.getTotalPage());
                                return f;
                            });
                    final var stateName = State.stateName(e);
                    final var cityName = City.cityName(e);
                    final var districtName = District.districtName(e);
                    final var restPages = Mono.just(LandAddressEngSearchListRequest.of(
                                    null,
                                    stateName,
                                    cityName,
                                    null,
                                    districtName,
                                    null,
                                    COUNT_PER_PAGE,
                                    2 // the second page
                            ))
                            .expand(r -> Mono.just(r.forNextPage()))
                            .takeWhile(r -> r.getCurrentPage() < totalPage.get())
                            .flatMapSequential(r -> r.exchange(webClient), 1);
                    return super.writeWith(
                            Flux.concat(firstPage, restPages)
                                    .flatMap(r -> Flux.fromIterable(r.getLandAddressEngSearchList())
                                            .map(DistrictAddress::from)
                                            .map(DistrictAddress::addLinks)
                                    )
                                    .map(ra -> new _Jackson2JsonEncoder(objectMapper).encodeValue(
                                                         ra,
                                                         getDelegate().bufferFactory(),
                                                         ResolvableType.forType(DistrictAddress.class),
                                                         null,
                                                         null
                                                 )
                                                 .ensureWritable(1).write((byte) 0x0A)
                                    )
                    );
                }
            };
            e.getResponse().getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
//            response.beforeCommit(() -> {
//                response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_NDJSON_VALUE);
//                return Mono.empty();
//            });
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
