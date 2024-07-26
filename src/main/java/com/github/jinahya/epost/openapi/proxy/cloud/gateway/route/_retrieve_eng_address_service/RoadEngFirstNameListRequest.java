package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import jakarta.validation.constraints.NotBlank;
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
public class RoadEngFirstNameListRequest
        extends AbstractRequestType {

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
    public static RoadEngFirstNameListRequest.RoadEngFirstNameListRequestBuilder<?, ?> builderFrom(
            final CityEngListRequest cityEngListRequest) {
        return builder()
                .stateEngName(cityEngListRequest.getStateEngName())
                ;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected UriBuilder set(final UriBuilder builder) {
        return super.set(
                builder.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_FIRST_NAME_LIST)
                        .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, stateEngName)
                        .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, cityEngName)
        );
    }

    // ---------------------------------------------------------------------------------------------------- stateEngName

    // ----------------------------------------------------------------------------------------------------- cityEngName
    public RoadEngFirstNameListRequest cityEngName(final String cityEngName) {
        setCityEngName(cityEngName);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;
}
