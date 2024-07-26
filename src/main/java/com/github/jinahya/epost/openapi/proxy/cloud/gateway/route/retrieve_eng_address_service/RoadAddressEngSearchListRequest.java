package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

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
public class RoadAddressEngSearchListRequest
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = 3206249731898344984L;

    // -----------------------------------------------------------------------------------------------------------------
    public static RoadAddressEngSearchListRequest.RoadAddressEngSearchListRequestBuilder<?, ?> builderFrom(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList,
            final RoadEngFirstNameListResponse.RoadEngFirstNameList roadEngFirstNameList,
            final RoadEngListResponse.RoadEngList roadEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        Objects.requireNonNull(roadEngFirstNameList, "roadEngFirstNameList is null");
        Objects.requireNonNull(roadEngList, "roadEngList is null");
        return builder()
                .stateEngName(stateEngList.getStateEngName())
                .cityEngName(cityEngList.getCityEngName())
                .roadEngFirstName(roadEngFirstNameList.getRoadEngFirstName());
    }

    public static RoadAddressEngSearchListRequest from(final StateEngListResponse.StateEngList stateEngList,
                                                       final CityEngListResponse.CityEngList cityEngList,
                                                       final RoadEngFirstNameListResponse.RoadEngFirstNameList roadEngFirstNameList,
                                                       final RoadEngListResponse.RoadEngList roadEngList) {
        return builderFrom(stateEngList, cityEngList, roadEngFirstNameList, roadEngList)
                .build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;

    @Size(max = 1)
    @NotBlank
    private String roadEngFirstName;

    @NotBlank
    private String roadEngName;

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
