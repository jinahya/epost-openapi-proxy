package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController_SpringBootIT;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.core.TypeReferences;

import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class _DownloadAreaCodeServiceApiController_SpringBootIT
        extends _ApiController_SpringBootIT<_DownloadAreaCodeServiceApiController> {

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("GET /.../areaCodeInfo/{dwldSe}")
    @EnumSource(AreaCodeInfoRequest.DwldSe.class)
    @ParameterizedTest
    void readAreaCodeInfo__(final AreaCodeInfoRequest.DwldSe dwldSe) {
        webTestClient()
                .get()
                .uri(b -> b.path(__DownloadAreaCodeServiceApiConstants.REQUEST_URI_DWLD_SE)
                        .build(dwldSe.value()))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaTypes.HAL_JSON)
                .expectBody(new TypeReferences.EntityModelType<AreaCodeInfoResponse>() {
                })
                .consumeWith(r -> {
                    final var responseBody = r.getResponseBody();
                    assert responseBody != null;
                    final var content = responseBody.getContent();
                    assertThat(content).isNotNull();
                    assertValid(content);
                })
        ;
    }

    @Disabled("takes too long, baby")
    @DisplayName("GET /.../areaCodeInfo/{dwldSe}/fileContent")
    @EnumSource(AreaCodeInfoRequest.DwldSe.class)
    @ParameterizedTest
    void readAreaCodeInfoFileContent__(final AreaCodeInfoRequest.DwldSe dwldSe) {
        webTestClient()
                .get()
                .uri(b -> b.path(__DownloadAreaCodeServiceApiConstants.REQUEST_URI_FILE_CONTENT)
                        .build(dwldSe.value()))
                .exchange()
                .expectStatus().isOk()
                .returnResult(DataBuffer.class)
                .consumeWith(r -> {
                    r.getResponseBody().blockLast();
                })
        ;
    }
}