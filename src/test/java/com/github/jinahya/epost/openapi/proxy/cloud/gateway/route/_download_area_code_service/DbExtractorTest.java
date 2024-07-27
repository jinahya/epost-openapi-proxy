package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._download_area_code_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Slf4j
class DbExtractorTest {

    @DisplayName("지역별 주소 DB")
    @Test
    void extract__zipcode() throws IOException {
        final var resource = getClass().getResource("zipcode_DB.zip");
        assumeTrue(resource != null);
        final var flags = new HashMap<String, Boolean>();
        DbExtractor.extract(resource, (n, m) -> {
            if (flags.compute(n, (k, v) -> v == null)) {
                log.debug("n: {}, m: {}", n, m);
            }
        });
    }
}
