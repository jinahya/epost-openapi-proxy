package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.CmmMsgHeader;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serial;
import java.util.List;

@XmlRootElement(name = CityEngListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CityEngListResponse
        extends AbstractResponseType<CityEngListResponse> {

    @Serial
    private static final long serialVersionUID = 2105048637913742678L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "CityEngListResponse";

    private static final String NAME_CITY_ENG_LIST = "cityEngList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class CityEngList
            extends AbstractType<CityEngList> {

        @Serial
        private static final long serialVersionUID = -4416152072794741619L;

        @NotBlank
        @JsonProperty
        @XmlElement
        private String cityEngName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader

    // --------------------------------------------------------------------------------------------------------- wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public CityEngListResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_CITY_ENG_LIST)
    @XmlElement(name = NAME_CITY_ENG_LIST)
    private List<@Valid @NotNull CityEngList> cityEngList;
}
