package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DistrictAddress
        extends RepresentationModel<DistrictAddress> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static DistrictAddress from(final LandAddressEngSearchListResponse.LandAddressEngSearchList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new DistrictAddress();
        instance.wrapped = wrapped;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public DistrictAddress addLinks() {
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @Valid
    @NotNull
    private District district;

    // -----------------------------------------------------------------------------------------------------------------
    @JsonUnwrapped
    @JsonProperty
    private LandAddressEngSearchListResponse.LandAddressEngSearchList wrapped;
}
