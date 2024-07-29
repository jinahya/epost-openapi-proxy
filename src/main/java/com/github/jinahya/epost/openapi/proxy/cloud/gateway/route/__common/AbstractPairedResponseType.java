package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuppressWarnings({
        "java:S119" // <SELF ...>
})
public abstract class AbstractPairedResponseType<
        SELF extends AbstractPairedResponseType<SELF, REQUEST>,
        REQUEST extends AbstractRequestType<REQUEST>>
        extends AbstractResponseType<SELF> {

    @Serial
    private static final long serialVersionUID = -9157434345601279739L;

    // ------------------------------------------------------------------------------------------------------ CONSTRUCTORS
    protected AbstractPairedResponseType(final Class<REQUEST> requestClass) {
        super();
        this.requestClass = Objects.requireNonNull(requestClass, "requestClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    protected final Class<REQUEST> requestClass;

    @JsonIgnore
//    @XmlTransient // Transient field "requestInstance" cannot have any JAXB annotations.
    private transient REQUEST requestInstance;
}
