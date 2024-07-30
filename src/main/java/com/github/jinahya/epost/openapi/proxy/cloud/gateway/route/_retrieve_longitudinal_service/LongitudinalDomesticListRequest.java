package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_longitudinal_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedRequestType;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LongitudinalDomesticListRequest
        extends AbstractPairedRequestType<LongitudinalDomesticListRequest, LongitudinalDomesticListResponse> {

    @Serial
    private static final long serialVersionUID = -5844123251942291873L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public LongitudinalDomesticListRequest() {
        super(LongitudinalDomesticListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String rgist;
}
