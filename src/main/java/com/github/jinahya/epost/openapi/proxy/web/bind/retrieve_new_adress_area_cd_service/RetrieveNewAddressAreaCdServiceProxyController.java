package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_service.NewAddressListAreaCdRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_service.NewAddressListAreaCdResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_service._RetrieveNewAdressAreaCdServiceConstants;
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
class RetrieveNewAddressAreaCdServiceProxyController {

    @GetMapping(
            path = _RetrieveNewAdressAreaCdServiceConstants.REQUEST_URI_GET_NEW_ADDRESS_LIST_AREA_CD,
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    Mono<NewAddressListAreaCdResponse> getStateList(final @ParameterObject NewAddressListAreaCdRequest request) {
        throw new UnsupportedOperationException("");
    }
}
