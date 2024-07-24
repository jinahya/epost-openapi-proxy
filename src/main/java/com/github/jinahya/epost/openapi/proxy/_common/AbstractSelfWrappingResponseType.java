package com.github.jinahya.epost.openapi.proxy._common;

import java.io.Serial;

public abstract class AbstractSelfWrappingResponseType<T extends AbstractSelfWrappingResponseType<T>>
        extends AbstractResponseType
        implements Wrapping.Self<T> {

    @Serial
    private static final long serialVersionUID = 6895247353547450259L;
}
