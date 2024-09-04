package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._TestConstants;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiControllerTestUtils;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@EnabledIfEnvironmentVariable(named = _TestConstants.ENVIRONMENT_VARIABLE_SERVICE_KEY, matches = ".+")
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class RetrieveEngAddressServiceApiController_SpringBootTest
        extends _ApiController_SpringBootTest<RetrieveEngAddressServiceApiController> {

    // -----------------------------------------------------------------------------------------------------------------
    private <T> void validate(final T content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    private <T> void validate(final EntityModel<T> model) {
        Objects.requireNonNull(model, "model is null");
        validate(Objects.requireNonNull(model.getContent(), "model.content is null"));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static StateEngListResponse.StateEngList state = null;

    private static StateEngListResponse.StateEngList state() {
        return Optional.ofNullable(state).orElseGet(() -> {
            state = mock(StateEngListResponse.StateEngList.class);
            given(state.getStateEngName()).willReturn("Jeollanam-do");
            return state;
        });
    }

    @Order(_RetrieveEngAddressServiceApiController_TestConstants.ORDER_STATES)
    @DisplayName("GET " + _RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES)
    @Test
    void states__() {
        // ------------------------------------------------------------------------------------------------------- given
        mutateControllerInstanceWebClientWith(r -> {
            return Mono.just(
                    ClientResponse.create(HttpStatus.OK)
                            .headers(h -> {
                                h.setContentType(MediaType.APPLICATION_XML);
                            })
                            .body(routeResourceDataPublisher(
                                    _RetrieveEngAddressServiceConstants.ROUTE_ID + "/getStateList_response1.xml"))
                            .build()
            );
        });
        // -------------------------------------------------------------------------------------------------------- when
        final var states = controllerInstance()
                .readStates(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES)
                                        .build()
                        )
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        states.doOnNext(this::validate);
        state = _ApiControllerTestUtils.collectAndGetRandomContent(states).block();
    }

    @Order(_RetrieveEngAddressServiceApiController_TestConstants.ORDER_STATE)
    @DisplayName("GET " + REQUEST_URI_STATE)
    @Test
    void state__() {
        // ------------------------------------------------------------------------------------------------------- given
        mutateControllerInstanceWebClientWith(r -> {
            return Mono.just(
                    ClientResponse.create(HttpStatus.OK)
                            .headers(h -> {
                                h.setContentType(MediaType.APPLICATION_XML);
                            })
                            .body(routeResourceDataPublisher(
                                    _RetrieveEngAddressServiceConstants.ROUTE_ID + "/getStateList_response1.xml"))
                            .build()
            );
        });
        final var stateName = state().getStateEngName();
        // -------------------------------------------------------------------------------------------------------- when
        final var result = controllerInstance()
                .readState(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(REQUEST_URI_STATE, stateName)
                                        .build()
                        ),
                        state().getStateEngName()
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::validate)
                .block();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static CityEngListResponse.CityEngList city = null;

    private static CityEngListResponse.CityEngList city() {
        return Optional.ofNullable(city).orElseGet(() -> {
            city = mock(CityEngListResponse.CityEngList.class);
            given(city.getCityEngName()).willReturn("Jeollanam-do");
            return city;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Order(_RetrieveEngAddressServiceApiController_TestConstants.ORDER_CITIES)
    @DisplayName("GET " + _RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES)
    @Test
    void cities__() {
        // ------------------------------------------------------------------------------------------------------- given
        mutateControllerInstanceWebClientWith(r -> {
            return Mono.just(
                    ClientResponse.create(HttpStatus.OK)
                            .headers(h -> {
                                h.setContentType(MediaType.APPLICATION_XML);
                            })
                            .body(routeResourceDataPublisher(
                                    _RetrieveEngAddressServiceConstants.ROUTE_ID + "/getCityList_response0.xml"))
                            .build()
            );
        });
        final var stateName = state().getStateEngName();
        // -------------------------------------------------------------------------------------------------------- when
        final var cities = controllerInstance()
                .readCities(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES, stateName)
                                        .build()
                        ),
                        state().getStateEngName()
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        cities.doOnNext(this::validate);
        city = _ApiControllerTestUtils.collectAndGetRandomContent(cities).block();
    }

    @Order(_RetrieveEngAddressServiceApiController_TestConstants.ORDER_CITY)
    @DisplayName("GET " + REQUEST_URI_CITY)
    @Test
    void city__() {
        // ------------------------------------------------------------------------------------------------------- given
        mutateControllerInstanceWebClientWith(r -> {
            return Mono.just(
                    ClientResponse.create(HttpStatus.OK)
                            .headers(h -> {
                                h.setContentType(MediaType.APPLICATION_XML);
                            })
                            .body(routeResourceDataPublisher(
                                    _RetrieveEngAddressServiceConstants.ROUTE_ID + "/getCityList_response0.xml"))
                            .build()
            );
        });
        final var stateName = state().getStateEngName();
        final var cityName = city().getCityEngName();
        // -------------------------------------------------------------------------------------------------------- when
        final var result = controllerInstance()
                .readCity(
                        MockServerWebExchange.from(
                                MockServerHttpRequest
                                        .get(REQUEST_URI_CITY, stateName, cityName)
                                        .build()
                        ),
                        state.getStateEngName(),
                        city().getCityEngName()
                )
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::validate)
                .block();
    }
}
