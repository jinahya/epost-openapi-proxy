package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.DistrictEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import lombok.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import java.io.Serial;

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
    static String getHref(final String stateName, final String cityName, final String districtName) {
        return City.getHref(stateName, cityName)
                + '/' + _RetrieveEngAddressServiceApiConstants.REL_DISTRICTS
                + '/' + districtName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static District districtOf(final String stateName, final String cityName,
                                      final DistrictEngListResponse.DistrictEngList wrapped) {
        final var instance = new District();
        instance.stateName = stateName;
        instance.cityName = cityName;
        instance.wrapped = wrapped;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public District addLinks() {
        add(
                Link.of(getHref(stateName, cityName, wrapped.getDistrictEngName()))
                        .withRel(IanaLinkRelations.SELF)
        );
        add(
                Link.of(getHref(stateName, cityName, wrapped.getDistrictEngName())
                                + '/' + _RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
                        .withRel(_RetrieveEngAddressServiceApiConstants.REL_ADDRESSES)
        );
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    private String stateName;

    @JsonIgnore
    private String cityName;
}
