package com.github.jinahya.epost.openapi.proxy._common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
public abstract class AbstractRequestType
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -5536339965586422829L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -----------------------------------------------------------------------------------------------------------------
    protected UriBuilder set(final UriBuilder builder) {
        Objects.requireNonNull(builder, "builder is null");
        return builder
                .queryParamIfPresent(_Constants.PARAM_SERVICE_KEY, Optional.ofNullable(serviceKey))
                ;
    }

    // ------------------------------------------------------------------------------------------------------ serviceKey

    // ---------------------------------------------------------------------------------------------------------- accept

    // -----------------------------------------------------------------------------------------------------------------
    @EqualsAndHashCode.Exclude
    private String serviceKey;

    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private MediaType accept;
}
