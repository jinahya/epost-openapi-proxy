package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngFirstNameListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngFirstNameListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
@Service
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
@SuppressWarnings({
        "java:S101" // class _Retrieve...
})
class RetrieveEngAddressServiceApiService
        extends _ApiService {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @SuppressWarnings({
            "java:S119" // <REQUEST ...>
    })
    <REQUEST extends AbstractPairedRequestType<REQUEST, RESPONSE>,
            RESPONSE extends AbstractPairedResponseType<RESPONSE, REQUEST>>
    Mono<RESPONSE> exchange(@Valid @NotNull final REQUEST request) {
        return super.exchangeResponse(request);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    Mono<StateEngListResponse> exchange(@Valid @NotNull final StateEngListRequest request) {
        return super.exchangeResponse(request);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    Mono<CityEngListResponse> exchange(@Valid @NotNull final CityEngListRequest request) {
        return super.exchangeResponse(request);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    Mono<DistrictEngFirstNameListResponse> exchange(@Valid @NotNull final DistrictEngFirstNameListRequest request) {
        return super.exchangeResponse(request);
    }

    @NotNull
    Mono<DistrictEngListResponse> exchange(@Valid @NotNull final DistrictEngListRequest request) {
        return super.exchangeResponse(request);
    }

    @NotNull
    Mono<LandAddressEngSearchListResponse> exchange(@Valid @NotNull final LandAddressEngSearchListRequest request) {
        return super.exchangeResponse(request);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    Mono<RoadEngFirstNameListResponse> exchange(@Valid @NotNull final RoadEngFirstNameListRequest request) {
        return super.exchangeResponse(request);
    }

    @NotNull
    Mono<RoadEngListResponse> exchange(@Valid @NotNull final RoadEngListRequest request) {
        return super.exchangeResponse(request);
    }

    @NotNull
    Mono<RoadAddressEngSearchListResponse> exchange(@Valid @NotNull final RoadAddressEngSearchListRequest request) {
        return super.exchangeResponse(request);
    }
}
