package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;

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
                + '/' + _RetrieveEngAddressServiceApiConstants.REL_CITIES
                + '/' + cityName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static City instanceOf(final String stateName, final CityEngListResponse.CityEngList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new City();
        instance.setStateName(stateName);
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public City addLinks() {
        add(
                Link.of(getHref(stateName, wrapped.getCityEngName()))
                        .withRel(IanaLinkRelations.SELF)
        );
        add(
                Link.of(getHref(stateName, wrapped.getCityEngName())
                                + '/' + _RetrieveEngAddressServiceApiConstants.REL_ROADS)
                        .withRel(_RetrieveEngAddressServiceApiConstants.REL_ROADS)
        );
        add(
                Link.of(getHref(stateName, wrapped.getCityEngName())
                                + '/' + _RetrieveEngAddressServiceApiConstants.REL_LANDS)
                        .withRel(_RetrieveEngAddressServiceApiConstants.REL_LANDS)
        );
        return this;
    }

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty(required = true)
    public String getName() {
        return Optional.ofNullable(getWrapped())
                .map(CityEngListResponse.CityEngList::getCityEngName)
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty(required = true)
    @NotBlank
    private String stateName;
}
