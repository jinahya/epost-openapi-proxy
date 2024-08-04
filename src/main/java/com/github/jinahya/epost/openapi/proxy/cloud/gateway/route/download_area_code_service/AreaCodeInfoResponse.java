package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import jakarta.validation.constraints.NotBlank;
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

@XmlRootElement(name = AreaCodeInfoResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Slf4j
public class AreaCodeInfoResponse
        extends AbstractResponseType<AreaCodeInfoResponse> {

    @Serial
    private static final long serialVersionUID = 9803126941295821L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "AreaCodeInfoResponse";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader

    // --------------------------------------------------------------------------------------------------------- wrapped
    @JsonProperty(ROOT_NAME)
    @Override
    public AreaCodeInfoResponse getWrapped() {
        return super.getWrapped();
    }

    // ------------------------------------------------------------------------------------------------------------ file

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @JsonProperty(required = true)
    @XmlElement(required = true, nillable = false)
    private String file;
}
