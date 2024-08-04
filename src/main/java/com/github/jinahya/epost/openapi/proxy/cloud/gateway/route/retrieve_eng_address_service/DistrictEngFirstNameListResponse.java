package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.List;

@XmlRootElement(name = DistrictEngFirstNameListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DistrictEngFirstNameListResponse
        extends AbstractResponseType<DistrictEngFirstNameListResponse> {

    @Serial
    private static final long serialVersionUID = 1731787869103028619L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "DistrictEngFirstNameListResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_DISTRICT_ENG_FIRST_NAME_LIST = "districtEngFirstNameList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DistrictEngFirstNameList
            extends AbstractType<DistrictEngFirstNameList> {

        @Serial
        private static final long serialVersionUID = -7393190937923231268L;

        // -------------------------------------------------------------------------------------------------------------
        @Size(max = 1)
        @NotBlank
        @JsonProperty
        @XmlElement
        private String districtEngFirstName;

        @Positive
//        @PositiveOrZero
        @NotNull
        private Integer cnt;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader

    // --------------------------------------------------------------------------------------------------------- wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public DistrictEngFirstNameListResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_DISTRICT_ENG_FIRST_NAME_LIST)
    @XmlElement(name = NAME_DISTRICT_ENG_FIRST_NAME_LIST)
    private List<@Valid @NotNull DistrictEngFirstNameList> districtEngFirstNameList;
}
