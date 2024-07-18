package com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_search_all_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.jinahya.epost.openapi.proxy._common.AbstractAddress;
import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@JsonRootName(GetNewAddressListAreaCdSearchAllResponse.ROOT_NAME)
@XmlRootElement(name = GetNewAddressListAreaCdSearchAllResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class GetNewAddressListAreaCdSearchAllResponse
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -1527464956712592866L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "NewAddressListResponse";

    private static final String NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL = "newAddressListAreaCdSearchAll";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class NewAddressListAreaCdSearchAll
            extends AbstractAddress {

        @Serial
        private static final long serialVersionUID = -3719216662681924951L;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    GetNewAddressListAreaCdSearchAllResponse cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
    @XmlElement(name = NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
    private List<@Valid @NotNull NewAddressListAreaCdSearchAll> newAddressListAreaCdSearchAll;

//    // -----------------------------------------------------------------------------------------------------------------
//    public GetNewAddressListAreaCdSearchAllResponse get() {
//        return Optional.ofNullable(wrapped).orElse(this);
//    }
//
//    @Valid
//    @JsonProperty(ROOT_NAME)
//    @Setter(AccessLevel.NONE)
//    @Getter(AccessLevel.NONE)
//    private GetNewAddressListAreaCdSearchAllResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(GetNewAddressListAreaCdSearchAllResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    public static GetNewAddressListAreaCdSearchAllResponse unmarshalInstance(final Object source)
            throws JAXBException {
        return XMLInputFactoryUtils.unmarshalNoNamespacedInstance(
                JAXB_CONTEXT,
                GetNewAddressListAreaCdSearchAllResponse.class,
                source
        );
    }

    public static GetNewAddressListAreaCdSearchAllResponse deserializeInstance(final ObjectReader reader,
                                                                               final Object source) {
        return ObjectReaderUtils.readValue(
                reader,
                source
        );
    }

    public static GetNewAddressListAreaCdSearchAllResponse deserializeInstance(final ObjectMapper mapper,
                                                                               final Object source) {
        Objects.requireNonNull(mapper, "mapper is null");
        if (!mapper.isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE)) {
            log.warn("mapper is not enabled with {}", DeserializationFeature.UNWRAP_ROOT_VALUE);
        }
        return deserializeInstance(
                mapper.readerFor(GetNewAddressListAreaCdSearchAllResponse.class),
                source
        );
    }

    public static GetNewAddressListAreaCdSearchAllResponse deserializeInstance(final Object source) {
        return deserializeInstance(
                new ObjectMapper().enable(DeserializationFeature.UNWRAP_ROOT_VALUE),
                source
        );
    }
}
