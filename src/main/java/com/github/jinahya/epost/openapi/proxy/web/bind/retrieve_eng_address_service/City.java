package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModel;
import jakarta.validation.constraints.NotBlank;
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
public class City
        extends AbstractTypeWrappingModel<City, CityEngListResponse.CityEngList> {

    @Serial
    private static final long serialVersionUID = -9164721273765267181L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static City newInstance(final String stateName, final CityEngListResponse.CityEngList wrapped) {
        final var instance = new City();
        instance.setStateName(stateName);
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonIgnore
    String name() {
        return Objects.requireNonNull(getWrapped(), "getWrapped() is null").getCityEngName();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty
    @NotBlank
    private String stateName;
}
