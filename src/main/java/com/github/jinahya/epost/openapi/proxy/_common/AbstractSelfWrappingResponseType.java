package com.github.jinahya.epost.openapi.proxy._common;

import lombok.*;

import java.io.Serial;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSelfWrappingResponseType<SELF extends AbstractSelfWrappingResponseType<SELF>>
        extends AbstractResponseType<SELF>
        implements Wrapping.Self<SELF> {

    @Serial
    private static final long serialVersionUID = 6895247353547450259L;
}
