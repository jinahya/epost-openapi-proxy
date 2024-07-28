package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.*;

import java.io.Serial;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(includeFieldNames = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractResponseType<SELF extends AbstractResponseType<SELF>>
        extends AbstractType<SELF> {

    @Serial
    private static final long serialVersionUID = 3542834861055866296L;

    // ---------------------------------------------------------------------------------------------------- cmmMsgHeader
    // just for the prefab values.
    @SuppressWarnings({"unchecked"})
    SELF cmmMsgHeader(final CmmMsgHeader cmmMsgHeader) {
        setCmmMsgHeader(cmmMsgHeader);
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    @JsonProperty
    @XmlElement
    private CmmMsgHeader cmmMsgHeader;
}
