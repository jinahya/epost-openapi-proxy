package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse.CityEngList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse.DistrictEngList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse.LandAddressEngSearchList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse.RoadAddressEngSearchList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse.RoadEngList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse.StateEngList;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service._RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class RetrieveEngAddressServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<RetrieveEngAddressServiceApiController> {

    // 왜 안되는지 잘 모르겠다.
    // https://stackoverflow.com/q/78942661/330457
    // https://github.com/spring-projects/spring-hateoas/issues/2211
    private static <T> List<EntityModel<T>> readList(final WebTestClient client,
                                                     final Function<UriBuilder, URI> uriFunction,
                                                     @Nullable final String accept,
                                                     final Class<T> type) {
        final var responseBody = client
                .get()
                .uri(uriFunction)
                .headers(h -> {
                    Optional.ofNullable(accept)
                            .map(MediaType::valueOf)
                            .map(List::of)
                            .ifPresent(h::setAccept);
                })
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(new TypeReferences.EntityModelType<T>() {
                })
//                .<EntityModel<T>>expectBodyList(TypeReferences.EntityModelType.<EntityModel<T>>forType(type))
                .returnResult()
                .getResponseBody();
        return Objects.requireNonNull(responseBody, "responseBody is null");
    }

    private static <T> EntityModel<T> readSingle(final WebTestClient client,
                                                 final Function<UriBuilder, URI> uriFunction,
                                                 @Nullable final String accept) {
        final var responseBody = client
                .get()
                .uri(uriFunction)
                .headers(h -> {
                    Optional.ofNullable(accept)
                            .map(MediaType::valueOf)
                            .map(List::of)
                            .ifPresent(h::setAccept);
                })
                .exchange()
                .expectStatus().isOk()
                .expectBody(new TypeReferences.EntityModelType<T>() {
                })
                .returnResult()
                .getResponseBody();
        return Objects.requireNonNull(responseBody, "responseBody is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<StateEngList>> readStates(final WebTestClient client,
                                                      @Nullable final String accept) {
        if (false) {
            return readList(
                    client,
                    b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES).build(),
                    accept,
                    StateEngList.class
            );
        }
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
                        .expectBodyList(new TypeReferences.EntityModelType<StateEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<StateEngList> readState(final WebTestClient client,
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
                        .expectBody(new TypeReferences.EntityModelType<StateEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<CityEngList>> readCities(final WebTestClient client,
                                                     final String stateName,
                                                     @Nullable final String accept) {
        if (false) {
            return readList(
                    client,
                    b -> b.path(REQUEST_URI_CITIES).build(stateName),
                    accept,
                    CityEngList.class
            );
        }
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_CITIES).build(stateName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(new TypeReferences.EntityModelType<CityEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<CityEngList> readCity(final WebTestClient client, final String stateName,
                                             final String cityName,
                                             @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_CITY).build(stateName, cityName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(new TypeReferences.EntityModelType<CityEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<RoadEngList>> readRoads(final WebTestClient client,
                                                    final String stateName, final String cityName,
                                                    @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_ROADS).build(stateName, cityName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(new TypeReferences.EntityModelType<RoadEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<RoadEngList> readRoad(final WebTestClient client, final String stateName,
                                             final String cityName, final String roadName,
                                             @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_ROAD).build(stateName, cityName, roadName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(new TypeReferences.EntityModelType<RoadEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static List<EntityModel<RoadAddressEngSearchList>> readRoadAddresses(
            final WebTestClient client, String stateName, final String cityName,
            final String roadName, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_ROAD_ADDRESSES).build(stateName, cityName, roadName))
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
                                        RoadAddressEngSearchList>() {
                                })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<EntityModel<DistrictEngList>> readDistricts(final WebTestClient client,
                                                            final String stateName,
                                                            final String cityName,
                                                            @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_DISTRICTS).build(stateName, cityName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(new TypeReferences.EntityModelType<DistrictEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static EntityModel<DistrictEngList> readDistrict(final WebTestClient client,
                                                     final String stateName,
                                                     final String cityName,
                                                     final String districtName,
                                                     @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_DISTRICT).build(stateName, cityName, districtName))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(new TypeReferences.EntityModelType<DistrictEngList>() {
                        })
                        .returnResult()
                        .getResponseBody()
        );
    }

    static List<EntityModel<LandAddressEngSearchList>> readDistrictAddresses(
            final WebTestClient client, String stateName, final String cityName,
            final String districtName, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(REQUEST_URI_DISTRICT_ADDRESSES).build(stateName, cityName, districtName))
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
                                        LandAddressEngSearchList>() {
                                })
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static <T> T getRandom(final List<T> list) {
        Collections.shuffle(list);
        return list.getFirst();
    }

    @SuppressWarnings({
            "java:S6204" // STREAM#collect(Collectors.toList()) <> Stream#toList()
    })
    private static <T> T getRandomContent(final List<EntityModel<T>> list) {
        return getRandom(list.stream().map(EntityModel::getContent).collect(Collectors.toList()));
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
    private static StateEngList _state = null;

    private void validateState(final StateEngList content) {
        Objects.requireNonNull(content, "content is null");
    }

    private void validateState(final EntityModel<StateEngList> model) {
        Objects.requireNonNull(model, "model is null");
//        final var content = model.getContent();
        validateState(model.getContent());
    }

    @Order(ORDER_STATES)
    @DisplayName("GET /.../states")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readStates__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var states = readStates(webTestClient(), accept);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(states)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> {
//                    log.debug("m: {}", m);
//                    final var c = m.getContent();
//                    log.debug("c: {}", c);
                    validateState(m);
                });
//        _state = getRandomContent(states);
    }

    @Order(ORDER_STATE)
    @DisplayName("GET /.../states/{stateName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readState__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        // -------------------------------------------------------------------------------------------------------- when
        final var state = readState(webTestClient(), _state.getStateEngName(), accept);
        // -------------------------------------------------------------------------------------------------------- then
        validateState(state);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static CityEngList _city;

    private void validate(final CityEngList content) {
        Objects.requireNonNull(content, "content is null");
    }

    private void validateCity(final EntityModel<CityEngList> model) {
        Objects.requireNonNull(model, "model is null");
        validate(model.getContent());
    }

    @Order(ORDER_CITIES)
    @DisplayName("GET /.../{stateName}/cities")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readCities__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        // -------------------------------------------------------------------------------------------------------- when
        final var cities = readCities(webTestClient(), _state.getStateEngName(), accept);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(cities)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> {
                    validateCity(m);
                });
        {
            final var list = cities.stream().map(EntityModel::getContent).collect(Collectors.toList());
            Collections.shuffle(list);
            _city = list.getFirst();
        }
    }

    @Order(ORDER_CITY)
    @DisplayName("GET /.../cities/{cityName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readCity__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        final var city = readCity(webTestClient(), _state.getStateEngName(), _city.getCityEngName(), accept);
        validateCity(city);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static RoadEngList _road;

    private void validate(final RoadEngList content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    private void validateRoad(final EntityModel<RoadEngList> model) {
        Objects.requireNonNull(model, "model is null");
        validate(model.getContent());
    }

    @Order(ORDER_ROADS)
    @DisplayName("GET /.../{cityName}/roads")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoads__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        final var roads = readRoads(webTestClient(), _state.getStateEngName(), _city.getCityEngName(), accept);
        assertThat(roads)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> validateRoad(m));
        {
            final var list = roads.stream().map(EntityModel::getContent).collect(Collectors.toList());
            Collections.shuffle(list);
            _road = list.getFirst();
        }
    }

    @Order(ORDER_ROAD)
    @DisplayName("GET /.../roads/{roadName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readRoad__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        Assumptions.assumeTrue(_road != null);
        final var road = readRoad(
                webTestClient(),
                _state.getStateEngName(),
                _city.getCityEngName(),
                _road.getRoadEngName(),
                accept
        );
        validateRoad(road);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void validate(final RoadAddressEngSearchList content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    private void validateRoadAddress(final EntityModel<RoadAddressEngSearchList> model) {
        Objects.requireNonNull(model, "model is null");
        validate(model.getContent());
    }

    @Order(ORDER_ROAD_ADDRESSES)
    @DisplayName("GET /.../{roadName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoadAddresses__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        Assumptions.assumeTrue(_road != null);
        // -------------------------------------------------------------------------------------------------------- when
        final var roadAddresses = readRoadAddresses(
                webTestClient(),
                _state.getStateEngName(),
                _city.getCityEngName(),
                _road.getRoadEngName(),
                accept
        );
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(roadAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::validateRoadAddress);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static DistrictEngList _district;

    private void validate(final DistrictEngList content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    private void validateDistrict(final EntityModel<DistrictEngList> model) {
        Objects.requireNonNull(model, "model is null");
        validate(model.getContent());
    }

    @Order(ORDER_DISTRICTS)
    @DisplayName("GET /.../{cityName}/districts")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistricts__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        // -------------------------------------------------------------------------------------------------------- when
        final var districts = readDistricts(webTestClient(), _state.getStateEngName(), _city.getCityEngName(), accept);
        assertThat(districts)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(v -> validateDistrict(v));
        // -------------------------------------------------------------------------------------------------------- then
        _district = getRandomContent(districts);
    }

    @Order(ORDER_DISTRICT)
    @DisplayName("GET /.../districts/{districtName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readDistrict__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        Assumptions.assumeTrue(_district != null);
        // -------------------------------------------------------------------------------------------------------- when
        final var district = readDistrict(
                webTestClient(),
                _state.getStateEngName(),
                _city.getCityEngName(),
                _district.getDistrictEngName(),
                accept
        );
        // -------------------------------------------------------------------------------------------------------- then
        validateDistrict(district);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void validate(final LandAddressEngSearchList content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    private void validateDistrictAddress(final EntityModel<LandAddressEngSearchList> model) {
        Objects.requireNonNull(model, "model is null");
        validate(model.getContent());
    }

    @Order(ORDER_DISTRICT_ADDRESSES)
    @DisplayName("GET /.../{districtName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistrictAddresses__(final String accept) {
        Assumptions.assumeTrue(_state != null);
        Assumptions.assumeTrue(_city != null);
        Assumptions.assumeTrue(_district != null);
        // -------------------------------------------------------------------------------------------------------- when
        final var districtAddresses = readDistrictAddresses(
                webTestClient(),
                _state.getStateEngName(),
                _city.getCityEngName(),
                _district.getDistrictEngName(),
                accept
        );
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(districtAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> {
                    validateDistrictAddress(m);
                });
    }
}
