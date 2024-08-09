package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class City
        extends RepresentationModel<City> {

    static String getHref(final State state, final String name) {
        return _Constants.REQUEST_URI_STATES + '/' + name;
    }

    static String getHref(final State state, City city) {
        return getHref(state, city.getName());
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static City from(final CityEngListResponse.CityEngList cityEngList) {
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        final var instance = new City();
        instance.setName(cityEngList.getCityEngName());
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public City addLinks() {
        return add(
                Link.of(getHref(state, this))
                        .withRel(_Constants.REL_CITIES)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @Valid
    @NotNull
    private State state;

    @NotBlank
    private String name;
}
