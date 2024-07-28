package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseType;
import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serial;
import java.util.List;

@XmlRootElement(name = RoadAddressEngSearchListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadAddressEngSearchListResponse
        extends AbstractSelfWrappingResponseType<RoadAddressEngSearchListResponse> {

    @Serial
    private static final long serialVersionUID = -8470164971827744847L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "RoadAddressEngSearchListResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_ROAD_ADDRESS_ENG_SEARCH_LIST = "roadAddressEngSearchList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class RoadAddressEngSearchList
            extends AbstractType {

        @Serial
        private static final long serialVersionUID = -4962767662385926995L;

        @NotBlank
        @JsonProperty
        @XmlElement(required = true)
        private String engAddress;

        @Size(min = 5, max = 5)
        @NotNull
        @XmlElement(required = true)
        private String areaCode;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    RoadAddressEngSearchListResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
//    @Valid
//    @NotNull
//    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_ROAD_ADDRESS_ENG_SEARCH_LIST)
    @XmlElement(name = NAME_ROAD_ADDRESS_ENG_SEARCH_LIST)
    private List<@Valid @NotNull RoadAddressEngSearchList> roadAddressEngSearchList;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private RoadAddressEngSearchListResponse wrapped;
}
