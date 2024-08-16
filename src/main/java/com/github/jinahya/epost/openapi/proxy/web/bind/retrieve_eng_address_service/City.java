package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class City
        extends AbstractWrappingModel<City, CityEngListResponse.CityEngList> {

    @Serial
    private static final long serialVersionUID = -9164721273765267181L;

    // -----------------------------------------------------------------------------------------------------------------
    static String getHref(final String stateName, final String cityName) {
        return State.getHref(stateName)
                + '/' + __RetrieveEngAddressServiceApiConstants.REL_CITIES
                + '/' + cityName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static City newInstance(final String stateName, final CityEngListResponse.CityEngList wrapped) {
        final var instance = new City();
        instance.setStateName(stateName);
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public City addLinks() {
        add(
                Link.of(State.getHref(stateName))
                        .withRel(__RetrieveEngAddressServiceApiConstants.REL_STATE)
        );
        add(
                Link.of(getHref(stateName, wrapped.getCityEngName()))
                        .withRel(IanaLinkRelations.SELF)
        );
        add(
                Link.of(getHref(stateName, wrapped.getCityEngName())
                                + '/' + __RetrieveEngAddressServiceApiConstants.REL_ROADS)
                        .withRel(__RetrieveEngAddressServiceApiConstants.REL_ROADS)
        );
        add(
                Link.of(getHref(stateName, wrapped.getCityEngName())
                                + '/' + __RetrieveEngAddressServiceApiConstants.REL_LANDS)
                        .withRel(__RetrieveEngAddressServiceApiConstants.REL_LANDS)
        );
        return this;
    }

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
