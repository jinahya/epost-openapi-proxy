package com.github.jinahya.epost.openapi.proxy.web.bind;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@SuppressWarnings({
        "java:S119" // <SELF ...>
})
public abstract class AbstractModel2<SELF extends AbstractModel2<SELF>>
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -2347216264885910369L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    protected static <T extends AbstractModel2> T newInstance(final Supplier<? extends T> initializer) {
        final T instance = Objects.requireNonNull(initializer, "initializer is null").get();
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    public HalModelBuilder builder(final Iterable<Link> links) {
        return HalModelBuilder.halModelOf(this)
                .links(links);
    }

    public RepresentationModel<EntityModel<SELF>> build(final Iterable<Link> links) {
        return builder(links)
                .build();
    }

    public <T extends RepresentationModel<T>> RepresentationModel<T> build2(final Iterable<Link> links) {
        return builder(links)
                .<T>build();
    }
}
