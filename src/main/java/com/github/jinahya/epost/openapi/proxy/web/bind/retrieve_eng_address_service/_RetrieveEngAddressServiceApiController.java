package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse.LandAddressEngSearchList;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Predicate;

import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.PATH_NAME_DISTRICT_NAME;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.REL_CITIES;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE;
import static com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service.__RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES;

@Hidden
@Tag(name = __RetrieveEngAddressServiceApiConstants.TAG)
@Validated
@RestController
//@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
@SuppressWarnings({
        "java:S101" // class _Retrieve...
})
class _RetrieveEngAddressServiceApiController
        extends _ApiController {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    _RetrieveEngAddressServiceApiController() {
        super();
    }

    // ----------------------------------------------------------------------------------------------------- /.../states
    private Mono<StateEngListResponse> exchange() {
        return new StateEngListRequest()
                .exchange(webClient())
                ;
    }

    private Flux<StateEngListResponse.StateEngList> statePublisher(
            final Predicate<? super StateEngListResponse.StateEngList> filter) {
        return exchange()
                .flatMapMany(r -> Flux.fromIterable(r.getStateEngList()))
                .filter(filter)
                ;
    }

    private Iterable<Link> links(final StateEngListResponse.StateEngList stateEngList) {
        return List.of(
                Link.of(
                        UriComponentsBuilder.fromPath(REQUEST_URI_STATE)
                                .build(stateEngList.getStateEngName())
                                .toString(),
                        IanaLinkRelations.SELF
                ),
                Link.of(
                        UriComponentsBuilder.fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES)
                                .build(stateEngList.getStateEngName())
                                .toString(),
                        LinkRelation.of(REL_CITIES)
                )
        );
    }

    private RepresentationModel<EntityModel<StateEngListResponse.StateEngList>> model(
            StateEngListResponse.StateEngList stateEngList) {
        return HalModelBuilder.halModelOf(stateEngList).links(links(stateEngList)).build();
    }

    @Operation(summary = "Reads all states.")
    @GetMapping(
            path = REQUEST_URI_STATES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RepresentationModel<EntityModel<StateEngListResponse.StateEngList>>> readStates(
            final ServerWebExchange exchange) {
        return statePublisher(v -> true)
                .map(this::model);
    }

    @Operation(summary = "Reads a state.")
    @GetMapping(
            path = REQUEST_URI_STATE,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<RepresentationModel<EntityModel<StateEngListResponse.StateEngList>>> readState(
            final ServerWebExchange exchange,
            @PathVariable(PATH_NAME_STATE_NAME) final String stateName) {
        return statePublisher(s -> s.getStateEngName().equals(stateName))
                .single()
                .map(this::model)
                .onErrorResume(
                        NoSuchElementException.class,
                        t -> {
                            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                            return Mono.empty();
                        }
                );
    }

    // ---------------------------------------------------------------------------------- /.../states/{stateName}/cities
    private Mono<CityEngListResponse> exchange(final String stateName) {
        return CityEngListRequest.of(null, stateName)
                .exchange(webClient());
    }

    private Iterable<Link> links(final String stateName, final CityEngListResponse.CityEngList cityEngList) {
        return List.of(
                Link.of(
                        UriComponentsBuilder.fromPath(REQUEST_URI_STATE)
                                .build(stateName)
                                .toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_STATE
                ),
                Link.of(
                        UriComponentsBuilder.fromPath(REQUEST_URI_CITY)
                                .build(stateName, cityEngList.getCityEngName())
                                .toString(),
                        IanaLinkRelations.SELF
                ),
                Link.of(
                        UriComponentsBuilder.fromPath(REQUEST_URI_ROADS)
                                .build(stateName, cityEngList.getCityEngName())
                                .toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_ROADS
                ),
                Link.of(
                        UriComponentsBuilder.fromPath(REQUEST_URI_DISTRICTS)
                                .build(stateName, cityEngList.getCityEngName())
                                .toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_DISTRICTS
                )
        );
    }

    private RepresentationModel<EntityModel<CityEngListResponse.CityEngList>> model(
            final String stateName, final CityEngListResponse.CityEngList cityEngList) {
        return HalModelBuilder.halModelOf(cityEngList)
                .links(links(stateName, cityEngList))
                .build();
    }

    private Flux<CityEngListResponse.CityEngList> cityEngListPublisher(
            final String statEngName, final Predicate<? super CityEngListResponse.CityEngList> filter) {
        return exchange(statEngName)
                .flatMapMany(r -> Flux.fromIterable(r.getCityEngList()))
                .filter(filter)
                ;
    }

    @Operation(summary = "Reads all cities.", description = "Reads all cities in specified state.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RepresentationModel<EntityModel<CityEngListResponse.CityEngList>>> readCities(
            final ServerWebExchange exchange,
            @PathVariable(name = PATH_NAME_STATE_NAME) final String stateName) {
        return cityEngListPublisher(stateName, c -> true)
                .map(c -> model(stateName, c));
    }

    @Operation(
            summary = "Reads a city.",
            description = "Reads a specific city in a specific state."
    )
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<RepresentationModel<EntityModel<CityEngListResponse.CityEngList>>> readCity(
            @Parameter(description = "the name of the state")
            @PathVariable(name = PATH_NAME_STATE_NAME) final String stateName,
            @Parameter(description = "the name of the city")
            @PathVariable(name = PATH_NAME_CITY_NAME) final String cityName,
            final ServerWebExchange exchange) {
        return cityEngListPublisher(stateName, c -> c.getCityEngName().equals(cityName))
                .map(c -> model(stateName, c))
                .single()
                .onErrorResume(
                        NoSuchElementException.class,
                        t -> {
                            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                            return Mono.empty();
                        }
                );
    }

    // ----------------------------------------------------------------- /.../states/{stateName}/cities/{cityName}/roads
    private Flux<RoadEngListResponse.RoadEngList> roadEngListPublisher(
            final String stateName, final String cityName,
            final Predicate<? super RoadEngFirstNameListResponse.RoadEngFirstNameList> filter1,
            final Predicate<? super RoadEngListResponse.RoadEngList> filter2) {
        return RoadEngFirstNameListRequest.of(null, stateName, cityName)
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getRoadEngFirstNameList()))
                .filter(filter1)
                .map(e -> RoadEngListRequest.of(null, stateName, cityName, e.getRoadEngFirstName()))
                .flatMapSequential(r -> r.exchange(webClient()), 5)
                .flatMap(r -> Flux.fromIterable(r.getRoadEngList()))
                .filter(filter2)
                ;
    }

    private Iterable<Link> links(final String stateName, final String cityName,
                                 final RoadEngListResponse.RoadEngList roadEngList) {
        return List.of(
                Link.of(
                        UriComponentsBuilder
                                .fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY)
                                .build(stateName, cityName).toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_CITY
                ),
                Link.of(
                        UriComponentsBuilder
                                .fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD)
                                .build(stateName, cityName, roadEngList.getRoadEngName())
                                .toString(),
                        IanaLinkRelations.SELF
                ),
                Link.of(
                        UriComponentsBuilder
                                .fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES)
                                .build(stateName, cityName, roadEngList.getRoadEngName())
                                .toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_ADDRESSES
                )
        );
    }

    private RepresentationModel<EntityModel<RoadEngListResponse.RoadEngList>> model(
            final String stateName, final String cityName,
            final RoadEngListResponse.RoadEngList roadEngList) {
        return HalModelBuilder.halModelOf(roadEngList)
                .links(links(stateName, cityName, roadEngList))
                .build()
                ;
    }

    @GetMapping(
            path = REQUEST_URI_ROADS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RepresentationModel<EntityModel<RoadEngListResponse.RoadEngList>>> readRoads(
            final ServerWebExchange exchange,
            @PathVariable(PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = PATH_NAME_CITY_NAME) final String cityName) {
        return roadEngListPublisher(stateName, cityName, v -> true, c -> true)
                .map(r -> model(stateName, cityName, r))
                ;
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<RepresentationModel<EntityModel<RoadEngListResponse.RoadEngList>>> readRoad(
            final ServerWebExchange exchange,
            @PathVariable(PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(name = PATH_NAME_ROAD_NAME) final String roadName) {
        return roadEngListPublisher(
                stateName,
                cityName,
                v -> roadName.toUpperCase().startsWith(v.getRoadEngFirstName()),
                c -> c.getRoadEngName().equals(roadName)
        )
                .map(r -> model(stateName, cityName, r))
                .single()
                .onErrorResume(
                        NoSuchElementException.class,
                        t -> {
                            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                            return Mono.empty();
                        }
                );
    }

    // -------------------------------------------- /.../states/{stateName}/cities/{cityName}/roads/{roadName}/addresses
    private Flux<RoadAddressEngSearchListResponse.RoadAddressEngSearchList> roadAddressEngSearchListPublisher(
            final String stateName, final String cityName, final String roadName,
            final Predicate<? super RoadAddressEngSearchListResponse.RoadAddressEngSearchList> filter) {
        final var total = new AtomicReference<Integer>();
        final var count = new LongAdder();
        return Mono.just(RoadAddressEngSearchListRequest.of(
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
                .flatMapSequential(
                        r -> r.exchange(webClient()),
                        5,
                        1
                )
                .switchOnFirst((s, f) -> {
                    final var cmmMsgHeader = s.get().getCmmMsgHeader();
                    total.compareAndSet(null, cmmMsgHeader.getTotalCount());
                    return f;
                })
                .flatMap(r -> Flux.fromIterable(r.getRoadAddressEngSearchList()))
                .filter(filter)
                .<RoadAddressEngSearchListResponse.RoadAddressEngSearchList>handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                });
    }

    private Iterable<Link> links(
            final String stateName, final String cityName, final String roadName,
            final RoadAddressEngSearchListResponse.RoadAddressEngSearchList roadAddressEngSearchList) {
        return List.of(
                Link.of(
                        UriComponentsBuilder.fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD)
                                .build(stateName, cityName, roadName)
                                .toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_ROAD
                )
        );
    }

    private RepresentationModel<EntityModel<RoadAddressEngSearchListResponse.RoadAddressEngSearchList>> model(
            final String stateName, final String cityName, final String roadName,
            final RoadAddressEngSearchListResponse.RoadAddressEngSearchList roadAddressEngSearchList) {
        return HalModelBuilder.halModelOf(roadAddressEngSearchList)
                .links(links(stateName, cityName, roadName, roadAddressEngSearchList))
                .build();
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RepresentationModel<EntityModel<RoadAddressEngSearchListResponse.RoadAddressEngSearchList>>> readRoadAddresses(
            final ServerWebExchange exchange,
            @PathVariable(PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(PATH_NAME_ROAD_NAME) final String roadName) {
        return roadAddressEngSearchListPublisher(
                stateName,
                cityName,
                roadName,
                v -> true
        )
                .map(v -> model(stateName, cityName, roadName, v));
    }

    // ------------------------------------------------------------- /.../states/{stateName}/cities/{cityName}/districts
    private Flux<DistrictEngListResponse.DistrictEngList> getDistrictEngListPublisher(
            final String stateName, final String cityName,
            final Predicate<? super DistrictEngFirstNameListResponse.DistrictEngFirstNameList> filter1,
            final Predicate<? super DistrictEngListResponse.DistrictEngList> filter2) {
        return DistrictEngFirstNameListRequest.of(null, stateName, cityName)
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getDistrictEngFirstNameList()))
                .filter(filter1)
                .map(e -> DistrictEngListRequest.of(null, stateName, cityName, e.getDistrictEngFirstName()))
                .flatMapSequential(r -> r.exchange(webClient()), 5)
                .flatMap(r -> Flux.fromIterable(r.getDistrictEngList()))
                .filter(filter2);
    }

    private Iterable<Link> links(final String stateName, final String cityName,
                                 final DistrictEngListResponse.DistrictEngList districtEngList) {
        return List.of(
                Link.of(
                        UriComponentsBuilder
                                .fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY)
                                .build(stateName, cityName).toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_CITY
                ),
                Link.of(
                        UriComponentsBuilder
                                .fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT)
                                .build(stateName, cityName, districtEngList.getDistrictEngName())
                                .toString(),
                        IanaLinkRelations.SELF
                ),
                Link.of(
                        UriComponentsBuilder
                                .fromPath(REQUEST_URI_DISTRICT_ADDRESSES)
                                .build(stateName, cityName, districtEngList.getDistrictEngName())
                                .toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_ADDRESSES
                )
        );
    }

    private RepresentationModel<EntityModel<DistrictEngListResponse.DistrictEngList>> model(
            final String stateName, final String cityName,
            final DistrictEngListResponse.DistrictEngList districtEngList) {
        return HalModelBuilder.halModelOf(districtEngList)
                .links(links(stateName, cityName, districtEngList))
                .build()
                ;
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RepresentationModel<EntityModel<DistrictEngListResponse.DistrictEngList>>> readDistricts(
            final ServerWebExchange exchange,
            @PathVariable(PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = PATH_NAME_CITY_NAME) final String cityName) {
        return getDistrictEngListPublisher(stateName, cityName, v -> true, c -> true)
                .map(r -> model(stateName, cityName, r));
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<RepresentationModel<EntityModel<DistrictEngListResponse.DistrictEngList>>> readDistrict(
            final ServerWebExchange exchange,
            @PathVariable(PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(name = PATH_NAME_DISTRICT_NAME) final String districtName) {
        return getDistrictEngListPublisher(
                stateName,
                cityName,
                v -> districtName.toUpperCase().startsWith(v.getDistrictEngFirstName()),
                v -> v.getDistrictEngName().equals(districtName)
        )
                .map(d -> model(stateName, cityName, d))
                .single()
                .onErrorResume(
                        NoSuchElementException.class,
                        t -> {
                            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                            return Mono.empty();
                        }
                );
    }

    // ------------------------------------ /.../states/{stateName}/cities/{cityName}/districts/{districtName}/addresses
    private Flux<LandAddressEngSearchList> districtAddressPublisher(
            final String stateName, final String cityName, final String districtName,
            final Predicate<? super LandAddressEngSearchList> filter) {
        final var total = new AtomicReference<Integer>();
        final var count = new LongAdder();
        return Mono.just(LandAddressEngSearchListRequest.of(
                        null,
                        stateName,
                        cityName,
                        null,
                        districtName,
                        null,
                        32,
                        1
                ))
                .expand(r -> Mono.just(r.forNextPage()))
                .flatMapSequential(r -> r.exchange(webClient()), 5, 1)
                .switchOnFirst((s, f) -> {
                    if (s.hasValue()) {
                        final var cmmMsgHeader = s.get().getCmmMsgHeader();
                        total.compareAndSet(null, cmmMsgHeader.getTotalCount());
                    }
                    return f;
                })
                .flatMap(r -> Flux.fromIterable(r.getLandAddressEngSearchList()))
                .filter(filter)
                .handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                })
                ;
    }

    private Iterable<Link> links(final String stateName, final String cityName, final String roadName,
                                 final LandAddressEngSearchList landAddressEngSearchList) {
        return List.of(
                Link.of(
                        UriComponentsBuilder.fromPath(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD)
                                .build(stateName, cityName, roadName)
                                .toString(),
                        __RetrieveEngAddressServiceApiConstants.REL_ROAD
                )
        );
    }

    private RepresentationModel<EntityModel<LandAddressEngSearchList>> model(
            final String stateName, final String cityName, final String roadName,
            final LandAddressEngSearchList landAddressEngSearchList) {
        return HalModelBuilder.halModelOf(landAddressEngSearchList)
                .links(links(stateName, cityName, roadName, landAddressEngSearchList))
                .build();
    }

    @Operation(summary = "Reads district addresses.", description = "Reads all addresses in specified district.")
    @GetMapping(
            path = REQUEST_URI_DISTRICT_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RepresentationModel<EntityModel<LandAddressEngSearchList>>> readDistrictAddresses(
            final ServerWebExchange exchange,
            @PathVariable(PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(PATH_NAME_DISTRICT_NAME) final String districtName) {
        return districtAddressPublisher(
                stateName,
                cityName,
                districtName,
                v -> true
        )
                .map(v -> model(stateName, cityName, districtName, v));
    }
}
