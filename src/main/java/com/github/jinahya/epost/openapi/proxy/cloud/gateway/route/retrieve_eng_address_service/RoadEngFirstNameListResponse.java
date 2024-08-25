package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

@XmlRootElement(name = RoadEngFirstNameListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadEngFirstNameListResponse
        extends AbstractPairedResponseType<RoadEngFirstNameListResponse, RoadEngFirstNameListRequest> {

    @Serial
    private static final long serialVersionUID = -669670987447183138L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "RoadEngFirstNameListResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_ROAD_ENG_FIRST_NAME_LIST = "roadEngFirstNameList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class RoadEngFirstNameList
            extends AbstractType<RoadEngFirstNameList> {

        @Serial
        private static final long serialVersionUID = -437925892962234479L;

        // -------------------------------------------------------------------------------------------------------------
        @JsonIgnore
        @EqualsAndHashCode.Exclude
        private transient RoadEngFirstNameListResponse parent;

        @Size(max = 1)
        @NotBlank
        @JsonProperty
        @XmlElement
        private String roadEngFirstName;

        @Positive
        @NotNull
        private Integer cnt;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public RoadEngFirstNameListResponse() {
        super(RoadEngFirstNameListRequest.class);
    }

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader

    // --------------------------------------------------------------------------------------------------------- wrapper
    @JsonProperty(ROOT_NAME)
    @Override
    public RoadEngFirstNameListResponse getWrapped() {
        return super.getWrapped();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_ROAD_ENG_FIRST_NAME_LIST)
    @XmlElement(name = NAME_ROAD_ENG_FIRST_NAME_LIST)
    private List<@Valid @NotNull RoadEngFirstNameList> roadEngFirstNameList;
}
