package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class RoadEngListRequest
        extends AbstractRequestType {

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected UriBuilder set(final UriBuilder builder) {
        return super.set(builder)
                .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, stateEngName)
                .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, cityEngName)
                .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_FIRST_NAME, roadEngFirstName)
                ;
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
