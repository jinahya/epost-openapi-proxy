package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.CityEngListResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class City
        extends RepresentationModel<City> {

    public static City from(final CityEngListResponse.CityEngList cityEngList) {
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        final var instance = new City();
        instance.setName(cityEngList.getCityEngName());
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String name;
}
