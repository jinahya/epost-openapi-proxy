package com.github.jinahya.epost.openapi.proxy.web.bind;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@SuppressWarnings({
        "java:S119"
})
public final class _ApiControllerTestUtils {

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> T getRandom(final List<T> list) {
        Objects.requireNonNull(list, "list is null");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        final var l = new ArrayList<>(list);
        Collections.shuffle(l);
        return list.getFirst();
    }

    public static <T> T getRandomContent(final List<? extends EntityModel<T>> list) {
        Objects.requireNonNull(list, "list is null");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        final var l = new ArrayList<>(list);
        Collections.shuffle(l);
        return l.getFirst().getContent();
    }

    public static <T> Mono<T> collectAndGetRandomContent(final Flux<? extends EntityModel<T>> list) {
        return list.collectList().map(_ApiControllerTestUtils::getRandomContent);
    }

    // ------------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private _ApiControllerTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
