package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadAddress
        extends RepresentationModel<RoadAddress> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static RoadAddress from(
            final RoadAddressEngSearchListResponse.RoadAddressEngSearchList roadAddressEngSearchList) {
        Objects.requireNonNull(roadAddressEngSearchList, "roadAddressEngSearchList is null");
        final var instance = new RoadAddress();
        instance.wrapped = roadAddressEngSearchList;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public RoadAddress addLinks() {
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @Valid
    private Road road;

    // -----------------------------------------------------------------------------------------------------------------
    @JsonUnwrapped
    @JsonProperty
    private RoadAddressEngSearchListResponse.RoadAddressEngSearchList wrapped;
}
