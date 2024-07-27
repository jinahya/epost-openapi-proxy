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
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serial;
import java.util.List;

@XmlRootElement(name = DistrictEngListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DistrictEngListResponse
        extends AbstractSelfWrappingResponseType<DistrictEngListResponse> {

    @Serial
    private static final long serialVersionUID = -7654933306929922530L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "DistrictEngListResponse";

    private static final String NAME_DISTRICT_ENG_LIST = "districtEngList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DistrictEngList
            extends AbstractType {

        @Serial
        private static final long serialVersionUID = -3888280332264139184L;

        @NotBlank
        @JsonProperty
        @XmlElement
        private String districtEngName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    DistrictEngListResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
//    @Valid
//    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_DISTRICT_ENG_LIST)
    @XmlElement(name = NAME_DISTRICT_ENG_LIST)
    private List<@Valid @NotNull DistrictEngList> districtEngList;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private DistrictEngListResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(DistrictEngListResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static DistrictEngListResponse unmarshalInstance(final Object source) throws JAXBException {
        return AbstractTypeUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                DistrictEngListResponse.class,
                source
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static DistrictEngListResponse deserializeInstance(final ObjectReader reader, final Object source) {
        return AbstractTypeUtils.deserializeInstance(reader, source);
    }

    public static DistrictEngListResponse deserializeInstance(final ObjectMapper mapper, final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                mapper,
                DistrictEngListResponse.class,
                source
        );
    }

    public static DistrictEngListResponse deserializeInstance(final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                DistrictEngListResponse.class,
                source
        );
    }
}
