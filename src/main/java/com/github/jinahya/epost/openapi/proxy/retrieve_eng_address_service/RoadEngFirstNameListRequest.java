package com.github.jinahya.epost.openapi.proxy.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class RoadEngFirstNameListRequest
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -3089723119999877810L;

    // -----------------------------------------------------------------------------------------------------------------
    public static RoadEngFirstNameListRequest.RoadEngFirstNameListRequestBuilder<?, ?> builderFrom(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        return builder()
                .stateEngName(stateEngList.getStateEngName())
                .cityEngName(cityEngList.getCityEngName());
    }

    public static RoadEngFirstNameListRequest from(final StateEngListResponse.StateEngList stateEngList,
                                                   final CityEngListResponse.CityEngList cityEngList) {
        return builderFrom(stateEngList, cityEngList)
                .build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;
}
