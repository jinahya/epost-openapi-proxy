package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse.CityEngList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse.DistrictEngList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse.LandAddressEngSearchList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse.RoadAddressEngSearchList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse.RoadEngList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse.StateEngList;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Objects;
import java.util.Optional;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class RetrieveEngAddressServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<RetrieveEngAddressServiceApiController> {

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
    private <T> void validate(final T content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    private <T> void validate(final EntityModel<T> model) {
        Objects.requireNonNull(model, "model is null");
        validate(Objects.requireNonNull(model.getContent(), "model.content is null"));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static StateEngList state = null;

    private static StateEngList state() {
        return Optional.ofNullable(state).orElseGet(() -> {
            state = mock(StateEngList.class);
            given(state.getStateEngName()).willReturn("Jeollanam-do");
            return state;
        });
    }

    @Order(ORDER_STATES)
    @DisplayName("GET /.../states")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readStates__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var states = readList(
                StateEngList.class,
                b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES).build(),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(states)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> {
                    validate(m);
                });
        state = getRandomContent(states);
    }

    @Order(ORDER_STATE)
    @DisplayName("GET /.../states/{stateName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readState__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var state = readSingle(
                StateEngList.class,
                b -> b.path(_RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE)
                        .build(state().getStateEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        validate(state);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static CityEngList city;

    private static CityEngList city() {
        return Optional.ofNullable(city).orElseGet(() -> {
            city = mock(CityEngList.class);
            given(city.getCityEngName()).willReturn("Naju-si");
            return city;
        });
    }

    @Order(ORDER_CITIES)
    @DisplayName("GET /.../{stateName}/cities")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readCities__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var cities = readList(
                CityEngList.class,
                b -> b.path(REQUEST_URI_CITIES).build(state().getStateEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(cities)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> {
                    validate(m);
                });
        city = getRandomContent(cities);
    }

    @Order(ORDER_CITY)
    @DisplayName("GET /.../cities/{cityName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readCity__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var city = readSingle(
                CityEngList.class,
                b -> b.path(REQUEST_URI_CITY).build(state().getStateEngName(), city().getCityEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        validate(city);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static RoadEngList road;

    private RoadEngList road() {
        return Optional.ofNullable(road).orElseGet(() -> {
            road = mock(RoadEngList.class);
            given(road.getRoadEngName()).willReturn("Daeho-gil");
            return road;
        });
    }

    @Order(ORDER_ROADS)
    @DisplayName("GET /.../{cityName}/roads")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoads__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var roads = readList(
                RoadEngList.class,
                b -> b.path(REQUEST_URI_ROADS).build(state().getStateEngName(), city().getCityEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(roads)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> validate(m));
        road = getRandomContent(roads);
    }

    @Order(ORDER_ROAD)
    @DisplayName("GET /.../roads/{roadName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readRoad__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var road = readSingle(
                RoadEngList.class,
                b -> b.path(REQUEST_URI_ROAD)
                        .build(state().getStateEngName(), city().getCityEngName(), road().getRoadEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        validate(road);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Order(ORDER_ROAD_ADDRESSES)
    @DisplayName("GET /.../{roadName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readRoadAddresses__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var roadAddresses = readList(
                RoadAddressEngSearchList.class,
                b -> b.path(REQUEST_URI_ROAD_ADDRESSES)
                        .build(state().getStateEngName(), city().getCityEngName(), road().getRoadEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(roadAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> {
                    validate(m);
                });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static DistrictEngList district;

    private DistrictEngList district() {
        return Optional.ofNullable(district).orElseGet(() -> {
            district = mock(DistrictEngList.class);
            given(district.getDistrictEngName()).willReturn("Daeho-dong");
            return district;
        });
    }

    @Order(ORDER_DISTRICTS)
    @DisplayName("GET /.../{cityName}/districts")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistricts__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var districts = readList(
                DistrictEngList.class,
                b -> b.path(REQUEST_URI_DISTRICTS).build(state().getStateEngName(), city().getCityEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        assertThat(districts)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(v -> {
                    validate(v);
                });
        // -------------------------------------------------------------------------------------------------------- then
        district = getRandomContent(districts);
    }

    @Order(ORDER_DISTRICT)
    @DisplayName("GET /.../districts/{districtName}")
    @MethodSource({"getMediaTypeStreamForItem"})
    @ParameterizedTest
    void readDistrict__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var district = readSingle(
                DistrictEngList.class,
                b -> b.path(REQUEST_URI_DISTRICT)
                        .build(state().getStateEngName(), city().getCityEngName(), district().getDistrictEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        validate(district);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Order(ORDER_DISTRICT_ADDRESSES)
    @DisplayName("GET /.../{districtName}/addresses")
    @MethodSource({"getMediaTypeStreamForCollection"})
    @ParameterizedTest
    void readDistrictAddresses__(final String accept) {
        // -------------------------------------------------------------------------------------------------------- when
        final var districtAddresses = readList(
                LandAddressEngSearchList.class,
                b -> b.path(REQUEST_URI_DISTRICT_ADDRESSES)
                        .build(state().getStateEngName(), city().getCityEngName(), district().getDistrictEngName()),
                accept,
                new TypeReferences.EntityModelType<>() {
                }
        );
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(districtAddresses)
                .isNotEmpty()
                .doesNotContainNull()
                .allSatisfy(m -> {
                    validate(m);
                });
    }
}
