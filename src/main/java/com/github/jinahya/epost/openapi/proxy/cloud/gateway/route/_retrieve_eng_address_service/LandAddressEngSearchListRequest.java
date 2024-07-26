package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class LandAddressEngSearchListRequest
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -1827246138318731047L;

    // -----------------------------------------------------------------------------------------------------------------
    public static LandAddressEngSearchListRequest.LandAddressEngSearchListRequestBuilder<?, ?> builderFrom(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList,
            final DistrictEngFirstNameListResponse.DistrictEngFirstNameList districtEngFirstNameList,
            final DistrictEngListResponse.DistrictEngList districtEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        Objects.requireNonNull(districtEngFirstNameList, "districtEngFirstNameList is null");
        Objects.requireNonNull(districtEngList, "districtEngList is null");
        return builder()
                .stateEngName(stateEngList.getStateEngName())
                .cityEngName(cityEngList.getCityEngName())
                .districtEngFirstName(districtEngFirstNameList.getDistrictEngFirstName())
                .districtEngName(districtEngList.getDistrictEngName());
    }

    public static LandAddressEngSearchListRequest from(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList,
            final DistrictEngFirstNameListResponse.DistrictEngFirstNameList districtEngFirstNameList,
            final DistrictEngListResponse.DistrictEngList districtEngList) {
        return builderFrom(stateEngList, cityEngList, districtEngFirstNameList, districtEngList)
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

    @NotBlank
    private String districtEngName;

    // -----------------------------------------------------------------------------------------------------------------
    private String keyword;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    private Integer countPerPage;

    @Positive
    @NotNull
    private Integer currentPage;
}
