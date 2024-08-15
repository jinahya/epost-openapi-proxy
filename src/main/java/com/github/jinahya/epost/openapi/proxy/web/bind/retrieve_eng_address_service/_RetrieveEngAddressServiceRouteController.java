package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.*;
import com.github.jinahya.epost.openapi.proxy.web.bind._RouteController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = _RetrieveEngAddressServiceApiConstants.TAG)
@RestController
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class _RetrieveEngAddressServiceRouteController
        extends _RouteController {

    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<StateEngListResponse> getStateList(final @ParameterObject StateEngListRequest request) {
        throw new UnsupportedOperationException("");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<CityEngListResponse> getCityList(final @ParameterObject CityEngListRequest request) {
        throw new UnsupportedOperationException("");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_FIRST_NAME_LIST,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<RoadEngFirstNameListResponse> getRoadFirstNameList(
            final @ParameterObject RoadEngFirstNameListRequest request) {
        throw new UnsupportedOperationException("");
    }

    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_ADDRESS_SEARCH,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<RoadAddressEngSearchListResponse> getRoadAddressSearch(
            final @ParameterObject RoadAddressEngSearchListRequest request) {
        throw new UnsupportedOperationException("");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_FIRST_NAME_LIST,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<DistrictEngFirstNameListResponse> getDistrictFirstNameList(
            final @ParameterObject DistrictEngFirstNameListRequest request) {
        throw new UnsupportedOperationException("");
    }

    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_LAND_ADDRESS_SEARCH,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<LandAddressEngSearchListResponse> getLandAddressSearch(
            final @ParameterObject LandAddressEngSearchListRequest request) {
        throw new UnsupportedOperationException("");
    }
}
