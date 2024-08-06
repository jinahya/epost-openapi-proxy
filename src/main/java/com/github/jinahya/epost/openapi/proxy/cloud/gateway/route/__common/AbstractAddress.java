package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

// -----------------------------------------------------------------------------------------------------------------
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractAddress<SELF extends AbstractAddress<SELF>>
        extends AbstractType<SELF> {

    @Serial
    private static final long serialVersionUID = -4210995980428120824L;

    // -----------------------------------------------------------------------------------------------------------------
    @Pattern(regexp = "\\d{5}")
    @NotNull
    private String zipNo;

    // 도로명 주소
    private String lnmAdres;

    // 지번 주소
    private String rnAdres;
}
