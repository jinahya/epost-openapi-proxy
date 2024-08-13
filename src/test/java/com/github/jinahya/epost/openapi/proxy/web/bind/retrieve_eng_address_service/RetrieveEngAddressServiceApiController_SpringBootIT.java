package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas.*;
import com.github.jinahya.epost.openapi.proxy.web.bind._WebBindSpringBootIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveEngAddressServiceApiController_SpringBootIT
        extends _WebBindSpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    static List<State> readStates(final WebTestClient client) {
        return client
                .get()
                .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES).build())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                .expectBodyList(State.class)
                .returnResult()
                .getResponseBody();
    }

    static List<City> readCities(final WebTestClient client, final State state) {
        return client
                .get()
                .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES).build(
                        state.getWrapped().getStateEngName()
                ))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                .expectBodyList(City.class)
                .returnResult()
                .getResponseBody();
    }

    static List<Road> readRoads(final WebTestClient client, final State state, final City city) {
        return client
                .get()
                .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS).build(
                        state.getWrapped().getStateEngName(),
                        city.getWrapped().getCityEngName()
                ))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                .expectBodyList(Road.class)
                .returnResult()
                .getResponseBody();
    }

    static List<RoadAddress> readRoadAddresses(final WebTestClient client, final State state, final City city,
                                               final Road road) {
        return client
                .get()
                .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES).build(
                        state.getWrapped().getStateEngName(),
                        city.getWrapped().getCityEngName(),
                        road.getWrapped().getRoadEngName()
                ))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                .expectBodyList(RoadAddress.class)
                .returnResult()
                .getResponseBody();
    }

    static List<District> readDistricts(final WebTestClient client, final State state, final City city) {
        return client
                .get()
                .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS).build(
                        state.getWrapped().getStateEngName(),
                        city.getWrapped().getCityEngName()
                ))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                .expectBodyList(District.class)
                .returnResult()
                .getResponseBody();
    }

    static List<DistrictAddress> readDistrictAddresses(final WebTestClient client, final State state, final City city,
                                                       final District district) {
        return client
                .get()
                .uri(b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES).build(
                        state.getWrapped().getStateEngName(),
                        city.getWrapped().getCityEngName(),
                        district.getWrapped().getDistrictEngName()
                ))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_NDJSON)
                .expectBodyList(DistrictAddress.class)
                .returnResult()
                .getResponseBody();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../states")
    @Test
    void readStates__() {
        final var states = readStates(webTestClient());
        assertThat(states)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid)
        ;
    }

    @DisplayName("GET /.../states/{stateName}/cities")
    @Test
    void readCities__() {
        final var state = readStates(webTestClient()).getFirst();
        final var cities = readCities(webTestClient(), state);
        assertThat(cities)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../states/{stateName}/cities/{cityName}/roads")
    @Test
    void readRoads__() {
        final var state = readStates(webTestClient()).getFirst();
        final var city = readCities(webTestClient(), state).getFirst();
        final var roads = readRoads(webTestClient(), state, city);
        assertThat(roads)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    @DisplayName("GET /.../states/{stateName}/cities/{cityName}/roads/{roadName}/addresses")
    @Test
    void readRoadAddresses__() {
        final var state = readStates(webTestClient()).getFirst();
        final var city = readCities(webTestClient(), state).getFirst();
        final var road = readRoads(webTestClient(), state, city).getFirst();
        final var roadAddresses = readRoadAddresses(webTestClient(), state, city, road);
        assertThat(roadAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../states/{stateName}/cities/{cityName}/districts")
    @Test
    void readDistricts__() {
        final var state = readStates(webTestClient()).getFirst();
        final var city = readCities(webTestClient(), state).getFirst();
        final var districts = readDistricts(webTestClient(), state, city);
        assertThat(districts)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }

    @DisplayName("GET /.../states/{stateName}/cities/{cityName}/districts/{districtName}/addresses")
    @Test
    void readDistrictAddresses__() {
        final var state = readStates(webTestClient()).getFirst();
        final var city = readCities(webTestClient(), state).getFirst();
        final var district = readDistricts(webTestClient(), state, city).getFirst();
        final var districtAddresses = readDistrictAddresses(webTestClient(), state, city, district);
        assertThat(districtAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(this::assertValid);
    }
}
