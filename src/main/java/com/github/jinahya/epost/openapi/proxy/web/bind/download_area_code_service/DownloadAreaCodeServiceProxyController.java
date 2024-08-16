package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service._DownloadAreaCodeServiceConstants;
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
class DownloadAreaCodeServiceProxyController {

    @GetMapping(path = _DownloadAreaCodeServiceConstants.REQUEST_URI_GET_AREA_CODE_INFO,
                produces = {
                        MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                })
    Mono<AreaCodeInfoResponse> read(final @ParameterObject AreaCodeInfoRequest request) {
        throw new UnsupportedOperationException("");
    }
}
