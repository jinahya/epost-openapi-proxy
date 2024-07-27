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
import jakarta.validation.constraints.Positive;
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

@XmlRootElement(name = DistrictEngFirstNameListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DistrictEngFirstNameListResponse
        extends AbstractSelfWrappingResponseType<DistrictEngFirstNameListResponse> {

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
            extends AbstractType {

        @Serial
        private static final long serialVersionUID = -7393190937923231268L;

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
    // just for the prefab values.
    DistrictEngFirstNameListResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
//    @Valid
//    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_DISTRICT_ENG_FIRST_NAME_LIST)
    @XmlElement(name = NAME_DISTRICT_ENG_FIRST_NAME_LIST)
    private List<@Valid @NotNull DistrictEngFirstNameList> districtEngFirstNameList;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private DistrictEngFirstNameListResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(DistrictEngFirstNameListResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static DistrictEngFirstNameListResponse unmarshalInstance(final Object source) throws JAXBException {
        return AbstractTypeUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                DistrictEngFirstNameListResponse.class,
                source
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static DistrictEngFirstNameListResponse deserializeInstance(final ObjectReader reader, final Object source) {
        return AbstractTypeUtils.deserializeInstance(reader, source);
    }

    public static DistrictEngFirstNameListResponse deserializeInstance(final ObjectMapper mapper, final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                mapper,
                DistrictEngFirstNameListResponse.class,
                source
        );
    }

    public static DistrictEngFirstNameListResponse deserializeInstance(final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                DistrictEngFirstNameListResponse.class,
                source
        );
    }
}
