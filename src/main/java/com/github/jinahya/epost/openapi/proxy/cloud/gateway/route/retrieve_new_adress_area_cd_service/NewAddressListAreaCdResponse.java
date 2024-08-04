package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.CmmMsgHeader;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

@XmlRootElement(name = NewAddressListAreaCdResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NewAddressListAreaCdResponse
//        extends AbstractSelfWrappingResponseType<NewAddressListAreaCdResponse> {
        extends AbstractResponseType<NewAddressListAreaCdResponse> {

    @Serial
    private static final long serialVersionUID = 774107822436988264L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "NewAddressListResponse";

    private static final String NAME_NEW_ADDRESS_LIST_AREA_CD = "newAddressListAreaCd";

    @Setter
    @Getter
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public static class NewAddressListAreaCd
            extends AbstractType<NewAddressListAreaCd> {

        @Serial
        private static final long serialVersionUID = 493013101186485936L;

        // -----------------------------------------------------------------------------------------------------------------
        @Pattern(regexp = "\\d{5}")
        @NotNull
        private String zipNo;

        private String lnmAdres;

        private String rnAdres;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    NewAddressListAreaCdResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // --------------------------------------------------------------------------------------------------------- wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public NewAddressListAreaCdResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_NEW_ADDRESS_LIST_AREA_CD)
    @XmlElement(name = NAME_NEW_ADDRESS_LIST_AREA_CD)
    private List<@Valid @NotNull NewAddressListAreaCd> newAddressListAreaCdList;
}