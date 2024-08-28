package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModel;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadAddress
        extends AbstractTypeWrappingModel<RoadAddress, RoadAddressEngSearchListResponse.RoadAddressEngSearchList> {

    @Serial
    private static final long serialVersionUID = -5101898500073554823L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static RoadAddress newInstance(final String stateName, final String cityName, final String roadName,
                                          final RoadAddressEngSearchListResponse.RoadAddressEngSearchList wrapped) {
        final var instance = new RoadAddress();
        instance.stateName = Objects.requireNonNull(stateName, "stateName is null");
        instance.cityName = Objects.requireNonNull(cityName, "cityName is null");
        instance.roadName = Objects.requireNonNull(roadName, "roadName is null");
        instance.wrapped = Objects.requireNonNull(wrapped, "wrapped is null");
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links

    // --------------------------------------------------------------------------------------------------- super.wrapped

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty
    private String stateName;

    @JsonProperty
    private String cityName;

    @JsonProperty
    private String roadName;
}
