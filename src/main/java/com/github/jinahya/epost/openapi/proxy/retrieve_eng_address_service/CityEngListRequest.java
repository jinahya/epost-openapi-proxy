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
public class CityEngListRequest
        extends AbstractType {

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

    // ---------------------------------------------------------------------------------------------------- stateEngName

    // ----------------------------------------------------------------------------------------------------- cityEngName

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;
}
