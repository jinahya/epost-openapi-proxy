package com.github.jinahya.epost.openapi.proxy._common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
public abstract class AbstractRequestType
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -5536339965586422829L;

    // -----------------------------------------------------------------------------------------------------------------
    protected UriBuilder set(final UriBuilder builder) {
        Objects.requireNonNull(builder, "builder is null");
        return builder
                .queryParamIfPresent(_Constants.PARAM_SERVICE_KEY, Optional.ofNullable(serviceKey))
                ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String serviceKey;
}
