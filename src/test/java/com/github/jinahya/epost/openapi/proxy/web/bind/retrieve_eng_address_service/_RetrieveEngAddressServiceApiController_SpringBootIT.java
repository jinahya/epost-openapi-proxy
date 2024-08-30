package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class _RetrieveEngAddressServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<_RetrieveEngAddressServiceApiController> {

    // -----------------------------------------------------------------------------------------------------------------
    static List<State> readStates(final WebTestClient client, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES)
                                .build())
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(State.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    static State readState(final WebTestClient client, final State state, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE)
                                .build(state.name()))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(State.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<City> readCities(final WebTestClient client, final State state, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES).build(
                                state.getWrapped().getStateEngName()
                        ))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(City.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    static City readCity(final WebTestClient client, final City city, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY)
                                .build(city.getStateName(), city.name()))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(City.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    static List<Road> readRoads(final WebTestClient client, final State state, final City city,
                                @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS).build(
                                state.getWrapped().getStateEngName(),
                                city.getWrapped().getCityEngName()
                        ))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(Road.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    static List<RoadAddress> readRoadAddresses(final WebTestClient client, final State state, final City city,
                                               final Road road, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES).build(
                                state.getWrapped().getStateEngName(),
                                city.getWrapped().getCityEngName(),
                                road.getWrapped().getRoadEngName()
                        ))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(RoadAddress.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    static List<District> readDistricts(final WebTestClient client, final State state, final City city,
                                        @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS).build(
                                state.getWrapped().getStateEngName(),
                                city.getWrapped().getCityEngName()
                        ))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(District.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    static List<DistrictAddress> readDistrictAddresses(final WebTestClient client, final State state, final City city,
                                                       final District district, @Nullable final String accept) {
        return Objects.requireNonNull(
                client
                        .get()
                        .uri(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES).build(
                                state.getWrapped().getStateEngName(),
                                city.getWrapped().getCityEngName(),
                                district.getWrapped().getDistrictEngName()
                        ))
                        .headers(h -> {
                            Optional.ofNullable(accept)
                                    .map(MediaType::valueOf)
                                    .map(List::of)
                                    .ifPresent(h::setAccept);
                        })
                        .exchange()
                        .expectStatus().isOk()
//                        .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                        .expectBodyList(DistrictAddress.class)
                        .returnResult()
                        .getResponseBody()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getMediaTypeStreamForCollection() {
        return Stream.of(
                null,
                MediaType.APPLICATION_NDJSON_VALUE,
                MediaTypes.HAL_JSON_VALUE
        );
    }

    private static Stream<String> getMediaTypeStreamForItem() {
        return Stream.of(
                null,
                MediaTypes.HAL_JSON_VALUE
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static State state;

    @DisplayName("GET /.../states")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readStates__(final String mediaType) {
        final var states = readStates(webTestClient(), mediaType);
        assertThat(states)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
        state = states.getLast();
    }

    private State state(final String accept) {
        return Optional.ofNullable(state)
                .orElseGet(() -> state = readStates(webTestClient(), accept).getLast());
    }

    @DisplayName("GET /.../states/{stateName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readState__(final String accept) {
        final var state = readState(webTestClient(), state(accept), accept);
        assertThat(state)
                .satisfies(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static City city;

    @DisplayName("GET /.../{stateName}/cities")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readCities__(final String accept) {
        final var state = readStates(webTestClient(), accept).getFirst();
        final var cities = readCities(webTestClient(), state, accept);
        assertThat(cities)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    private City city(final String accept) {
        return Optional.ofNullable(city)
                .orElseGet(() -> city = readCities(webTestClient(), state(accept), accept).getLast());
    }

    @DisplayName("GET /.../cities/{cityName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readCity__(final String accept) {
        final var city = readCity(webTestClient(), city(accept), accept);
        assertThat(city)
                .satisfies(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../roads")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoads__(final String accept) {
        final var state = readStates(webTestClient(), accept).getFirst();
        final var city = readCities(webTestClient(), state, accept).getFirst();
        final var roads = readRoads(webTestClient(), state, city, accept);
        assertThat(roads)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    @DisplayName("GET /.../{roadName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoadAddresses__(final String accept) {
        final var state = readStates(webTestClient(), accept).getFirst();
        final var city = readCities(webTestClient(), state, accept).getFirst();
        final var road = readRoads(webTestClient(), state, city, accept).getFirst();
        final var roadAddresses = readRoadAddresses(webTestClient(), state, city, road, accept);
        assertThat(roadAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../districts")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistricts__(final String accept) {
        final var state = readStates(webTestClient(), accept).getFirst();
        final var city = readCities(webTestClient(), state, accept).getFirst();
        final var districts = readDistricts(webTestClient(), state, city, accept);
        assertThat(districts)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    @DisplayName("GET /.../{districtName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistrictAddresses__(final String accept) {
        final var state = readStates(webTestClient(), accept).getFirst();
        final var city = readCities(webTestClient(), state, accept).getFirst();
        final var district = readDistricts(webTestClient(), state, city, accept).getFirst();
        final var districtAddresses = readDistrictAddresses(webTestClient(), state, city, district, accept);
        assertThat(districtAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }
}
