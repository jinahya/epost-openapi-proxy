package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.http.server.reactive.ServerHttpRequest;

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

    // -----------------------------------------------------------------------------------------------------------------
    static String getHref(final ServerHttpRequest request, final String stateName, final String cityName,
                          final String districtName) {
        return City.getHref(request, stateName, cityName)
                + '/' + __RetrieveEngAddressServiceApiConstants.REL_DISTRICTS
                + '/' + districtName;
    }

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
    public District addLinks(final ServerHttpRequest request) {
        add(
                Link.of(getHref(request, stateName, cityName, wrapped.getDistrictEngName()))
                        .withSelfRel()
        );
        add(
                Link.of(getHref(request, stateName, cityName, wrapped.getDistrictEngName())
                                + '/' + __RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
                        .withRel(__RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
        );
        return this;
    }

    // --------------------------------------------------------------------------------------------------- super.wrapped

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty(required = true)
    private String stateName;

    @JsonProperty(required = true)
    private String cityName;
}
