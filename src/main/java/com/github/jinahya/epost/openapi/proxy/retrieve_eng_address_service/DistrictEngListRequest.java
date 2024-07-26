package com.github.jinahya.epost.openapi.proxy.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class DistrictEngListRequest
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -6793297919987439959L;

    // -----------------------------------------------------------------------------------------------------------------
    public static DistrictEngListRequest.DistrictEngListRequestBuilder<?, ?> builderFrom(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList,
            final DistrictEngFirstNameListResponse.DistrictEngFirstNameList districtEngFirstNameList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        Objects.requireNonNull(districtEngFirstNameList, "districtEngFirstNameList is null");
        return builder()
                .stateEngName(stateEngList.getStateEngName())
                .cityEngName(cityEngList.getCityEngName())
                .districtEngFirstName(districtEngFirstNameList.getDistrictEngFirstName());
    }

    public static DistrictEngListRequest from(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList,
            final DistrictEngFirstNameListResponse.DistrictEngFirstNameList districtEngFirstNameList) {
        return builderFrom(stateEngList, cityEngList, districtEngFirstNameList)
                .build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;

    @Size(max = 1)
    @NotBlank
    private String districtEngFirstName;
}
