package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class City
        extends RepresentationModel<City> {

    static String getHref(final City city) {
        return State.getHref(city.getState())
                + '/' + _RetrieveEngAddressServiceApiConstants.REL_CITIES
                + '/' + city.getWrapped().getCityEngName();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    static City of(final State state, final CityEngListResponse.CityEngList wrapped) {
        Objects.requireNonNull(state, "state is null");
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new City();
        instance.setState(state);
        instance.setWrapped(wrapped);
        return instance;
    }

    static City from(final ServerWebExchange exchange, final CityEngListResponse.CityEngList wrapped) {
        final var state = State.from(exchange);
        return City.of(state, wrapped);
    }

    static String cityName(final ServerWebExchange exchange) {
        Objects.requireNonNull(exchange, "exchange is null");
        final Map<String, String> variables = exchange.getAttribute(
                ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE
        );
        assert variables != null;
        return variables.get(_RetrieveEngAddressServiceApiConstants.PATH_NAME_CITY_NAME);
    }

    static City from(final ServerWebExchange exchange) {
        final var wrapped = CityEngListResponse.CityEngList.of(cityName(exchange));
        return from(exchange, wrapped);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public City addLinks() {
//        add(Link.of(getHref(this)).withRel(IanaLinkRelations.SELF));
        add(Link.of(getHref(this) + '/' + _RetrieveEngAddressServiceApiConstants.REL_ROADS).withRel(
                _RetrieveEngAddressServiceApiConstants.REL_ROADS));
        add(Link.of(getHref(this) + '/' + _RetrieveEngAddressServiceApiConstants.REL_LANDS).withRel(
                _RetrieveEngAddressServiceApiConstants.REL_LANDS));
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @NotNull
    private State state;

    @JsonUnwrapped
    @NotNull
    private CityEngListResponse.CityEngList wrapped;
}
