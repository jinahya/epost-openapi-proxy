package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
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
public class District
        extends RepresentationModel<District> {

    static String getHref(final District district) {
        return City.getHref(district.getCity())
                + '/' + _RetrieveEngAddressServiceApiConstants.REL_DISTRICTS
                + '/' + district.wrapped.getDistrictEngName();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static District of(final City city, final DistrictEngListResponse.DistrictEngList wrapped) {
        Objects.requireNonNull(city, "city is null");
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new District();
        instance.city = city;
        instance.wrapped = wrapped;
        return instance;
    }

    static District from(final ServerWebExchange exchange, final DistrictEngListResponse.DistrictEngList wrapped) {
        final var city = City.from(exchange);
        return of(city, wrapped);
    }

    static String districtName(final ServerWebExchange exchange) {
        Objects.requireNonNull(exchange, "exchange is null");
        final Map<String, String> variables = exchange.getAttribute(
                ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE
        );
        assert variables != null;
        return variables.get(_RetrieveEngAddressServiceApiConstants.PATH_NAME_DISTRICT_NAME);
    }

    static District from(final ServerWebExchange exchange) {
        final var wrapped = DistrictEngListResponse.DistrictEngList.of(districtName(exchange));
        return from(exchange, wrapped);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public District addLinks() {
//        add(Link.of(getHref(this)).withRel(IanaLinkRelations.SELF));
        add(Link.of(getHref(this) + '/' + _RetrieveEngAddressServiceApiConstants.REL_ADDRESSES).withRel(
                _RetrieveEngAddressServiceApiConstants.REL_ADDRESSES));
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @Valid
    private City city;

    @JsonUnwrapped
    @Valid
    @NotNull
    private DistrictEngListResponse.DistrictEngList wrapped;
}
