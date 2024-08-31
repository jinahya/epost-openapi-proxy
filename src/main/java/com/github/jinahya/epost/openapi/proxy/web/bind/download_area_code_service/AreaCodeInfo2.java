package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service.AreaCodeInfoResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel2;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AreaCodeInfo2
        extends AbstractWrappingModel2<AreaCodeInfo2, AreaCodeInfoResponse> {

    @Serial
    private static final long serialVersionUID = 5639131955588158747L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static AreaCodeInfo2 newInstance(final AreaCodeInfoResponse wrapped) {
        return newInstance(AreaCodeInfo2::new, wrapped);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------------------- wrapped
    @Override
    public void setWrapped(final AreaCodeInfoResponse response) {
        super.setWrapped(response);
        setDwldSe(response.getRequestInstance().getDwldSe());
        setFile(response.getFile());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String dwldSe;

    @NotBlank
    private String file;
}
