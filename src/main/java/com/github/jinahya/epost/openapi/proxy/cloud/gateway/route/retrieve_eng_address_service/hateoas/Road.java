package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
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
public class Road
        extends RepresentationModel<Road> {

    static String getHref(final String stateName, final String cityName, final String roadName) {
        return City.getHref(stateName, cityName)
                + '/' + _RetrieveEngAddressServiceApiConstants.REL_ROADS
                + '/' + roadName;
    }

    static String getHref(final Road road) {
        return City.getHref(road.getCity())
                + '/' + _RetrieveEngAddressServiceApiConstants.REL_ROADS
                + '/' + road.wrapped.getRoadEngName();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static Road roadOf(final String stateName, final String cityName,
                              final RoadEngListResponse.RoadEngList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new Road();
        instance.stateName = stateName;
        instance.cityName = cityName;
        instance.wrapped = wrapped;
        return instance;
    }

    public static Road roadOf(final City city, final RoadEngListResponse.RoadEngList wrapped) {
        Objects.requireNonNull(city, "city is null");
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new Road();
        instance.city = city;
        instance.wrapped = wrapped;
        return instance;
    }

    static Road from(final ServerWebExchange exchange, final RoadEngListResponse.RoadEngList wrapped) {
        final var city = City.from(exchange);
        return roadOf(city, wrapped);
    }

    static String roadName(final ServerWebExchange exchange) {
        Objects.requireNonNull(exchange, "exchange is null");
        final Map<String, String> variables = exchange.getAttribute(
                ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE
        );
        assert variables != null;
        return variables.get(_RetrieveEngAddressServiceApiConstants.PATH_NAME_ROAD_NAME);
    }

    static Road from(final ServerWebExchange exchange) {
        final var wrapped = RoadEngListResponse.RoadEngList.of(roadName(exchange));
        return from(exchange, wrapped);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public Road addLinks() {
//        add(Link.of(getHref(this)).withRel(IanaLinkRelations.SELF));
//        add(Link.of(City.getHref(stateName, cityName) + '/' + _RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
//                    .withRel(
//                            _RetrieveEngAddressServiceApiConstants.REL_ADDRESSES));
        add(Link.of(
                            getHref(stateName, cityName, wrapped.getRoadEngName())
                                    + '/' + _RetrieveEngAddressServiceApiConstants.REL_ADDRESSES
                    )
                    .withRel(_RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
        );
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @Valid
    private City city;

    @JsonIgnore
    private String stateName;

    @JsonIgnore
    private String cityName;

    @JsonUnwrapped
    @Valid
    @NotNull
    private RoadEngListResponse.RoadEngList wrapped;
}
