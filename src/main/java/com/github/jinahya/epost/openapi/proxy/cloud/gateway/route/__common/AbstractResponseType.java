package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
//@EqualsAndHashCode(callSuper = true)
@ToString(includeFieldNames = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings({
        "java:S119" // <SELF ...>
})
public abstract class AbstractResponseType<SELF extends AbstractResponseType<SELF>>
        extends AbstractType<SELF> {

    @Serial
    private static final long serialVersionUID = 3542834861055866296L;

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbstractResponseType<?> that)) return false;
        if (!super.equals(obj)) return false;
        return Objects.equals(get().getCmmMsgHeader(), that.get().getCmmMsgHeader())
                && Objects.equals(get().getWrapped(), that.get().getWrapped());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), get().getCmmMsgHeader(), get().getWrapped());
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

    @Valid
    @XmlTransient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PUBLIC)
//    @EqualsAndHashCode.Exclude
    private SELF wrapped;
}
