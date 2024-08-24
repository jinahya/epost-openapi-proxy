package com.github.jinahya.epost.openapi.proxy.reactor.util.context;

import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.net.MalformedURLException;

// https://github.com/reactor/reactor-core/issues/2572
// https://www.pkslow.com/docs/en/spring-webflux-get-request/#webfilter-fetch-and-save
// https://gist.github.com/Elyorbe/c7cc5f28d0fced86dbd054dce0a9e206
public final class ContextUtils {

    // -----------------------------------------------------------------------------------------------------------------
    private static final Class<ServerHttpRequest> KEY_REQUEST = ServerHttpRequest.class;

    public static Context putRequest(final Context context, final ServerHttpRequest request) {
        return context.put(KEY_REQUEST, request);
    }

    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.deferContextual(c -> Mono.just(c.get(KEY_REQUEST)));
    }

    public static Mono<String> getRequestBaseUrl() {
        return getRequest()
                .map(r -> {
                    try {
                        final var url = r.getURI().toURL();
                        return url.getProtocol() + "://" + url.getAuthority();
                    } catch (final MalformedURLException murle) {
                        throw new RuntimeException("failed to get url from " + r, murle);
                    }
                });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ContextUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
