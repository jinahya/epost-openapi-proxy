package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;

import java.io.Serial;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings({
        "java:S119" // <SELF ...>
})
public abstract class AbstractResponseType<SELF extends AbstractResponseType<SELF>>
        extends AbstractType<SELF> {

    @Serial
    private static final long serialVersionUID = 3542834861055866296L;

    // -----------------------------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    @SuppressWarnings({"unchecked"})
    SELF cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- wrapped
    @SuppressWarnings({"unchecked"})
    public final SELF get() {
        if (wrapped != null) {
            return wrapped;
        }
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty
    @XmlElement
    private CmmMsgHeader cmmMsgHeader;

    @Valid
    @XmlTransient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PUBLIC)
//    @EqualsAndHashCode.Exclude
    private SELF wrapped;
}
