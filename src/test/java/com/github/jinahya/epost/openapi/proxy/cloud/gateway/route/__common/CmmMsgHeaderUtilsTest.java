package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class CmmMsgHeaderUtilsTest {

    @ValueSource(booleans = {true, false})
    @ParameterizedTest
    void toPage__(final boolean oneIndexed) {
        // ------------------------------------------------------------------------------------------------------- given
        final var header = new CmmMsgHeader();
        {
            header.setTotalPage(ThreadLocalRandom.current().nextInt(128));
            header.setCurrentPage(ThreadLocalRandom.current().nextInt(1, header.getTotalPage() + 1));
            header.setCountPerPage(ThreadLocalRandom.current().nextInt(1, 128));
            header.setTotalCount(header.getTotalPage() * header.getCountPerPage());
        }
        // -------------------------------------------------------------------------------------------------------- when
        final Page<Object> page = CmmMsgHeaderUtils.toPage(header, List.of(), oneIndexed);
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(page.getTotalPages()).isEqualTo(header.getTotalPage());
        assertThat(page.getNumber()).isEqualTo(header.getCurrentPage() - (oneIndexed ? 0 : 1));
        assertThat(page.getTotalElements()).isEqualTo((long) header.getTotalCount());
    }
}
