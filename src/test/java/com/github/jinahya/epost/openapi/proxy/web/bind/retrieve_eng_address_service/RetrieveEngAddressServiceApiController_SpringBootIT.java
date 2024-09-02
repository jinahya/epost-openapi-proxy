package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RetrieveEngAddressServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<RetrieveEngAddressServiceApiController> {

    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<StateEngListResponse.StateEngList>> readStates(final WebTestClient client,
                                                                           @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES)
                                .build())
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(new TypeReferences.EntityModelType<StateEngListResponse.StateEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<StateEngListResponse.StateEngList> readState(final WebTestClient client,
                                                                    final String stateName,
                                                                    @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE)
                                .build(stateName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(new TypeReferences.EntityModelType<StateEngListResponse.StateEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<CityEngListResponse.CityEngList>> readCities(final WebTestClient client,
                                                                         final String stateName,
                                                                         @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES)
                                .build(stateName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(new TypeReferences.EntityModelType<CityEngListResponse.CityEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<CityEngListResponse.CityEngList> readCity(final WebTestClient client, final String stateName,
                                                                 final String cityName,
                                                                 @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY)
                                .build(stateName, cityName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(new TypeReferences.EntityModelType<CityEngListResponse.CityEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<RoadEngListResponse.RoadEngList>> readRoads(final WebTestClient client,
                                                                        final String stateName, final String cityName,
                                                                        @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS)
                                .build(stateName, cityName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(new TypeReferences.EntityModelType<RoadEngListResponse.RoadEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<RoadEngListResponse.RoadEngList> readRoad(final WebTestClient client, final String stateName,
                                                                 final String cityName, final String roadName,
                                                                 @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD)
                                .build(stateName, cityName, roadName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(new TypeReferences.EntityModelType<RoadEngListResponse.RoadEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static List<EntityModel<RoadAddressEngSearchListResponse.RoadAddressEngSearchList>> readRoadAddresses(
            final WebTestClient client, String stateName, final String cityName,
            final String roadName, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES)
                                .build(stateName, cityName, roadName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(
                                new TypeReferences.EntityModelType<
                                        RoadAddressEngSearchListResponse.RoadAddressEngSearchList>() {
                                })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<DistrictEngListResponse.DistrictEngList>> readDistricts(final WebTestClient client,
                                                                                    final String stateName,
                                                                                    final String cityName,
                                                                                    @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS)
                                .build(stateName, cityName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(new TypeReferences.EntityModelType<DistrictEngListResponse.DistrictEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<DistrictEngListResponse.DistrictEngList> readDistrict(final WebTestClient client,
                                                                             final String stateName,
                                                                             final String cityName,
                                                                             final String districtName,
                                                                             @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT)
                                .build(stateName, cityName, districtName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(new TypeReferences.EntityModelType<DistrictEngListResponse.DistrictEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static List<EntityModel<LandAddressEngSearchListResponse.LandAddressEngSearchList>> readDistrictAddresses(
            final WebTestClient client, String stateName, final String cityName,
            final String districtName, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES)
                                .build(stateName, cityName, districtName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(
                                new TypeReferences.EntityModelType<
                                        LandAddressEngSearchListResponse.LandAddressEngSearchList>() {
                                })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getMediaTypeStreamForCollection() {
        return Stream.of(
                null,
                MediaType.APPLICATION_NDJSON_VALUE
        );
    }

    private static Stream<String> getMediaTypeStreamForItem() {
        return Stream.of(
                null,
                MediaTypes.HAL_JSON_VALUE
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final int ORDER_STATES = 1;

    private static final int ORDER_STATE = ORDER_STATES + 1;

    // -----------------------------------------------------------------------------------------------------------------
    private static final int ORDER_CITIES = ORDER_STATE + 1;

    private static final int ORDER_CITY = ORDER_CITIES + 1;

    // -----------------------------------------------------------------------------------------------------------------
    private static final int ORDER_ROADS = ORDER_CITY + 1;

    private static final int ORDER_ROAD = ORDER_ROADS + 1;

    private static final int ORDER_ROAD_ADDRESSES = ORDER_ROAD + 1;

    // -----------------------------------------------------------------------------------------------------------------
    private static final int ORDER_DISTRICTS = ORDER_CITY + 1;

    private static final int ORDER_DISTRICT = ORDER_DISTRICTS + 1;

    private static final int ORDER_DISTRICT_ADDRESSES = ORDER_DISTRICT + 1;

    // -----------------------------------------------------------------------------------------------------------------
    private static StateEngListResponse.StateEngList _state = null;

    @Order(ORDER_STATES)
    @DisplayName("GET /.../states")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readStates__(final String accept) {
        final var states = readStates(webTestClient(), accept);
        assertThat(states)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(s -> {
                    assertValid(s);
                });
        final var list = states.stream().map(EntityModel::getContent).collect(Collectors.toList());
        Collections.shuffle(list);
        _state = list.getFirst();
    }

    @Order(ORDER_STATE)
    @DisplayName("GET /.../states/{stateName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readState__(final String accept) {
        final var state = readState(webTestClient(), _state.getStateEngName(), accept);
        assertThat(state)
                .satisfies(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static CityEngListResponse.CityEngList _city;

    @Order(ORDER_CITIES)
    @DisplayName("GET /.../{stateName}/cities")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readCities__(final String accept) {
        final var cities = readCities(webTestClient(), _state.getStateEngName(), accept);
        assertThat(cities)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
        final var list = cities.stream().map(EntityModel::getContent).collect(Collectors.toList());
        Collections.shuffle(list);
        _city = list.getFirst();
    }

    @Order(ORDER_CITY)
    @DisplayName("GET /.../cities/{cityName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readCity__(final String accept) {
        final var city = readCity(webTestClient(), _state.getStateEngName(), _city.getCityEngName(), accept);
        assertThat(city)
                .satisfies(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static RoadEngListResponse.RoadEngList _road;

    @Order(ORDER_ROADS)
    @DisplayName("GET /.../{cityName}/roads")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoads__(final String accept) {
        final var roads = readRoads(webTestClient(), _state.getStateEngName(), _city.getCityEngName(), accept);
        assertThat(roads)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
        final var list = roads.stream().map(EntityModel::getContent).collect(Collectors.toList());
        Collections.shuffle(list);
        _road = list.getFirst();
    }

    @Order(ORDER_ROAD)
    @DisplayName("GET /.../roads/{roadName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readRoad__(final String accept) {
        final var road = readRoad(webTestClient(), _state.getStateEngName(), _city.getCityEngName(),
                                  _road.getRoadEngName(), accept);
        assertThat(road)
                .satisfies(this::assertValid);
    }

    @Order(ORDER_ROAD_ADDRESSES)
    @DisplayName("GET /.../{roadName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoadAddresses__(final String accept) {
        final var roadAddresses = readRoadAddresses(
                webTestClient(),
                _state.getStateEngName(),
                _city.getCityEngName(),
                _road.getRoadEngName(),
                accept
        );
        assertThat(roadAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static DistrictEngListResponse.DistrictEngList _district;

    @Order(ORDER_DISTRICTS)
    @DisplayName("GET /.../{cityName}/districts")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistricts__(final String accept) {
        final var districts = readDistricts(webTestClient(), _state.getStateEngName(), _city.getCityEngName(), accept);
        assertThat(districts)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
        final var list = districts.stream().map(EntityModel::getContent).collect(Collectors.toList());
        Collections.shuffle(list);
        _district = list.getFirst();
    }

    @Order(ORDER_DISTRICT)
    @DisplayName("GET /.../districts/{districtName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readDistrict__(final String accept) {
        final var district = readDistrict(webTestClient(), _state.getStateEngName(), _city.getCityEngName(),
                                          _district.getDistrictEngName(), accept);
        assertThat(district)
                .satisfies(this::assertValid);
    }

    @Order(ORDER_DISTRICT_ADDRESSES)
    @DisplayName("GET /.../{districtName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistrictAddresses__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        Assumptions.assumeTrue(_district != null);
        final var districtAddresses = readDistrictAddresses(
                webTestClient(),
                _state.getStateEngName(),
                _city.getCityEngName(),
                _district.getDistrictEngName(),
                accept
        );
        assertThat(districtAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }
}
