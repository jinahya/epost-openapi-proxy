package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.hateoas.MediaTypes;

class _DownloadAreaCodeServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<_DownloadAreaCodeServiceApiController> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    _DownloadAreaCodeServiceApiController_SpringBootIT() {
        super(_DownloadAreaCodeServiceApiController.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../areaCodeInfo/{dwldSe}")
    @EnumSource(AreaCodeInfoRequest.DwldSe.class)
    @ParameterizedTest
    void readAreaCodeInfo__(final AreaCodeInfoRequest.DwldSe dwldSe) {
        final var responseBody = webTestClient()
                .get()
                .uri(b -> b.path(__DownloadAreaCodeServiceApiConstants.REQUEST_URI_DWLD_SE)
                        .build(dwldSe.value()))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaTypes.HAL_JSON)
                .expectBodyList(AreaCodeInfo.class)
                .returnResult()
                .getResponseBody();
        assertValid(responseBody);
    }
}