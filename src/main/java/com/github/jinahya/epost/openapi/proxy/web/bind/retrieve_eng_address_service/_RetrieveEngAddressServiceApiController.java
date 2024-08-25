package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListRequest;
import com.github.jinahya.epost.openapi.proxy.util.ReactorContextUtils;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
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

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Hidden
@Tag(name = __RetrieveEngAddressServiceApiConstants.TAG)
@Validated
@RestController
//@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class _RetrieveEngAddressServiceApiController
        extends _ApiController {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    _RetrieveEngAddressServiceApiController() {
        super();
        stateModelAssembler = new RepresentationModelAssemblerSupport<State, State>(
                _RetrieveEngAddressServiceApiController.class, State.class) {
            @Override
            public State toModel(final State entity) {
                return entity;
            }
        };
    }

    // ----------------------------------------------------------------------------------------------------- /.../states
    private Flux<State> getStatesPublisher() {
        return new StateEngListRequest()
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getStateEngList()))
                .map(State::newInstance)
                .flatMap(s -> ReactorContextUtils.getRequestBaseUrl()
                        .map(UriComponentsBuilder::fromHttpUrl)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES))
                        .map(b -> b.pathSegment(s.name()))
                        .map(b -> {
                            s.add(
                                    Link.of(b.cloneBuilder().build().toString())
                                            .withSelfRel()
                            );
                            s.add(
                                    Link.of(b.cloneBuilder()
                                                    .pathSegment(__RetrieveEngAddressServiceApiConstants.REL_CITIES)
                                                    .build()
                                                    .toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_CITIES)
                            );
                            return s;
                        }))
                ;
    }

    //    @Operation(summary = "Reads all states.")
//    @GetMapping(
//            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES,
//            produces = {
//                    MediaType.APPLICATION_NDJSON_VALUE,
//                    MediaTypes.HAL_JSON_VALUE
//            }
//    )
//    Mono<Void> readStates(final ServerHttpRequest exchange, final ServerHttpResponse response) {
//        final var authority = getAuthorityFrom(exchange);
//        log.debug("authority: {}", authority);
//        return writeNdjsonResponseWith(
//                response,
//                getStatesPublisher(),
//                State.class
//        );
//    }
    @Operation(summary = "Reads all states.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Flux<State> readStates(final ServerWebExchange exchange) {
        return getStatesPublisher();
    }

//    @Operation(summary = "Reads a state.")
//    @GetMapping(
//            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE,
//            produces = {
//                    MediaTypes.HAL_JSON_VALUE
//            }
//    )
//    Mono<Void> readState(
//            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
//            final ServerWebExchange exchange) {
//        return getStatesPublisher().filter(s -> s.name().equals(stateName)).single()
//                .onErrorResume(
//                        NoSuchElementException.class,
//                        t -> {
//                            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
//                            return Mono.empty();
//                        }
//                )
//                .flatMap(s -> {
//                    return writeHalJsonResponseWith(
//                            exchange.getResponse(),
//                            s,
//                            State.class
//                    );
//                });
//    }

    @Operation(summary = "Reads a state.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<State> readState(
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            final ServerWebExchange exchange) {
        return getStatesPublisher()
                .filter(s -> s.name().equals(stateName))
                .single()
                .onErrorResume(
                        NoSuchElementException.class,
                        t -> {
                            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                            return Mono.empty();
                        }
                );
    }

    // ---------------------------------------------------------------------------------- /.../states/{stateName}/cities
    private Flux<City> getCitiesPublisher(final String stateName) {
        return CityEngListRequest.of(null, stateName)
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getCityEngList()))
                .map(cel -> City.newInstance(stateName, cel))
                .flatMap(c -> ReactorContextUtils.getRequestBaseUrl()
                        .map(UriComponentsBuilder::fromHttpUrl)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE))
                        .map(b -> {
                            c.add(
                                    Link.of(b.cloneBuilder().build(c.getStateName()).toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_STATE)
                            );
                            return c;
                        }))
                .flatMap(c -> ReactorContextUtils.getRequestBaseUrl()
                        .map(UriComponentsBuilder::fromHttpUrl)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY))
                        .map(b -> {
                            c.add(
                                    Link.of(b.cloneBuilder().build(c.getStateName(), c.name()).toString())
                                            .withSelfRel()
                            );
                            c.add(
                                    Link.of(b.cloneBuilder()
                                                    .pathSegment(__RetrieveEngAddressServiceApiConstants.REL_ROADS)
                                                    .build(c.getStateName(), c.name())
                                                    .toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_ROADS)
                            );
                            c.add(
                                    Link.of(b.cloneBuilder()
                                                    .pathSegment(__RetrieveEngAddressServiceApiConstants.REL_LANDS)
                                                    .build(c.getStateName(), c.name())
                                                    .toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_LANDS)
                            );
                            return c;
                        }))
                ;
    }

//    @Operation(summary = "Reads all cities.", description = "Reads all cities in specified state.")
//    @GetMapping(
//            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES,
//            produces = {
//                    MediaType.APPLICATION_NDJSON_VALUE
//            }
//    )
//    Mono<Void> readCities(
//            @NotBlank
//            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
//            final ServerWebExchange exchange) {
//        return writeNdjsonResponseWith(
//                exchange.getResponse(),
//                getCitiesPublisher(stateName),
//                City.class
//        );
//    }

    @Operation(summary = "Reads all cities.", description = "Reads all cities in specified state.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Flux<City> readCities(
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            final ServerWebExchange exchange) {
        return getCitiesPublisher(stateName);
    }

//    @Operation(
//            summary = "Reads a city.",
//            description = "Reads a specific city in specified state."
//    )
//    @GetMapping(
//            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY,
//            produces = {
//                    MediaType.APPLICATION_NDJSON_VALUE
//            }
//    )
//    Mono<Void> readCity(
//            @Parameter(description = "the name of the state")
//            @NotBlank
//            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
//            @Parameter(description = "the name of the city")
//            @NotBlank
//            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
//            final ServerWebExchange exchange) {
//        return writeNdjsonResponseWith(
//                exchange.getResponse(),
//                getCitiesPublisher(stateName).filter(c -> c.name().equals(cityName)),
//                City.class
//        );
//    }

    @Operation(
            summary = "Reads a city.",
            description = "Reads a specific city in specified state."
    )
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<City> readCity(
            @Parameter(description = "the name of the state")
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @Parameter(description = "the name of the city")
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            final ServerWebExchange exchange) {
        return getCitiesPublisher(stateName)
                .filter(c -> c.name().equals(cityName))
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
    private Flux<Road> getRoadPublisher(final String stateName, final String cityName) {
        final var builder = ReactorContextUtils.getRequestBaseUrl().map(UriComponentsBuilder::fromHttpUrl);
        return RoadEngFirstNameListRequest.of(null, stateName, cityName)
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getRoadEngFirstNameList()))
                .map(e -> RoadEngListRequest.of(null, stateName, cityName, e.getRoadEngFirstName()))
                .flatMapSequential(r -> r.exchange(webClient()), 5)
                .flatMap(r -> Flux.fromIterable(r.getRoadEngList()))
                .map(e -> Road.newInstance(stateName, cityName, e))
                .flatMap(r -> builder.map(UriComponentsBuilder::cloneBuilder)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY))
                        .map(b -> {
                            r.add(
                                    Link.of(b.build(r.getStateName(), r.getCityName()).toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_CITY)
                            );
                            return r;
                        }))
                .flatMap(r -> builder.map(UriComponentsBuilder::cloneBuilder)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD))
                        .map(b -> {
                            r.add(
                                    Link.of(b.build(r.getStateName(), r.getCityName(), r.name()).toString())
                                            .withSelfRel()
                            );
                            r.add(
                                    Link.of(b.pathSegment(__RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
                                                    .build(r.getStateName(), r.getCityName(), r.name())
                                                    .toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
                            );
                            return r;
                        }))
                ;
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Flux<Road> readRoads(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            final ServerWebExchange exchange) {
        return getRoadPublisher(stateName, cityName);
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<Road> readRoad(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME) final String roadName,
            final ServerWebExchange exchange) {
        return getRoadPublisher(stateName, cityName)
                .filter(r -> r.name().equals(roadName))
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
    private Flux<RoadAddress> getRoadAddressPublisher(final String stateName, final String cityName,
                                                      final String roadName) {
        final var builder = ReactorContextUtils.getRequestBaseUrl().map(UriComponentsBuilder::fromHttpUrl);
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
                .flatMap(r -> Flux.fromIterable(r.getRoadAddressEngSearchList())
                        .map(e -> RoadAddress.newInstance(stateName, cityName, roadName, e)))
                .<RoadAddress>handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                })
                .flatMap(a -> builder.map(UriComponentsBuilder::cloneBuilder)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD))
                        .map(b -> {
                            a.add(
                                    Link.of(b.build(a.getStateName(), a.getCityName(), a.getRoadName()).toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_ROAD)
                            );
                            return a;
                        }))
                ;
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Flux<RoadAddress> readRoadAddresses(
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME) final String roadName,
            final ServerWebExchange exchange) {
        return getRoadAddressPublisher(stateName, cityName, roadName);
    }

    // ------------------------------------------------------------- /.../states/{stateName}/cities/{cityName}/districts
    private Flux<District> getDistrictPublisher(final String stateName, final String cityName) {
        final var builder = ReactorContextUtils.getRequestBaseUrl().map(UriComponentsBuilder::fromHttpUrl);
        return DistrictEngFirstNameListRequest.of(null, stateName, cityName)
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getDistrictEngFirstNameList()))
                .map(e -> DistrictEngListRequest.of(null, stateName, cityName, e.getDistrictEngFirstName()))
                .flatMapSequential(r -> r.exchange(webClient()), 5)
                .flatMap(r -> Flux.fromIterable(r.getDistrictEngList()))
                .map(e -> District.newInstance(stateName, cityName, e))
                .flatMap(d -> builder.map(UriComponentsBuilder::cloneBuilder)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY))
                        .map(b -> {
                            d.add(
                                    Link.of(b.build(d.getStateName(), d.getCityName()).toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_CITY)
                            );
                            return d;
                        }))
                .flatMap(d -> builder.map(UriComponentsBuilder::cloneBuilder)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT))
                        .map(b -> {
                            d.add(
                                    Link.of(b.build(d.getStateName(), d.getCityName(), d.name()).toString())
                                            .withSelfRel()
                            );
                            d.add(
                                    Link.of(b.pathSegment(__RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
                                                    .build(d.getStateName(), d.getCityName(), d.name())
                                                    .toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
                            );
                            return d;
                        }))
                ;
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Flux<District> readDistricts(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            final ServerWebExchange exchange) {
        return getDistrictPublisher(stateName, cityName);
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT,
            produces = {
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Mono<District> readDistrict(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @NotBlank
            @PathVariable(
                    name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_DISTRICT_NAME) final String districtName,
            final ServerWebExchange exchange) {
        return getDistrictPublisher(stateName, cityName)
                .filter(d -> d.name().equals(districtName))
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
    private Flux<DistrictAddress> getDistrictAddressPublisher(final String stateName, final String cityName,
                                                              final String districtName) {
        final var builder = ReactorContextUtils.getRequestBaseUrl().map(UriComponentsBuilder::fromHttpUrl);
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
                .flatMap(r -> Flux.fromIterable(r.getLandAddressEngSearchList())
                        .map(e -> DistrictAddress.newInstance(stateName, cityName, districtName, e)))
                .<DistrictAddress>handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                })
                .flatMap(a -> builder.map(UriComponentsBuilder::cloneBuilder)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT))
                        .map(b -> {
                            a.add(
                                    Link.of(b.build(a.getStateName(), a.getCityName(), a.getDistrictName()).toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_DISTRICT)
                            );
                            return a;
                        }))
                ;
    }

    @Operation(summary = "Reads district addresses.", description = "Reads all addresses in specified district.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    Flux<DistrictAddress> readDistrictAddresses(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @NotBlank
            @PathVariable(
                    name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_DISTRICT_NAME) final String districtName,
            final ServerWebExchange exchange) {
        return getDistrictAddressPublisher(stateName, cityName, districtName);
    }

    // =================================================================================================================

    // ----------------------------------------------------------------------------------------------------- /.../cities
    @Hidden
    @Operation(summary = "Reads all cities.", description = "Reads all cities regardless states.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI
                    + '/' + __RetrieveEngAddressServiceApiConstants.REL_CITIES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readAllCities(final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getStatesPublisher()
                        .flatMapSequential(
                                s -> getCitiesPublisher(s.name()),
                                4
                        ),
                City.class
        );
    }

    // ------------------------------------------------------------------------------------------------------ /.../roads
    @Hidden
    @Operation(summary = "Reads all roads.", description = "Reads all cities regardless cities.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI
                    + '/' + __RetrieveEngAddressServiceApiConstants.REL_ROADS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readAllRoads(final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getStatesPublisher()
                        .flatMapSequential(
                                s -> getCitiesPublisher(s.name())
                                        .flatMapSequential(
                                                c -> getRoadPublisher(s.name(), c.name()),
                                                8
                                        ),
                                4
                        ),
                Road.class
        );
    }

    // -------------------------------------------------------------------------------------------------- /.../districts
    @Hidden
    @Operation(summary = "Reads all districts.", description = "Reads all districts regardless cities.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI
                    + '/' + __RetrieveEngAddressServiceApiConstants.REL_DISTRICTS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readAllDistricts(final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getStatesPublisher()
                        .flatMapSequential(
                                s -> getCitiesPublisher(s.name())
                                        .flatMapSequential(
                                                c -> getDistrictPublisher(s.name(), c.name()),
                                                8
                                        ),
                                4
                        ),
                District.class
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final RepresentationModelAssemblerSupport<State, State> stateModelAssembler;
}
