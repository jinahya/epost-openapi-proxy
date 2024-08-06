package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

@XmlRootElement(name = LandAddressEngSearchListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LandAddressEngSearchListResponse
        extends AbstractResponseType<LandAddressEngSearchListResponse> {

    @Serial
    private static final long serialVersionUID = -6712632432291074179L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "LandAddressEngSearchListResponse";

    private static final String NAME_LAND_ADDRESS_ENG_SEARCH_LIST = "landAddressEngSearchList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class LandAddressEngSearchList
            extends AbstractType<LandAddressEngSearchList> {

        @Serial
        private static final long serialVersionUID = 7006810512668078489L;

        // -------------------------------------------------------------------------------------------------------------
        @JsonIgnore
        @EqualsAndHashCode.Exclude
        private transient LandAddressEngSearchListResponse parent;

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

    // ---------------------------------------------------------------------------------------------- super.cmmMsgHeader

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public LandAddressEngSearchListResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_LAND_ADDRESS_ENG_SEARCH_LIST)
    @XmlElement(name = NAME_LAND_ADDRESS_ENG_SEARCH_LIST)
    private List<@Valid @NotNull LandAddressEngSearchList> landAddressEngSearchList;
}
