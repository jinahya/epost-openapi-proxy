package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._download_area_code_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractSelfWrappingResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.CmmMsgHeader;
import com.github.jinahya.epost.openapi.proxy._misc.jackson.databind.ObjectReaderUtils;
import com.github.jinahya.epost.openapi.proxy._misc.xml.stream.XMLInputFactoryUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.Serial;
import java.util.Objects;

//@JsonRootName(AreaCodeInfoResponse.ROOT_NAME)
@XmlRootElement(name = AreaCodeInfoResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AreaCodeInfoResponse
        extends AbstractSelfWrappingResponseType<AreaCodeInfoResponse> {

    @Serial
    private static final long serialVersionUID = 9803126941295821L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "AreaCodeInfoResponse";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    AreaCodeInfoResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
//    @Valid
//    private CmmMsgHeader cmmMsgHeader;

    @NotBlank
    @JsonProperty(required = true)
    @XmlElement(required = true, nillable = false)
    private String file;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(AreaCodeInfoResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static AreaCodeInfoResponse unmarshalInstance(final Object source)
            throws JAXBException {
        return XMLInputFactoryUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                AreaCodeInfoResponse.class,
                source
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static AreaCodeInfoResponse deserializeInstance(final ObjectReader reader,
                                                           final Object source) {
        return ObjectReaderUtils.readValue(
                reader,
                source
        );
    }

    public static AreaCodeInfoResponse deserializeInstance(final ObjectMapper mapper,
                                                           final Object source) {
        Objects.requireNonNull(mapper, "mapper is null");
        return deserializeInstance(
                mapper.readerFor(AreaCodeInfoResponse.class),
                source
        );
    }

    public static AreaCodeInfoResponse deserializeInstance(final Jackson2ObjectMapperBuilder builder,
                                                           final Object source) {
        Objects.requireNonNull(builder, "builder is null");
        return deserializeInstance(
                builder.build(),
                source
        );
    }

    public static AreaCodeInfoResponse deserializeInstance(final Object source) {
        return deserializeInstance(
                new ObjectMapper(),
                source
        );
    }
}
