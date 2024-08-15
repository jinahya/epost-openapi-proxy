package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadAddress
        extends AbstractWrappingModel<RoadAddress, RoadAddressEngSearchListResponse.RoadAddressEngSearchList> {

    @Serial
    private static final long serialVersionUID = -5101898500073554823L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static RoadAddress instanceOf(
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
//    @NotBlank
    private String stateName;

    @JsonIgnore
//    @NotBlank
    private String cityName;

    @JsonIgnore
//    @NotBlank
    private String districtName;
}
