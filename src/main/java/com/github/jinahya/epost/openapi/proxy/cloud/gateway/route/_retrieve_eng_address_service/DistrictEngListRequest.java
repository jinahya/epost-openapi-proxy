package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DistrictEngListRequest
        extends AbstractRequestType {

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

    @Override
    protected UriBuilder set(UriBuilder builder) {
        return super.set(
                builder.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_NAME_LIST)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, stateEngName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, cityEngName)
        );
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
