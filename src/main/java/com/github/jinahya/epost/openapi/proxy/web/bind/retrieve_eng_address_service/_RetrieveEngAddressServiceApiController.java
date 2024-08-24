package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListRequest;
import com.github.jinahya.epost.openapi.proxy.reactor.util.context.ContextUtils;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Hidden
@Tag(name = __RetrieveEngAddressServiceApiConstants.TAG)
@Validated
@RestController
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class _RetrieveEngAddressServiceApiController
        extends _ApiController {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- /.../states
    private Flux<State> getStatesPublisher() {
        return new StateEngListRequest()
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getStateEngList()))
                .map(State::instanceOf)
                .flatMap(s -> ContextUtils.getRequestBaseUrl()
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

    @Operation(summary = "Reads all states.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readStates(final ServerHttpRequest request, final ServerHttpResponse response) {
        final var authority = getAuthorityFrom(request);
        log.debug("authority: {}", authority);
        return writeNdjsonResponseWith(
                response,
                getStatesPublisher(),
                State.class
        );
    }

    @Operation(summary = "Reads a state.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATE,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readState(
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getStatesPublisher().filter(s -> s.name().equals(stateName)).take(1L),
                State.class
        );
    }

    // ---------------------------------------------------------------------------------- /.../states/{stateName}/cities
    private Flux<City> getCitiesPublisher(final ServerHttpRequest request, final String stateName) {
        return CityEngListRequest.of(null, stateName)
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getCityEngList()))
                .map(cel -> City.newInstance(stateName, cel))
                .flatMap(c -> ContextUtils.getRequestBaseUrl()
                        .map(UriComponentsBuilder::fromHttpUrl)
                        .map(b -> b.path(__RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY))
                        .map(b -> {
                            c.add(
                                    Link.of(b.cloneBuilder().build(stateName, c.name()).toString())
                                            .withSelfRel()
                            );
                            c.add(
                                    Link.of(b.cloneBuilder()
                                                    .pathSegment(__RetrieveEngAddressServiceApiConstants.REL_ROADS)
                                                    .build(stateName, c.name())
                                                    .toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_ROADS)
                            );
                            c.add(
                                    Link.of(b.cloneBuilder()
                                                    .pathSegment(__RetrieveEngAddressServiceApiConstants.REL_LANDS)
                                                    .build(stateName, c.name())
                                                    .toString())
                                            .withRel(__RetrieveEngAddressServiceApiConstants.REL_LANDS)
                            );
                            return c;
                        }))
                ;
    }

    @Operation(summary = "Reads all cities.", description = "Reads all cities in specified state.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITIES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readCities(
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getCitiesPublisher(exchange.getRequest(), stateName),
                City.class
        );
    }

    @Operation(
            summary = "Reads a city.",
            description = "Reads a specific city in specified state."
    )
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_CITY,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readCity(
            @Parameter(description = "the name of the state")
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @Parameter(description = "the name of the city")
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getCitiesPublisher(exchange.getRequest(), stateName)
                        .filter(c -> Objects.equals(c.getWrapped().getCityEngName(), cityName)),
                City.class
        );
    }

    // ----------------------------------------------------------------- /.../states/{stateName}/cities/{cityName}/roads
    Flux<Road> getRoadPublisher(final ServerHttpRequest request, final String stateName, final String cityName) {
        return RoadEngFirstNameListRequest.of(null, stateName, cityName)
                .exchange(webClient())
                .flatMapMany(refnlr -> Flux.fromIterable(refnlr.getRoadEngFirstNameList()))
                .map(refnl -> RoadEngListRequest.of(null, stateName, cityName, refnl.getRoadEngFirstName()))
                .flatMapSequential(relr -> relr.exchange(webClient()), 5)
                .flatMap(relr -> Flux.fromIterable(relr.getRoadEngList()))
                .map(rel -> Road.newInstance(stateName, cityName, rel))
                .map(e -> e.addLinks(request));
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROADS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readRoads(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getRoadPublisher(exchange.getRequest(), stateName, cityName),
                Road.class
        );
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readRoad(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME) final String roadName,
            final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getRoadPublisher(exchange.getRequest(), stateName, cityName)
                        .filter(r -> Objects.equals(r.getWrapped().getRoadEngName(), roadName)),
                Road.class
        );
    }

    // -------------------------------------------- /.../states/{stateName}/cities/{cityName}/roads/{roadName}/addresses
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_ROAD_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readRoadAddresses(
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME) final String roadName,
            final ServerHttpResponse response) {
        final var total = new AtomicReference<Integer>();
        final var count = new LongAdder();
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
                .flatMap(r -> {
                    final var list = r.getRoadAddressEngSearchList();
                    return Flux.fromIterable(list)
                            .map(e -> RoadAddress.newInstance(stateName, cityName, roadName, e))
                            .map(RoadAddress::addLinks);
                })
                .<RoadAddress>handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                });
        return writeNdjsonResponseWith(response, data, RoadAddress.class);
    }

    // ------------------------------------------------------------- /.../states/{stateName}/cities/{cityName}/districts
    private Flux<District> getDistrictPublisher(final ServerHttpRequest request, final String stateName,
                                                final String cityName) {
        return DistrictEngFirstNameListRequest.of(null, stateName, cityName)
                .exchange(webClient())
                .flatMapMany(r -> Flux.fromIterable(r.getDistrictEngFirstNameList()))
                .map(e -> DistrictEngListRequest.of(null, stateName, cityName, e.getDistrictEngFirstName()))
                .flatMapSequential(r -> r.exchange(webClient()), 5)
                .flatMap(r -> Flux.fromIterable(r.getDistrictEngList()))
                .map(e -> District.newInstance(stateName, cityName, e))
                .map(e -> e.addLinks(request));
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICTS,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readDistricts(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getDistrictPublisher(exchange.getRequest(), stateName, cityName),
                District.class
        );
    }

    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readDistricts(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @NotBlank
            @PathVariable(
                    name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_DISTRICT_NAME) final String districtName,
            final ServerWebExchange exchange) {
        return writeNdjsonResponseWith(
                exchange.getResponse(),
                getDistrictPublisher(exchange.getRequest(), stateName, cityName)
                        .filter(d -> Objects.equals(d.getWrapped().getDistrictEngName(), districtName)),
                District.class
        );
    }

    // ------------------------------------ /.../states/{stateName}/cities/{cityName}/districts/{districtName}/addresses
    @Operation(summary = "Reads district addresses.", description = "Reads all addresses in specified district.")
    @GetMapping(
            path = __RetrieveEngAddressServiceApiConstants.REQUEST_URI_DISTRICT_ADDRESSES,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Mono<Void> readDistrictAddresses(
            @NotBlank
            @PathVariable(__RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME) final String stateName,
            @NotBlank
            @PathVariable(name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME) final String cityName,
            @NotBlank
            @PathVariable(
                    name = __RetrieveEngAddressServiceApiConstants.PATH_NAME_DISTRICT_NAME) final String districtName,
            final ServerHttpResponse response) {
        final var total = new AtomicReference<Integer>();
        final var count = new LongAdder();
        final var data = Mono.just(LandAddressEngSearchListRequest.of(
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
                .flatMap(r -> {
                    final var list = r.getLandAddressEngSearchList();
                    return Flux.fromIterable(list)
                            .map(e -> DistrictAddress.newInstance(stateName, cityName, districtName, e))
                            .map(DistrictAddress::addLinks);
                })
                .<DistrictAddress>handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                });
        return writeNdjsonResponseWith(response, data, DistrictAddress.class);
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
                                s -> getCitiesPublisher(exchange.getRequest(), s.name()),
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
                                s -> getCitiesPublisher(exchange.getRequest(), s.name())
                                        .flatMapSequential(
                                                c -> getRoadPublisher(exchange.getRequest(), s.name(), c.name()),
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
                                s -> getCitiesPublisher(exchange.getRequest(), s.name())
                                        .flatMapSequential(
                                                c -> getDistrictPublisher(exchange.getRequest(), s.name(), c.name()),
                                                8
                                        ),
                                4
                        ),
                District.class
        );
    }
}
