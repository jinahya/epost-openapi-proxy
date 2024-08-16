package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Slf4j
class _AreaCodeInfoUtilsTest {

    @DisplayName("지역별 주소 DB")
    @ValueSource(strings = {
            "zipcode_DB.zip",
            "areacd_chgaddr_DB.zip",
            "areacd_rangeaddr_DB.zip",
            "areacd_pobox_DB.zip"
    })
    @ParameterizedTest
    void extract__zipcode(final String resName) throws IOException {
        try (var resource = getClass().getResourceAsStream(resName)) {
            assumeTrue(resource != null, () -> "null resource for " + resource);
            final var flags = new HashMap<String, Boolean>();
            AreaCodeInfoUtils.extract(
                    resource,
                    (n, m) -> {
                        if (flags.compute(n, (k, v) -> v == null)) {
                            log.debug("n: {}, m: {}", n, m);
                        }
                    });
        }
    }
}
