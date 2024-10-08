package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_lot_number_adress_area_cd_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseElementAddressType;
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

import java.io.Serial;
import java.util.List;

@XmlRootElement(name = NewAddressListAreaCdSearchAllResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewAddressListAreaCdSearchAllResponse
        extends AbstractPairedResponseType<
        NewAddressListAreaCdSearchAllResponse,
        NewAddressListAreaCdSearchAllRequest> {

    @Serial
    private static final long serialVersionUID = -1527464956712592866L;

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
        private static final long serialVersionUID = -3719216662681924951L;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public NewAddressListAreaCdSearchAllResponse() {
        super(NewAddressListAreaCdSearchAllRequest.class);
    }

    // ---------------------------------------------------------------------------------------------- super.cmmMsgHeader

    // ---------------------------------------------------------------------=====------------------------- super.wrapped
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
