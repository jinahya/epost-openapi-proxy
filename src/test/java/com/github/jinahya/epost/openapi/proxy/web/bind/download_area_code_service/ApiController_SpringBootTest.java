package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy._misc.net.URLUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service._DownloadAreaCodeServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class ApiController_SpringBootTest
        extends _ApiController_SpringBootTest<ApiController> {

    private static String xmlRouteResName(final AreaCodeInfoRequest.DwldSe dwldSe) {
        return '/' + _DownloadAreaCodeServiceConstants.ROUTE_ID + "/getAreaCodeInfo_response" + dwldSe.value() + ".xml";
    }

    private static Stream<String> xmlRouteResNameStream() {
        return Stream.of(AreaCodeInfoRequest.DwldSe.values())
                .map(ApiController_SpringBootTest::xmlRouteResName);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() {
        // ------------------------------------------------------------------------------------------------------- given
        // mutate the controllerInstance().webClient to just return the resource
//        mutateControllerInstanceWebClientWith(r -> Mono.just(
//                ClientResponse.create(HttpStatus.OK)
//                        .headers(h -> {
//                            h.setContentType(MediaType.APPLICATION_NDJSON);
//                        })
//                        .body(routeResourceDataPublisher(
//                                '/' + _DownloadAreaCodeServiceConstants.ROUTE_ID + "/getAreaCodeInfo_response.ndjson")
//                        )
//                        .build()
//        ));
        mutateControllerInstanceWebClientWith(r -> {
            final var filename = URLUtils.getFileName(r.url().toString());
            final var dwldSe = AreaCodeInfoRequest.DwldSe.valueOfValue(URLUtils.getFileName(r.url().toString()));
            return Mono.just(
                    ClientResponse.create(HttpStatus.OK)
                            .headers(h -> {
                                h.setContentType(MediaType.APPLICATION_XML);
                            })
                            .body(routeResourceDataPublisher(xmlRouteResName(dwldSe)))
                            .build()
            );
        });
        // -------------------------------------------------------------------------------------------------------- when
        final var content = controllerInstance()
                .readAreaCodeInfo(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(_ApiConstants.REQUEST_URI_AREA_CODE_INFO)
                                        .build()
                        )
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")))
                .map(EntityModel::getContent);
        // -------------------------------------------------------------------------------------------------------- then
        content.doOnNext(c -> {
                    log.debug("content: {}", c);
                    assertValid(c);
                })
                .blockLast();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @EnumSource(AreaCodeInfoRequest.DwldSe.class)
    @ParameterizedTest
    void __(final AreaCodeInfoRequest.DwldSe dwldSe) {
        // ------------------------------------------------------------------------------------------------------- given
        // mutate the controllerInstance().webClient to just return the resource
        mutateControllerInstanceWebClientWith(r -> Mono.just(
                ClientResponse.create(HttpStatus.OK)
                        .headers(h -> {
                            h.setContentType(MediaType.APPLICATION_XML);
                        })
                        .body(routeResourceDataPublisher(xmlRouteResName(dwldSe)))
                        .build()
        ));
        // -------------------------------------------------------------------------------------------------------- when
        final var content = controllerInstance()
                .readAreaCodeInfo(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(_ApiConstants.REQUEST_URI_DWLD_SE, dwldSe.value())
                                        .build()
                        ),
                        dwldSe.value()
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")))
                .map(EntityModel::getContent)
                .block();
        log.debug("content: {}", content);
        // -------------------------------------------------------------------------------------------------------- then
        assertValid(content);
    }
}