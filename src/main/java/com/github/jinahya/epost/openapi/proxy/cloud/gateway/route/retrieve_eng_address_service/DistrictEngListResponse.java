package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

@XmlRootElement(name = DistrictEngListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DistrictEngListResponse
        extends AbstractPairedResponseType<DistrictEngListResponse, DistrictEngListRequest> {

    @Serial
    private static final long serialVersionUID = -7654933306929922530L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "DistrictEngListResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_DISTRICT_ENG_LIST = "districtEngList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DistrictEngList
            extends AbstractType<DistrictEngList> {

        @Serial
        private static final long serialVersionUID = -3888280332264139184L;

        // -----------------------------------------------------------------------------------------------------------------
        public static DistrictEngList of(final String districtEngName) {
            final var instance = new DistrictEngList();
            instance.setDistrictEngName(districtEngName);
            return instance;
        }

        // -------------------------------------------------------------------------------------------------------------
        @JsonIgnore
        @EqualsAndHashCode.Exclude
        private transient DistrictEngListResponse parent;

        @NotBlank
        @JsonProperty
        @XmlElement
        private String districtEngName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public DistrictEngListResponse() {
        super(DistrictEngListRequest.class);
    }

    // ---------------------------------------------------------------------------------------------- super.cmmMsgHeader

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public DistrictEngListResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_DISTRICT_ENG_LIST)
    @XmlElement(name = NAME_DISTRICT_ENG_LIST)
    private List<@Valid @NotNull DistrictEngList> districtEngList;
}
