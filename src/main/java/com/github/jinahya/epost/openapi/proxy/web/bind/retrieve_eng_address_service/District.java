package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
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
public class District
        extends AbstractWrappingModel<District, DistrictEngListResponse.DistrictEngList> {

    @Serial
    private static final long serialVersionUID = -623112560952700313L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static District newInstance(final String stateName, final String cityName,
                                       final DistrictEngListResponse.DistrictEngList wrapped) {
        final var instance = new District();
        instance.stateName = Objects.requireNonNull(stateName, "stateName is null");
        instance.cityName = Objects.requireNonNull(cityName, "cityName is null");
        instance.wrapped = Objects.requireNonNull(wrapped, "wrapped is null");
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonIgnore
    String name() {
        return getWrapped().getDistrictEngName();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty(required = true)
    private String stateName;

    @JsonProperty(required = true)
    private String cityName;
}
