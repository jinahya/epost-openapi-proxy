package com.github.jinahya.epost.openapi.proxy.web.bind;

import org.assertj.core.api.AbstractAssert;
import org.springframework.hateoas.EntityModel;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityModelAssert<SELF extends EntityModelAssert<SELF, T>, T>
        extends AbstractAssert<SELF, EntityModel<T>> {

    protected EntityModelAssert(final EntityModel<T> actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // -------------------------------------------------------------------------------------------------- actual.content
    protected SELF acceptContent(final Consumer<? super T> consumer) {
        assertThat(actual)
                .isNotNull()
                .extracting(EntityModel::getContent)
                .satisfies(consumer);
        return myself;
    }

    public SELF hasContentSatisfies(final Consumer<? super T> consumer) {
        return acceptContent(c -> assertThat(c).isNotNull().satisfies(consumer));
    }

    public SELF hasContent() {
        return hasContentSatisfies(c -> {
            // empty
        });
    }

    public SELF doesNotHaveContent() {
        return acceptContent(c -> assertThat(c).isNull());
    }
}
