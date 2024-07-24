package com.github.jinahya.epost.openapi.proxy._common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractRequestType
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -5536339965586422829L;

    // -----------------------------------------------------------------------------------------------------------------
    protected abstract URI buildUri(UriBuilder builder);

    // -----------------------------------------------------------------------------------------------------------------
    private String serviceKey;
}
