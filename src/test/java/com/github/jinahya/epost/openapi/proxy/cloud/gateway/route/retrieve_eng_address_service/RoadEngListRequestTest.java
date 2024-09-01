package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestTypeTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class RoadEngListRequestTest
        extends AbstractPairedRequestTypeTest<RoadEngListRequest, RoadEngListResponse> {

    RoadEngListRequestTest() {
        super(RoadEngListRequest.class, RoadEngListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("of(serviceKey, stateName, cityName, roadName)")
    @Nested
    class OfTest {

        @Test
        void __() {
            final var instance = RoadEngListRequest.of(null, "stateName", "cityName", "roadName");
            assertThat(instance).isNotNull();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("URI_BUILDER")
    @Nested
    class UriBuilderTest {

        @Test
        void __() {
            final var request = newTypeInstance();
            final var builder = UriComponentsBuilder.fromPath("/");
            assertThatCode(() -> {
                RoadEngListRequest.URI_BUILDER.accept(request, builder);
            }).doesNotThrowAnyException();
        }
    }
}