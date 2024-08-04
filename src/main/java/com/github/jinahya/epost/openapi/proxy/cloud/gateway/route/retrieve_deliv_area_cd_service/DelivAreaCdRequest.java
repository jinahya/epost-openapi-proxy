package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_deliv_area_cd_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedRequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DelivAreaCdRequest
        extends AbstractPairedRequestType<DelivAreaCdRequest, DelivAreaCdResponse> {

    @Serial
    private static final long serialVersionUID = -4097942189442536529L;

    // -----------------------------------------------------------------------------------------------------------------
    public enum MailDivCd {
        _1,
        _2;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static DelivAreaCdRequest of(final String serviceKey, final String zip, final String addr,
                                        final String mailDivCd) {
        final var instance = of(DelivAreaCdRequest::new, serviceKey);
        instance.setZip(zip);
        instance.setAddr(addr);
        instance.setMailDivCd(mailDivCd);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public DelivAreaCdRequest() {
        super(DelivAreaCdResponse.class);
        setUriConsumer(
                (s, b) -> {
                    b.path(_RetrieveDelivAreaCdServiceConstants.REQUEST_URI_GET_DELIV_AREA_CD)
                            .queryParam(_RetrieveDelivAreaCdServiceConstants.PARAM_ZIP, s.zip)
                            .queryParam(_RetrieveDelivAreaCdServiceConstants.PARAM_ADDR, s.addr)
                            .queryParam(_RetrieveDelivAreaCdServiceConstants.PARAM_MAIL_DIV_CD, s.mailDivCd);
                },
                true
        );
    }

    // ------------------------------------------------------------------------------------------------------------- zip

    // ------------------------------------------------------------------------------------------------------------ addr

    // ------------------------------------------------------------------------------------------------------- mailDivCd

    // -----------------------------------------------------------------------------------------------------------------
    @Pattern(regexp = "\\d{5}")
    @Size(min = 5, max = 5)
    @NotBlank
    private String zip;

    @NotNull
    private String addr;

    @NotNull
    private String mailDivCd;
}
