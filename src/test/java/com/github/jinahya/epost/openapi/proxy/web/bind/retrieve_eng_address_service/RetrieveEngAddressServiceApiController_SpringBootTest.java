package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._NoOp;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriTemplate;
import reactor.core.publisher.Mono;

import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractTypeUtils.unmarshalNoNamespacedInstance;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_FIRST_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_LAND_ADDRESS_SEARCH;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_ADDRESS_SEARCH;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_FIRST_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_NAME_LIST;
import static com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service._RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST;
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
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

//@Disabled
//@EnabledIfEnvironmentVariable(named = _TestConstants.ENVIRONMENT_VARIABLE_SERVICE_KEY, matches = ".+")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = {
        RetrieveEngAddressServiceApiController.class
})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
@SuppressWarnings({
        "java:S125" // / ----------
})
class RetrieveEngAddressServiceApiController_SpringBootTest
        extends _ApiController_SpringBootTest<
        RetrieveEngAddressServiceApiController,
        RetrieveEngAddressServiceApiService> {

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
    @BeforeEach
    void a() {
        when(serviceInstance().exchange(notNull(StateEngListRequest.class))).thenAnswer(i -> {
            try (var resource = StateEngListResponse.class.getResourceAsStream("getStateList_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(StateEngListResponse.class, resource));
            }
        });
        when(serviceInstance().exchange(notNull(CityEngListRequest.class))).thenAnswer(i -> {
            try (var resource = CityEngListResponse.class.getResourceAsStream("getCityList_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(CityEngListResponse.class, resource));
            }
        });
        // -------------------------------------------------------------------------------------------------------------
        when(serviceInstance().exchange(notNull(DistrictEngFirstNameListRequest.class))).thenAnswer(i -> {
            try (var r = _NoOp.class.getResourceAsStream("getDistrictFirstNameList_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(DistrictEngFirstNameListResponse.class, r));
            }
        });
        when(serviceInstance().exchange(notNull(DistrictEngListRequest.class))).thenAnswer(i -> {
            try (var r = _NoOp.class.getResourceAsStream("getDistrictNameList_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(DistrictEngListResponse.class, r));
            }
        });
        when(serviceInstance().exchange(notNull(LandAddressEngSearchListRequest.class))).thenAnswer(i -> {
            try (var r = _NoOp.class.getResourceAsStream("getLandAddressSearch_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(LandAddressEngSearchListResponse.class, r));
            }
        });
        // -------------------------------------------------------------------------------------------------------------
        when(serviceInstance().exchange(notNull(RoadEngFirstNameListRequest.class))).thenAnswer(i -> {
            try (var r = _NoOp.class.getResourceAsStream("getRoadFirstNameList_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(RoadEngFirstNameListResponse.class, r));
            }
        });
        when(serviceInstance().exchange(notNull(RoadEngListRequest.class))).thenAnswer(i -> {
            try (var r = _NoOp.class.getResourceAsStream("getRoadNameList_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(RoadEngListResponse.class, r));
            }
        });
        when(serviceInstance().exchange(notNull(RoadAddressEngSearchListRequest.class))).thenAnswer(i -> {
            try (var r = _NoOp.class.getResourceAsStream("getRoadAddressSearch_response1.xml")) {
                return Mono.just(unmarshalNoNamespacedInstance(RoadAddressEngSearchListResponse.class, r));
            }
        });
    }

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
