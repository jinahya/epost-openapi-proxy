package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public abstract class AbstractWrappingModel2<SELF extends AbstractWrappingModel2<SELF, WRAPPED>, WRAPPED>
        extends AbstractModel2<SELF> {

    @Serial
    private static final long serialVersionUID = -2347216264885910369L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    protected static <MODEL extends AbstractWrappingModel2<MODEL, WRAPPED>, WRAPPED> MODEL
    newInstance(final Supplier<? extends MODEL> initializer, final WRAPPED wrapped) {
        final MODEL instance = newInstance(initializer);
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public HalModelBuilder builder(final Iterable<Link> links) {
        return super.builder(links)
                .embed(getWrapped());
    }

    @Override
    public RepresentationModel<EntityModel<SELF>> build(final Iterable<Link> links) {
        return super.build(links);
    }

    @Override
    public <T extends RepresentationModel<T>> RepresentationModel<T> build2(Iterable<Link> links) {
        return super.build2(links);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    private WRAPPED wrapped;
}
