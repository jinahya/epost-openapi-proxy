package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.context.ReactiveWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Hidden
@Tag(name = _Constants.TAG)
@Validated
@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class RetrieveEngAddressServiceApiController {

    @EventListener
    void onApplicationEvent(final ReactiveWebServerInitializedEvent event) {
        webClient = WebClient.builder()
                .baseUrl("http://localhost:" + event.getWebServer().getPort())
                .build();
    }

    // ----------------------------------------------------------------------------------------------------- /.../states
    @Operation(summary = "Reads all states.")
    @GetMapping(
            path = _RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<State> readStates() {
//        return new StateEngListRequest()
//                .exchange(webClient)
//                .flatMapMany(r -> Flux.fromIterable(r.getStateEngList()))
//                .map(State::stateOf)
//                ;
        throw new UnsupportedOperationException("");
    }

    // ---------------------------------------------------------------------------------- /.../states/{stateName}/cities
    @Operation(summary = "Reads all cities.", description = "Reads all cities in specified state.")
    @GetMapping(
            path = _RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<City> readCities(
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName) {
        throw new UnsupportedOperationException("");
    }

    // ----------------------------------------------------------------- /.../states/{stateName}/cities/{cityName}/roads
    @GetMapping(
            path = _RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<Road> readRoads(
            @PathVariable(_RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName) {
        throw new UnsupportedOperationException("");
    }

    // -------------------------------------------- /.../states/{stateName}/cities/{cityName}/roads/{roadName}/addresses
    @GetMapping(
            path = _RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RoadAddress> readRoadAddresses(
            @PathVariable(_RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME) final String roadName) {
        throw new UnsupportedOperationException("");
    }

    // ------------------------------------------------------------- /.../states/{stateName}/cities/{cityName}/districts
    @GetMapping(
            path = _RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<District> readDistricts(
            @PathVariable(_RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName) {
        throw new UnsupportedOperationException("");
    }

    // ------------------------------------ /.../states/{stateName}/cities/{cityName}/districts/{districtName}/addresses
    @GetMapping(
            path = _RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<DistrictAddress> readDistrictAddresses(
            @PathVariable(_RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(
                    name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_DISTRICT_NAME) final String districtName) {
        throw new UnsupportedOperationException("");
    }

    // -----------------------------------------------------------------------------------------------------------------
    private WebClient webClient;
}
