package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.RoadEngListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Road
        extends RepresentationModel<Road> {

    static String getHref(final String name) {
        return _Constants.REQUEST_URI_STATES + '/' + name;
    }

    static String getHref(final Road state) {
        return getHref(state.getName());
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static Road from(final RoadEngListResponse.RoadEngList roadEngList) {
        Objects.requireNonNull(roadEngList, "roadEngList is null");
        final var instance = new Road();
        instance.setName(roadEngList.getRoadEngName());
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public Road addLinks() {
        add(
                Link.of(getHref(this))
                        .withRel(IanaLinkRelations.SELF)
        );
        add(
                Link.of(getHref(this) + '/' + _Constants.REL_CITIES)
                        .withRel(_Constants.REL_CITIES)
        );
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @Valid
    @NotNull
    private City city;

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String name;
}
