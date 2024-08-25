package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import lombok.EqualsAndHashCode;
import org.springframework.http.MediaType;

import java.io.Serial;
import java.util.List;
import java.util.stream.Stream;

public final class AbstractRequestTypeTestUtils {

    @EqualsAndHashCode(callSuper = true)
    static class AbstractRequestTypeImpl
            extends AbstractRequestType<AbstractRequestTypeImpl> {

        @Serial
        private static final long serialVersionUID = -6710656304567871877L;
    }

    public static AbstractRequestType<?> prefabValue1() {
        final var value = new AbstractRequestTypeImpl();
        value.setServiceKey("a");
        return value;
    }

    public static AbstractRequestType<?> prefabValue2() {
        final var value = new AbstractRequestTypeImpl();
        value.setServiceKey("b");
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
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
