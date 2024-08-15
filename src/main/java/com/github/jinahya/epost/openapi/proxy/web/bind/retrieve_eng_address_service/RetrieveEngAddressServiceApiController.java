package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas.*;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

@Hidden
@Tag(name = _Constants.TAG)
@Validated
@RestController
@Slf4j
class RetrieveEngAddressServiceApiController
        extends _ApiController {

    RetrieveEngAddressServiceApiController() {
        super();
    }

    // ----------------------------------------------------------------------------------------------------- /.../states
    @Operation(summary = "Reads all states.")
    @GetMapping(
            path = _RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readStates(final ServerHttpResponse response) {
        return writeNdjsonResponseWith(
                response,
                new StateEngListRequest()
                        .exchange(webClient())
                        .flatMapMany(r -> Flux.fromIterable(r.getStateEngList()))
                        .map(State::ofWrapped)
                        .map(State::addLinks),
                State.class
        );
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
    Mono<Void> readRoadAddresses(
            @PathVariable(_RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(name = _RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME) final String roadName,
            final ServerHttpResponse response) {
        final var count = new LongAdder();
        final var total = new AtomicInteger();
        final var data = Mono.just(RoadAddressEngSearchListRequest.of(
                        null,
                        stateName,
                        cityName,
                        null,
                        roadName,
                        null,
                        32,
                        1
                ))
                .expand(r -> Mono.just(r.forNextPage()))
                .flatMapSequential(r -> r.exchange(webClient()), 5, 1)
                .switchOnFirst((s, f) -> {
                    final var cmmMsgHeader = s.get().getCmmMsgHeader();
                    total.set(cmmMsgHeader.getTotalCount());
                    return f;
                })
                .flatMap(r -> {
                             final var list = r.getRoadAddressEngSearchList();
                             count.add(list.size());
                             return Flux.fromIterable(list)
                                     .map(RoadAddress::from)
                                     .map(RoadAddress::addLinks);
                         }
                )
                .takeWhile(e -> count.sum() < total.longValue());
        return writeNdjsonResponseWith(response, data, RoadAddress.class);
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
}
