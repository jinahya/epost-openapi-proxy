package com.github.jinahya.epost.openapi.proxy._common;

import org.springframework.http.MediaType;

import java.util.stream.Stream;

public final class AbstractRequestTypeTestUtils {

    public static <T extends AbstractRequestType> Stream<T> mapMediaType(final Stream<? extends T> stream) {
        return stream.flatMap(e -> {
            return Stream.of(
                            null,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_JSON
                    )
                    .map(mt -> {
                        e.setAccept(mt);
                        return e;
                    });
        });
    }

    private AbstractRequestTypeTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }

}
