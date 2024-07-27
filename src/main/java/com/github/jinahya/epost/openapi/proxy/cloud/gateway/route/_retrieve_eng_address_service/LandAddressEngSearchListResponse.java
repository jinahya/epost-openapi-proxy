package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseType;
import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeUtils;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serial;
import java.util.List;

@XmlRootElement(name = LandAddressEngSearchListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LandAddressEngSearchListResponse
        extends AbstractSelfWrappingResponseType<LandAddressEngSearchListResponse> {

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
            extends AbstractType {

        @Serial
        private static final long serialVersionUID = 7006810512668078489L;

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
    LandAddressEngSearchListResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
//    @Valid
//    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_LAND_ADDRESS_ENG_SEARCH_LIST)
    @XmlElement(name = NAME_LAND_ADDRESS_ENG_SEARCH_LIST)
    private List<@Valid @NotNull LandAddressEngSearchList> landAddressEngSearchList;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private LandAddressEngSearchListResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(LandAddressEngSearchListResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static LandAddressEngSearchListResponse unmarshalInstance(final Object source) throws JAXBException {
        return AbstractTypeUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                LandAddressEngSearchListResponse.class,
                source
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static LandAddressEngSearchListResponse deserializeInstance(final ObjectReader reader, final Object source) {
        return AbstractTypeUtils.deserializeInstance(reader, source);
    }

    public static LandAddressEngSearchListResponse deserializeInstance(final ObjectMapper mapper, final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                mapper,
                LandAddressEngSearchListResponse.class,
                source
        );
    }

    public static LandAddressEngSearchListResponse deserializeInstance(final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                LandAddressEngSearchListResponse.class,
                source
        );
    }
}
