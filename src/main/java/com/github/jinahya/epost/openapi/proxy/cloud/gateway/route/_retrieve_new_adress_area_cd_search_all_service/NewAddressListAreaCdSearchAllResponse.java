package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_new_adress_area_cd_search_all_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractAddress;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractSelfWrappingResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.CmmMsgHeader;
import com.github.jinahya.epost.openapi.proxy._misc.jackson.databind.ObjectReaderUtils;
import com.github.jinahya.epost.openapi.proxy._misc.xml.stream.XMLInputFactoryUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = NewAddressListAreaCdSearchAllResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class NewAddressListAreaCdSearchAllResponse
        extends AbstractSelfWrappingResponseType<NewAddressListAreaCdSearchAllResponse> {

    @Serial
    private static final long serialVersionUID = 8214625216695174852L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "NewAddressListResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL = "newAddressListAreaCdSearchAll";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class NewAddressListAreaCdSearchAll
            extends AbstractAddress {

        @Serial
        private static final long serialVersionUID = 2648926399901817807L;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    NewAddressListAreaCdSearchAllResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
    @XmlElement(name = NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
    private List<@Valid @NotNull NewAddressListAreaCdSearchAll> newAddressListAreaCdSearchAll;

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private NewAddressListAreaCdSearchAllResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(NewAddressListAreaCdSearchAllResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static NewAddressListAreaCdSearchAllResponse unmarshalInstance(final Object source)
            throws JAXBException {
        return XMLInputFactoryUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                NewAddressListAreaCdSearchAllResponse.class,
                source
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static NewAddressListAreaCdSearchAllResponse deserializeInstance(final ObjectReader reader,
                                                                            final Object source) {
        return ObjectReaderUtils.readValue(
                reader,
                source
        );
    }

    public static NewAddressListAreaCdSearchAllResponse deserializeInstance(final ObjectMapper mapper,
                                                                            final Object source) {
        Objects.requireNonNull(mapper, "mapper is null");
        if (!mapper.isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE)) {
            log.warn("mapper is not enabled with {}", DeserializationFeature.UNWRAP_ROOT_VALUE);
        }
        return deserializeInstance(
                mapper.readerFor(NewAddressListAreaCdSearchAllResponse.class),
                source
        );
    }

    public static NewAddressListAreaCdSearchAllResponse deserializeInstance(final Object source) {
        return deserializeInstance(
                new ObjectMapper(),
                source
        );
    }
}
