package com.github.jinahya.epost.openapi.proxy.web.bind.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractModel;
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
public class AreaCodeInfo
        extends AbstractModel<AreaCodeInfo> {

    @Serial
    private static final long serialVersionUID = 5639131955588158747L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static AreaCodeInfo newInstance(final String dwldSe, final String file) {
        final var instance = newInstance(AreaCodeInfo::new);
        instance.setDwldSe(dwldSe);
        instance.setFile(file);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String dwldSe;

    @NotBlank
    private String file;
}
