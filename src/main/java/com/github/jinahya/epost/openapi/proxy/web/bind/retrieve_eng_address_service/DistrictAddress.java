package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.LandAddressEngSearchListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import lombok.*;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DistrictAddress
        extends AbstractWrappingModel<DistrictAddress, LandAddressEngSearchListResponse.LandAddressEngSearchList> {

    @Serial
    private static final long serialVersionUID = -8964407039642096158L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static DistrictAddress newInstance(final String stateName, final String cityName, final String districtName,
                                              final LandAddressEngSearchListResponse.LandAddressEngSearchList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new DistrictAddress();
        instance.stateName = stateName;
        instance.cityName = cityName;
        instance.districtName = districtName;
        instance.wrapped = wrapped;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public DistrictAddress addLinks() {
        return this;
    }

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty
    public String getAddress() {
        return Optional.ofNullable(getWrapped())
                .map(LandAddressEngSearchListResponse.LandAddressEngSearchList::getEngAddress)
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty
    private String stateName;

    @JsonProperty
    private String cityName;

    @JsonProperty
    private String districtName;
}
