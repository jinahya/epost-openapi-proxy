package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public final class AbstractResponseTypeUtils {

    public static <RESPONSE extends AbstractResponseType<RESPONSE>, T> Page<T> toPage(
            final RESPONSE response, final Function<? super RESPONSE, ? extends List<T>> content,
            final boolean one) {
        return CmmMsgHeaderUtils.toPage(response.getCmmMsgHeader(), content.apply(response), one);
    }

    public static <RESPONSE extends AbstractResponseType<RESPONSE>, T> Page<T> toPage(
            final RESPONSE response, final Function<? super RESPONSE, ? extends List<T>> content,
            final Environment environment) {
        return CmmMsgHeaderUtils.toPage(response.getCmmMsgHeader(), content.apply(response), environment);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AbstractResponseTypeUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
