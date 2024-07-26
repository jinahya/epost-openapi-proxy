package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

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
public class CityEngListRequest
        extends AbstractRequestType {

    @Serial
    private static final long serialVersionUID = 2981550532310902459L;

    // -----------------------------------------------------------------------------------------------------------------
    public CityEngListRequest.CityEngListRequestBuilder<?, ?> builderFrom(
            final StateEngListResponse.StateEngList stateEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        return builder()
                .stateEngName(stateEngList.getStateEngName()
                );
    }

    public CityEngListRequest from(final StateEngListResponse.StateEngList stateEngList) {
        return builderFrom(stateEngList).build();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    public UriBuilder set(final UriBuilder builder) {
        return super.set(builder)
//                .path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST)
                .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NM, stateEngName)
                ;
    }

    // ---------------------------------------------------------------------------------------------------- stateEngName

    // ----------------------------------------------------------------------------------------------------- cityEngName

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;
}
