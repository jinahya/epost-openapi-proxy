package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class CmmMsgHeaderUtils {

    private static final String KEY_ONE_INDEXED_PARAMETERS = "spring.data.web.pageable.one-indexed-parameters";

    /**
     * Converts specified header into an instance of {@link Page}.
     *
     * @param header  the header.
     * @param content a list of elements.
     * @param one     a value of {@value #KEY_ONE_INDEXED_PARAMETERS} application property.
     * @param <T>     element type parameter
     * @return an instance of {@link Page} converted from {@code header}.
     */
    public static <T> Page<T> toPage(final CmmMsgHeader header, final List<T> content, final boolean one) {
        Objects.requireNonNull(header, "header is null");
        Objects.requireNonNull(content, "content is null");
        assert header.getCurrentPage() > 0;
        assert content.size() <= header.getCountPerPage();
        final var pageNumber = header.getCurrentPage() - (one ? 0 : 1);
        final var pageSize = header.getCountPerPage();
        final var pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        final long total = header.getTotalCount();
        return new PageImpl<>(content, pageable, total);
    }

    public static <T> Page<T> toPage(final CmmMsgHeader header, final List<T> content, final Environment environment) {
        Objects.requireNonNull(environment, "environment is null");
        final var one = Optional.ofNullable(environment.getProperty(KEY_ONE_INDEXED_PARAMETERS))
                .map(Boolean::valueOf)
                .orElse(false);
        return toPage(header, content, one);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private CmmMsgHeaderUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
