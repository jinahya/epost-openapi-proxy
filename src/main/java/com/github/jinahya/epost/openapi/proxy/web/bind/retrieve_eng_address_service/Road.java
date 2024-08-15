package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
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
public class Road
        extends AbstractWrappingModel<Road, RoadEngListResponse.RoadEngList> {

    @Serial
    private static final long serialVersionUID = -6556075429906227124L;

    // -----------------------------------------------------------------------------------------------------------------
    static String getHref(final String stateName, final String cityName, final String roadName) {
        return City.getHref(stateName, cityName)
                + '/' + _RetrieveEngAddressServiceApiConstants.REL_ROADS
                + '/' + roadName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static Road instanceOf(final String stateName, final String cityName,
                                  final RoadEngListResponse.RoadEngList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new Road();
        instance.stateName = stateName;
        instance.cityName = cityName;
        instance.wrapped = wrapped;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public Road addLinks() {
        add(
                Link.of(getHref(stateName, cityName, wrapped.getRoadEngName()))
                        .withRel(IanaLinkRelations.SELF)
        );
        add(
                Link.of(getHref(stateName, cityName, wrapped.getRoadEngName())
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
