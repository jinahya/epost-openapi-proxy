package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = _Constants.TAG)
@RestController
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class RetrieveEngAddressServiceProxyController {

    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<StateEngListResponse> getStateList(final @ParameterObject StateEngListRequest request) {
        throw new UnsupportedOperationException("");
    }

    @GetMapping(path = _RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<CityEngListResponse> getCityList(final @ParameterObject CityEngListRequest request) {
        throw new UnsupportedOperationException("");
    }
}
