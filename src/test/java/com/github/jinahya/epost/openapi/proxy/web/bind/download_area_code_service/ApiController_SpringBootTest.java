package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service._DownloadAreaCodeServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@ContextConfiguration(classes = {
        ApiController.class
})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class ApiController_SpringBootTest
        extends _ApiController_SpringBootTest<ApiController> {

    private static String xmlRouteResName(final String dwldSeValue) {
        return '/' + _DownloadAreaCodeServiceConstants.ROUTE_ID + "/getAreaCodeInfo_response" + dwldSeValue + ".xml";
    }

    private static String xmlRouteResName(final AreaCodeInfoRequest.DwldSe dwldSe) {
        return xmlRouteResName(dwldSe.value());
    }

    private static Stream<String> xmlRouteResNameStream() {
        return Stream.of(AreaCodeInfoRequest.DwldSe.values())
                .map(ApiController_SpringBootTest::xmlRouteResName);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET " + _ApiConstants.REQUEST_URI_AREA_CODE_INFO)
    @Test
    void __() {
        mutateControllerInstanceWebClientWith(r -> {
            final var dwldSeValue = UriComponentsBuilder.fromUri(r.url())
                    .build()
                    .getQueryParams()
                    .get(_DownloadAreaCodeServiceConstants.PARAM_NAME_DWLDSE)
                    .getFirst();
            return Mono.just(
                    ClientResponse.create(HttpStatus.OK)
                            .headers(h -> {
                                h.setContentType(MediaType.APPLICATION_XML);
                            })
                            .body(routeResourceDataPublisher(xmlRouteResName(dwldSeValue)))
                            .build()
            );
        });
        // -------------------------------------------------------------------------------------------------------- when
        final var flux = controllerInstance()
                .readAreaCodeInfo(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(_ApiConstants.REQUEST_URI_AREA_CODE_INFO)
                                        .build()
                        )
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")))
                .doOnNext(em -> {
                    log.debug("model: {}", em);
                })
                .map(EntityModel::getContent);
        // -------------------------------------------------------------------------------------------------------- then
        flux.doOnNext(c -> {
                    log.debug("content: {}", c);
                    assertValid(c);
                })
                .blockLast();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET" + _ApiConstants.REQUEST_URI_DWLD_SE)
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