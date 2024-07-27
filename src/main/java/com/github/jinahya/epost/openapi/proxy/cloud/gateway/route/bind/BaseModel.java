package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.bind;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseModel<SELF extends BaseModel<SELF>>
        extends RepresentationModel<SELF>
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 6473809234332969018L;

    // -------------------------------------------------------------------------------------------------- STATIC_FACTORY_METHODS
    protected static <T extends BaseModel<T>> T newInstance(final Supplier<? extends T> initializer) {
        final var instance = Objects.requireNonNull(initializer, "initializer is null").get();
        return instance;
    }
}
