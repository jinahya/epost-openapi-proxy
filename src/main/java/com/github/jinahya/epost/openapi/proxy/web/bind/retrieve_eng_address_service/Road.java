package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModel;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Road
        extends AbstractTypeWrappingModel<Road, RoadEngListResponse.RoadEngList> {

    @Serial
    private static final long serialVersionUID = -6556075429906227124L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static Road newInstance(final String stateName, final String cityName,
                                   final RoadEngListResponse.RoadEngList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new Road();
        instance.stateName = stateName;
        instance.cityName = cityName;
        instance.wrapped = wrapped;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Road() {
        super();
    }

    // ----------------------------------------------------------------------------------------------------- super.links

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonIgnore
    String name() {
        return getWrapped().getRoadEngName();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonProperty(required = true)
    @NotBlank
    private String stateName;

    @JsonProperty(required = true)
    @NotBlank
    private String cityName;
}
