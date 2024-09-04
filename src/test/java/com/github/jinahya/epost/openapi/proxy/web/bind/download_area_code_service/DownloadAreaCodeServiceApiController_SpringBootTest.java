package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy._TestConstants;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service._DownloadAreaCodeServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind.LocalExchangeFunction;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service._DownloadAreaCodeServiceConstants.REQUEST_URI_GET_AREA_CODE_INFO;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service._DownloadAreaCodeServiceConstants.ROUTE_ID;
import static com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service._DownloadAreaCodeServiceApiConstants.REQUEST_URI_AREA_CODE_INFO;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link DownloadAreaCodeServiceApiController}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@EnabledIfEnvironmentVariable(named = _TestConstants.ENVIRONMENT_VARIABLE_SERVICE_KEY, matches = ".+")
@Import(
        value = {
//                ValidationAutoConfiguration.class,
//                DownloadAreaCodeServiceApiController_SpringBootTest.TestConfiguration_.class
        }
)
@ContextConfiguration(classes = {
//        DownloadAreaCodeServiceApiController.class,
//        DownloadAreaCodeServiceApiController_SpringBootTest.TestConfiguration_.class,
        SpringBootConfiguration_.class,
        HalConfiguration.class
//        ,
//        DownloadAreaCodeServiceApiController_SpringBootTest.TestConfiguration_.class
})
//@Import({DownloadAreaCodeServiceApiController_SpringBootTest.TestConfiguration_.class})
//@SpringBootTest(classes = {
//        DownloadAreaCodeServiceApiController.class
//})
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//        ,
//        classes = {
//                DownloadAreaCodeServiceApiController.class
////                ,
////                HalConfiguration.class
//        }
)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class DownloadAreaCodeServiceApiController_SpringBootTest
        extends _ApiController_SpringBootTest<DownloadAreaCodeServiceApiController> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A uri template of {@value _DownloadAreaCodeServiceConstants#REQUEST_URI_GET_AREA_CODE_INFO}.
     */
    private static final UriTemplate URI_TEMPLATE_GET_AREA_CODE_INFO = new UriTemplate(REQUEST_URI_GET_AREA_CODE_INFO);

    // -----------------------------------------------------------------------------------------------------------------
    @TestConfiguration
    static class TestConfiguration_ {

        @LocalExchangeFunction
        @Bean
        ExchangeFunction exchangeFunction() {
            return r -> {
                log.debug("request: {}", r);
                final var url = r.url();
                final var urlPath = url.getPath();
                if (URI_TEMPLATE_GET_AREA_CODE_INFO.matches(urlPath)) {
                    final var dwldSeValue = UriComponentsBuilder.fromUri(url)
                            .build()
                            .getQueryParams()
                            .get(_DownloadAreaCodeServiceConstants.PARAM_NAME_DWLDSE)
                            .getFirst();
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> h.setContentType(MediaType.APPLICATION_XML))
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getAreaCodeInfo_response" + dwldSeValue + ".xml"))
                                    .build()
                    );
                }
                throw new RuntimeException("unhandled url: " + url);
            };
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static String xmlRouteResName(final String dwldSeValue) {
        Objects.requireNonNull(dwldSeValue, "dwldSeValue is null");
        return '/' + ROUTE_ID + "/getAreaCodeInfo_response" + dwldSeValue + ".xml";
    }

    private static String xmlRouteResName(final AreaCodeInfoRequest.DwldSe dwldSe) {
        Objects.requireNonNull(dwldSe, "dwldSe is null");
        return xmlRouteResName(dwldSe.value());
    }

    private static Stream<String> xmlRouteResNameStream() {
        return Stream.of(AreaCodeInfoRequest.DwldSe.values())
                .map(DownloadAreaCodeServiceApiController_SpringBootTest::xmlRouteResName);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void verifyAreaCodeInfoResponseContent(final AreaCodeInfoResponse content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    private void verifyAreaCodeInfoResponseModel(final EntityModel<AreaCodeInfoResponse> model) {
        Objects.requireNonNull(model, "model is null");
        final var links = model.getLinks();
        final var self = links.getLink(IanaLinkRelations.SELF);
        assertThat(self).hasValueSatisfying(v -> {
        });
        final var fileContent = links.getLink(_DownloadAreaCodeServiceApiConstants.REL_FILE_CONTENT);
        assertThat(fileContent).hasValueSatisfying(v -> {
        });
        verifyAreaCodeInfoResponseContent(model.getContent());
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link DownloadAreaCodeServiceApiController#readAreaCodeInfo(ServerWebExchange)} method.
     */
    @DisplayName("GET " + REQUEST_URI_AREA_CODE_INFO)
    @Test
    void __() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest
                        .get(REQUEST_URI_AREA_CODE_INFO)
                        .build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var result = controllerInstance()
                .readAreaCodeInfo(exchange)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::verifyAreaCodeInfoResponseModel)
                .blockLast();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link DownloadAreaCodeServiceApiController#readAreaCodeInfo(ServerWebExchange, String)} method.
     *
     * @param dwldSe the value for {@code dwldSe} parameter.
     */
    @DisplayName("GET " + _DownloadAreaCodeServiceApiConstants.REQUEST_URI_DWLD_SE)
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
        final var result = controllerInstance()
                .readAreaCodeInfo(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(_DownloadAreaCodeServiceApiConstants.REQUEST_URI_DWLD_SE, dwldSe.value())
                                        .build()
                        ),
                        dwldSe.value()
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::verifyAreaCodeInfoResponseModel)
                .block();
    }
}