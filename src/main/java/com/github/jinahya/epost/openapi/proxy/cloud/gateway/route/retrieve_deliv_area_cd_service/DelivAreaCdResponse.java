package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_deliv_area_cd_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.List;

@XmlRootElement(name = DelivAreaCdResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DelivAreaCdResponse
        extends AbstractPairedResponseType<DelivAreaCdResponse, DelivAreaCdRequest> {

    @Serial
    private static final long serialVersionUID = 383879671887152669L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "DelivAreaCdResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_DELIV_AREA_CD_LIST = "delivAreaCdList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class DelivAreaCdList
            extends AbstractType<DelivAreaCdList> {

        @Serial
        private static final long serialVersionUID = 2857913872559424421L;

        // ------------------------------------------------------------------------------------------------------- parent
        public DelivAreaCdList parent(final DelivAreaCdResponse parent) {
            setParent(parent);
            return this;
        }

        // -------------------------------------------------------------------------------------------------------------
        @JsonIgnore
        @EqualsAndHashCode.Exclude
        private transient DelivAreaCdResponse parent;

        @NotBlank
        @JsonProperty(required = true)
        @XmlElement(required = true, nillable = false)
        private String arrCnpoNm;

        @NotBlank
        @JsonProperty(required = true)
        @XmlElement(required = true, nillable = false)
        private String delivPoNm;

        @NotBlank
        @JsonProperty(required = true)
        @XmlElement(required = true, nillable = false)
        private String delivAreaCd;

        @JsonProperty
        @XmlElement
        private String courseNo;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public DelivAreaCdResponse() {
        super(DelivAreaCdRequest.class);
    }

    // ---------------------------------------------------------------------------------------------- super.cmmMsgHeader

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public DelivAreaCdResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_DELIV_AREA_CD_LIST)
    @XmlElement(name = NAME_DELIV_AREA_CD_LIST)
    private List<@Valid @NotNull DelivAreaCdList> delivAreaCdList;
}
