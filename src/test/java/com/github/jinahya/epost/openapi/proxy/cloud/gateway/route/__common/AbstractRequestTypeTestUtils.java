package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Stream;

public final class AbstractRequestTypeTestUtils {

    public static <T extends AbstractRequestType<T>> Stream<T> mapMediaType(final Stream<? extends T> stream) {
        return stream.flatMap(e -> {
            return Stream.of(
                            null,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_JSON
                    )
                    .map(mt -> {
                        final var headersConsumer = e.getHeadersConsumer();
                        e.setHeadersConsumer(h -> {
                            headersConsumer.accept(h);
                            if (mt != null) {
                                h.setAccept(List.of(mt));
                            }
                        });
                        return e;
                    });
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AbstractRequestTypeTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
