package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiService;
import jakarta.validation.Valid;
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
class RetrieveNewAdressAreaCdSearchAllServiceApiService
        extends _ApiService {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    Mono<NewAddressListAreaCdSearchAllResponse> exchange(@Valid final NewAddressListAreaCdSearchAllRequest request) {
        return super.exchangeResponse(request);
    }
}
