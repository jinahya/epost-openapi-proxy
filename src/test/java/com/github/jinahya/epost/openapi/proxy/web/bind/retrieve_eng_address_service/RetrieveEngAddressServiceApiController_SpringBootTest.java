package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._TestConstants;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind.LocalExchangeFunction;
import com.github.jinahya.epost.openapi.proxy.web.bind.__ApiController_SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriTemplate;
import reactor.core.publisher.Mono;

import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_FIRST_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_LAND_ADDRESS_SEARCH;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_ADDRESS_SEARCH;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_FIRST_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.ROUTE_ID;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES;

@Disabled
@EnabledIfEnvironmentVariable(named = _TestConstants.ENVIRONMENT_VARIABLE_SERVICE_KEY, matches = ".+")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@SuppressWarnings({
        "java:S125" // / ----------
})
class RetrieveEngAddressServiceApiController_SpringBootTest
        extends __ApiController_SpringBootTest<RetrieveEngAddressServiceApiController> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The value for the {@value _RetrieveEngAddressServiceApiConstants#PATH_TEMPLATE_STATE_NAME} path.
     */
    private static final String STATE_NAME = "Jeollanam-do";

    /**
     * The value for the {@value _RetrieveEngAddressServiceApiConstants#PATH_TEMPLATE_CITY_NAME} path.
     */
    private static final String CITY_NAME = "Naju-si";

    private static final String DISTRICT_ENG_FIRST_NAME = "D";

    private static final String DISTRICT_NAME = "Daeho-dong";

    private static final String ROAD_ENG_FIRST_NAME = "D";

    private static final String ROAD_NAME = "Daeho-gil";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A uri template of {@value _RetrieveEngAddressServiceConstants#REQUEST_URI_GET_STATE_LIST}.
     */
    private static final UriTemplate URI_TEMPLATE_GET_STATE_LIST = new UriTemplate(REQUEST_URI_GET_STATE_LIST);

    /**
     * A uri template of {@value _RetrieveEngAddressServiceConstants#REQUEST_URI_GET_CITY_LIST}.
     */
    private static final UriTemplate URI_TEMPLATE_GET_CITY_LIST = new UriTemplate(REQUEST_URI_GET_CITY_LIST);

    // -----------------------------------------------------------------------------------------------------------------
    private static final UriTemplate URI_TEMPLATE_GET_DISTRICT_FIRST_NAME_LIST = new UriTemplate(
            REQUEST_URI_GET_DISTRICT_FIRST_NAME_LIST);

    private static final UriTemplate URI_TEMPLATE_GET_DISTRICT_NAME_LIST = new UriTemplate(
            REQUEST_URI_GET_DISTRICT_NAME_LIST);

    private static final UriTemplate URI_TEMPLATE_GET_LAND_ADDRESS_SEARCH = new UriTemplate(
            REQUEST_URI_GET_LAND_ADDRESS_SEARCH);

    // -----------------------------------------------------------------------------------------------------------------
    private static final UriTemplate URI_TEMPLATE_GET_ROAD_FIRST_NAME_LIST = new UriTemplate(
            REQUEST_URI_GET_ROAD_FIRST_NAME_LIST);

    private static final UriTemplate URI_TEMPLATE_GET_ROAD_NAME_LIST = new UriTemplate(
            REQUEST_URI_GET_ROAD_NAME_LIST);

    private static final UriTemplate URI_TEMPLATE_GET_ROAD_ADDRESS_SEARCH = new UriTemplate(
            REQUEST_URI_GET_ROAD_ADDRESS_SEARCH);

    // -----------------------------------------------------------------------------------------------------------------
//    @TestConfiguration
    static class TestConfiguration_ {

        @LocalExchangeFunction
        @Bean
        ExchangeFunction exchangeFunction() {
            return r -> {
                log.debug("request: {}", r);
                final var url = r.url();
                final var urlPath = url.getPath();
                if (URI_TEMPLATE_GET_STATE_LIST.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> h.setContentType(MediaType.APPLICATION_XML))
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getStateList_response1.xml"))
                                    .build()
                    );
                }
                if (URI_TEMPLATE_GET_CITY_LIST.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> {
                                        h.setContentType(MediaType.APPLICATION_XML);
                                    })
                                    .body(routeResourceDataPublisher('/' + ROUTE_ID + "/getCityList_response1.xml"))
                                    .build()
                    );
                }
                if (URI_TEMPLATE_GET_DISTRICT_FIRST_NAME_LIST.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> {
                                        h.setContentType(MediaType.APPLICATION_XML);
                                    })
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getDistrictFirstNameList_response1.xml"))
                                    .build()
                    );
                }
                if (URI_TEMPLATE_GET_DISTRICT_NAME_LIST.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> {
                                        h.setContentType(MediaType.APPLICATION_XML);
                                    })
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getDistrictNameList_response1.xml"))
                                    .build()
                    );
                }
                if (URI_TEMPLATE_GET_LAND_ADDRESS_SEARCH.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> {
                                        h.setContentType(MediaType.APPLICATION_XML);
                                    })
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getLandAddressSearch_response1.xml"))
                                    .build()
                    );
                }
                if (URI_TEMPLATE_GET_ROAD_FIRST_NAME_LIST.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> {
                                        h.setContentType(MediaType.APPLICATION_XML);
                                    })
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getRoadFirstNameList_response1.xml"))
                                    .build()
                    );
                }
                if (URI_TEMPLATE_GET_ROAD_NAME_LIST.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> {
                                        h.setContentType(MediaType.APPLICATION_XML);
                                    })
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getRoadNameList_response1.xml"))
                                    .build()
                    );
                }
                if (URI_TEMPLATE_GET_ROAD_ADDRESS_SEARCH.matches(urlPath)) {
                    return Mono.just(
                            ClientResponse.create(HttpStatus.OK)
                                    .headers(h -> {
                                        h.setContentType(MediaType.APPLICATION_XML);
                                    })
                                    .body(routeResourceDataPublisher(
                                            '/' + ROUTE_ID + "/getRoadAddressSearch_response1.xml"))
                                    .build()
                    );
                }
                throw new RuntimeException("unhandled url: " + url);
            };
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void a() {
    }

    // -----------------------------------------------------------------------------------------------------------------
//    private <T> void validate(final T content) {
//        Objects.requireNonNull(content, "content is null");
//        assertValid(content);
//    }
//
//    private <T> void validate(final EntityModel<T> model) {
//        Objects.requireNonNull(model, "model is null");
//        validate(Objects.requireNonNull(model.getContent(), "model.content is null"));
//    }

    // ----------------------------------------------------------------------------------------------------- /.../states
    @DisplayName("GET " + REQUEST_URI_STATES)
    @Test
    void states__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest
                        .get(REQUEST_URI_STATES)
                        .build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var states = controllerInstance()
                .readStates(exchange)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        states
                .doOnNext(m -> {
                    log.debug("content: {}", m.getContent());
                })
                .doOnNext(this::validate)
                .blockLast();
    }

    // ----------------------------------------------------------------------------------------- /.../states/{stateName}
    @DisplayName("GET " + REQUEST_URI_STATE)
    @Test
    void state__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(REQUEST_URI_STATE, STATE_NAME).build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var result = controllerInstance()
                .readState(exchange, STATE_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::validate)
                .block();
    }

    // ----------------------------------------------------------------------------------------- /.../{stateName}/cities
    @DisplayName("GET " + REQUEST_URI_CITIES)
    @Test
    void cities__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(REQUEST_URI_CITIES, STATE_NAME).build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var cities = controllerInstance()
                .readCities(exchange, STATE_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        cities.doOnNext(this::validate).blockLast();
    }

    // ------------------------------------------------------------------------------------------ /.../cities/{cityName}
    @DisplayName("GET " + REQUEST_URI_CITY)
    @Test
    void city__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(REQUEST_URI_CITY, STATE_NAME, CITY_NAME).build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var result = controllerInstance()
                .readCity(exchange, STATE_NAME, CITY_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::validate)
                .block();
    }

    // ----------------------------------------------------------------------------------- /.../districts/{districtName}
    @DisplayName("GET " + REQUEST_URI_DISTRICTS)
    @Test
    void districts__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(REQUEST_URI_DISTRICTS, STATE_NAME, CITY_NAME).build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var districts = controllerInstance()
                .readDistricts(exchange, STATE_NAME, CITY_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        districts
                .doOnNext(this::validate)
                .blockLast();
    }

    // ----------------------------------------------------------------------------------- /.../districts/{districtName}
    @DisplayName("GET " + REQUEST_URI_DISTRICT)
    @Test
    void district__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest
                        .get(REQUEST_URI_DISTRICT, STATE_NAME, CITY_NAME, DISTRICT_NAME)
                        .build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var result = controllerInstance()
                .readDistrict(exchange, STATE_NAME, CITY_NAME, DISTRICT_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::validate)
                .block();
    }

    // ----------------------------------------------------------------------------------- /.../{districtName}/addresses
    @DisplayName("GET " + REQUEST_URI_DISTRICT_ADDRESSES)
    @Test
    void districtAddresses__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(
                                REQUEST_URI_DISTRICT_ADDRESSES,
                                STATE_NAME,
                                CITY_NAME,
                                DISTRICT_NAME
                        )
                        .build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var addresses = controllerInstance()
                .readDistrictAddresses(exchange, STATE_NAME, CITY_NAME, DISTRICT_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        addresses
                .doOnNext(this::validate)
                .blockLast();
    }

    // ------------------------------------------------------------------------------------------- /.../roads/{roadName}
    @DisplayName("GET " + REQUEST_URI_ROADS)
    @Test
    void roads__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(REQUEST_URI_ROADS, STATE_NAME, CITY_NAME).build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var roads = controllerInstance()
                .readRoads(exchange, STATE_NAME, CITY_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        roads
                .doOnNext(this::validate)
                .blockLast();
    }

    // ------------------------------------------------------------------------------------------- /.../roads/{roadName}
    @DisplayName("GET " + REQUEST_URI_ROAD)
    @Test
    void road__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest
                        .get(REQUEST_URI_ROAD, STATE_NAME, CITY_NAME, ROAD_NAME)
                        .build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var result = controllerInstance()
                .readRoad(exchange, STATE_NAME, CITY_NAME, ROAD_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        result.doOnNext(this::validate)
                .block();
    }

    // ----------------------------------------------------------------------------------- /.../{roadName}/addresses

    /**
     * Tests {@link RetrieveEngAddressServiceApiController#readRoadAddresses(ServerWebExchange, String, String, String)}
     * method.
     */
    @DisplayName("GET " + REQUEST_URI_ROAD_ADDRESSES)
    @Test
    void roadAddresses__() {
        // ------------------------------------------------------------------------------------------------------- given
        final var exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(
                                REQUEST_URI_ROAD_ADDRESSES,
                                STATE_NAME,
                                CITY_NAME,
                                ROAD_NAME
                        )
                        .build()
        );
        // -------------------------------------------------------------------------------------------------------- when
        final var addresses = controllerInstance()
                .readRoadAddresses(exchange, STATE_NAME, CITY_NAME, ROAD_NAME)
                .switchIfEmpty(Mono.error(new RuntimeException("empty")));
        // -------------------------------------------------------------------------------------------------------- then
        addresses
                .doOnNext(this::validate)
                .blockLast();
    }
}
