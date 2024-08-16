package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings({
        "java:S119" // <SELF ...>
})
public abstract class AbstractResponseType<SELF extends AbstractResponseType<SELF>>
        extends AbstractType<SELF> {

    @Serial
    private static final long serialVersionUID = 3542834861055866296L;

    // -----------------------------------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractResponseType<?> that)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return Objects.equals(cmmMsgHeader, that.cmmMsgHeader)
                && Objects.equals(wrapped, that.wrapped);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                cmmMsgHeader,
                wrapped
        );
    }

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

    // -----------------------------------------------------------------------------------------------------------------
    @Schema(hidden = true)
    @Valid
    @XmlTransient
    private SELF wrapped;
}
