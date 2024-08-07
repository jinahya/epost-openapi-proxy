package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseElementAddressType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

@XmlRootElement(name = NewAddressListAreaCdSearchAllResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class NewAddressListAreaCdSearchAllResponse
        extends AbstractPairedResponseType<
        NewAddressListAreaCdSearchAllResponse,
        NewAddressListAreaCdSearchAllRequest> {

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
            extends AbstractResponseElementAddressType<
            NewAddressListAreaCdSearchAll,
            NewAddressListAreaCdSearchAllResponse> {

        @Serial
        private static final long serialVersionUID = -7680172168065174597L;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public NewAddressListAreaCdSearchAllResponse() {
        super(NewAddressListAreaCdSearchAllRequest.class);
    }

    // ---------------------------------------------------------------------------------------------- super.cmmMsgHeader

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public NewAddressListAreaCdSearchAllResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
    @XmlElement(name = NAME_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
    private List<@Valid @NotNull NewAddressListAreaCdSearchAll> newAddressListAreaCdSearchAll;
}
