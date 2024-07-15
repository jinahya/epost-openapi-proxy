package com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.jinahya.epost.openapi.proxy.bind.AbstractType;
import com.github.jinahya.epost.openapi.proxy.bind.CmmMsgHeader;
import com.github.jinahya.epost.openapi.proxy.misc.jackson.databind.ObjectReaderUtils;
import com.github.jinahya.epost.openapi.proxy.misc.xml.stream.XMLInputFactoryUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serial;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@XmlRootElement(name = NewAddressListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class NewAddressListResponse
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -1527464956712592866L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "NewAddressListResponse";

    private static final String NAME_NEW_ADDRESS_LIST_AREA_CD = "newAddressListAreaCd";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = false)
    public static class NewAddressListAreaCd
            extends AbstractType {

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
    NewAddressListResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_NEW_ADDRESS_LIST_AREA_CD)
    @XmlElement(name = NAME_NEW_ADDRESS_LIST_AREA_CD)
    private List<@Valid @NotNull NewAddressListAreaCd> newAddressListAreaCdList;

    // -----------------------------------------------------------------------------------------------------------------
    public NewAddressListResponse get() {
        return Optional.ofNullable(wrapped).orElse(this);
    }

    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private NewAddressListResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(NewAddressListResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static NewAddressListResponse unmarshalInstance(final Object source) throws JAXBException {
        return XMLInputFactoryUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                NewAddressListResponse.class,
                source
        );
    }

    public static NewAddressListResponse deserializeInstance(final ObjectReader reader, final Object source) {
        Objects.requireNonNull(reader, "reader is null");
        Objects.requireNonNull(source, "source is null");
        return ObjectReaderUtils.readValue(
                reader,
                source
        );
    }

    public static NewAddressListResponse deserializeInstance(final ObjectMapper mapper, final Object source) {
        Objects.requireNonNull(mapper, "mapper is null");
        return deserializeInstance(
                mapper.readerFor(NewAddressListResponse.class),
                source
        );
    }

    public static NewAddressListResponse deserializeInstance(final Object source) {
        return deserializeInstance(
                new ObjectMapper(),
                source
        );
    }
}
