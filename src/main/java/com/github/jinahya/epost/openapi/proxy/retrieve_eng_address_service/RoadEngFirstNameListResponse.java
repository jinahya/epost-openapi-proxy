package com.github.jinahya.epost.openapi.proxy.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.jinahya.epost.openapi.proxy._common.AbstractSelfWrappingResponseType;
import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeUtils;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

import java.io.Serial;
import java.util.List;

//@XmlRootElement(name = StateEngListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class RoadEngFirstNameListResponse
        extends AbstractSelfWrappingResponseType<RoadEngFirstNameListResponse> {

    @Serial
    private static final long serialVersionUID = -669670987447183138L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "RoadEngFirstNameListResponse";

    private static final String NAME_ROAD_ENG_FIRST_NAME_LIST = "roadEngFirstNameList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class RoadEngFirstNameList
            extends AbstractType {

        @Serial
        private static final long serialVersionUID = -437925892962234479L;

        @Size(max = 1)
        @NotBlank
        @JsonProperty
        @XmlElement
        private String roadEngFirstName;

        @Positive
//        @PositiveOrZero
        @NotNull
        private Integer cnt;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    RoadEngFirstNameListResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_ROAD_ENG_FIRST_NAME_LIST)
    @XmlElement(name = NAME_ROAD_ENG_FIRST_NAME_LIST)
    private List<@Valid @NotNull RoadEngFirstNameList> roadEngFirstNameList;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private RoadEngFirstNameListResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(RoadEngFirstNameListResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static RoadEngFirstNameListResponse unmarshalInstance(final Object source) throws JAXBException {
        return AbstractTypeUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                RoadEngFirstNameListResponse.class,
                source
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static RoadEngFirstNameListResponse deserializeInstance(final ObjectReader reader, final Object source) {
        return AbstractTypeUtils.deserializeInstance(reader, source);
    }

    public static RoadEngFirstNameListResponse deserializeInstance(final ObjectMapper mapper, final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                mapper,
                RoadEngFirstNameListResponse.class,
                source
        );
    }

    public static RoadEngFirstNameListResponse deserializeInstance(final Object source) {
        return AbstractTypeUtils.deserializeInstance(
                RoadEngFirstNameListResponse.class,
                source
        );
    }
}
