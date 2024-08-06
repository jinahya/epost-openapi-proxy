package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractResponseElementType<
        SELF extends AbstractResponseElementType<SELF, PARENT>,
        PARENT extends AbstractResponseType<PARENT>>
        extends AbstractType<SELF> {

    @Serial
    private static final long serialVersionUID = 3439311955635107788L;

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private transient PARENT parent;
}
