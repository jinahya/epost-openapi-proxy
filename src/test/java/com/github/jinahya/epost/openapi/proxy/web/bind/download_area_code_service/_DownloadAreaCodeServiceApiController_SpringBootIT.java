package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class _DownloadAreaCodeServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<_DownloadAreaCodeServiceApiController> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    _DownloadAreaCodeServiceApiController_SpringBootIT() {
        super(_DownloadAreaCodeServiceApiController.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../areaCodeInfo/{dwlsSe}")
    @EnumSource(AreaCodeInfoRequest.DwldSe.class)
    @ParameterizedTest
    void readAreaCodeInfo__(final AreaCodeInfoRequest.DwldSe dwldSe) {
//        webTestClient()
//                .get()
//                .uri(b -> b.path(__DownloadAreaCodeServiceApiConstants.REQUEST_URI_AREA_CODE_INFO)
//                        .build(dwldSe.text()))
//                .headers(h -> {
//                    Optional.ofNullable(accept)
//                            .map(MediaType::valueOf)
//                            .map(List::of)
//                            .ifPresent(h::setAccept);
//                })
//                .exchange()
//                .expectStatus().isOk()
//                .expectBodyList(State.class)
//                .returnResult()
//                .getResponseBody()
    }

//    @DisplayName("GET /.../states")
//    @MethodSource({"getMediaTypeStreamForCollection"})
//    @ParameterizedTest
//    void readStates__(final String mediaType) {
//        final var states = readStates(webTestClient(), mediaType);
//        assertThat(states)
//                .isNotEmpty()
//                .doesNotContainNull()
//                .allSatisfy(this::assertValid);
//        state = states.getLast();
//    }

}