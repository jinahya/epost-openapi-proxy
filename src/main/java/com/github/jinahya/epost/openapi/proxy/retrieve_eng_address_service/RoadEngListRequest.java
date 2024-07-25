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
public class RoadEngListRequest
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = 1235816016916872587L;

    // -----------------------------------------------------------------------------------------------------------------
    public static RoadEngListRequest.RoadEngListRequestBuilder<?, ?> builderFrom(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList,
            final RoadEngFirstNameListResponse.RoadEngFirstNameList roadEngFirstNameList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        Objects.requireNonNull(roadEngFirstNameList, "roadEngFirstNameList is null");
        return builder()
                .stateEngName(stateEngList.getStateEngName())
                .cityEngName(cityEngList.getCityEngName())
                .roadEngFirstName(roadEngFirstNameList.getRoadEngFirstName());
    }

    public static RoadEngListRequest from(final StateEngListResponse.StateEngList stateEngList,
                                          final CityEngListResponse.CityEngList cityEngList,
                                          final RoadEngFirstNameListResponse.RoadEngFirstNameList roadEngFirstNameList) {
        return builderFrom(stateEngList, cityEngList, roadEngFirstNameList)
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
}
