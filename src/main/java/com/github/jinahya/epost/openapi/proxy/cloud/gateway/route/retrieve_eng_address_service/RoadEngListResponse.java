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

@XmlRootElement(name = RoadEngListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadEngListResponse
        extends AbstractPairedResponseType<RoadEngListResponse, RoadEngListRequest> {

    @Serial
    private static final long serialVersionUID = 2017574848867792334L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "RoadEngListResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_ROAD_ENG_LIST = "roadEngList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class RoadEngList
            extends AbstractType<RoadEngList> {

        @Serial
        private static final long serialVersionUID = 2944701965921059011L;

        // -------------------------------------------------------------------------------------------------------------
        public static RoadEngList of(final String roadEngName) {
            final var instance = new RoadEngList();
            instance.setRoadEngName(roadEngName);
            return instance;
        }

        // -------------------------------------------------------------------------------------------------------------
        @JsonIgnore
        @EqualsAndHashCode.Exclude
        private transient RoadEngListResponse parent;

        @NotBlank
        @JsonProperty
        @XmlElement
        private String roadEngName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    public RoadEngListResponse() {
        super(RoadEngListRequest.class);
    }

    // ---------------------------------------------------------------------------------------------- super.cmmMsgHeader

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public RoadEngListResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_ROAD_ENG_LIST)
    @XmlElement(name = NAME_ROAD_ENG_LIST)
    private List<@Valid @NotNull RoadEngList> roadEngList;
}
