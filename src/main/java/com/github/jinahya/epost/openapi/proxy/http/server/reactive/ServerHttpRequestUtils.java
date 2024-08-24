package com.github.jinahya.epost.openapi.proxy.http.server.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

@Slf4j
public final class ServerHttpRequestUtils {

    private static String baseUrl(final URL url) {
        Objects.requireNonNull(url, "url is null");
        return url.getProtocol() + "://" + url.getAuthority();
    }

    private static String baseUrl(final URI uri) {
        Objects.requireNonNull(uri, "uri is null");
        try {
            return baseUrl(uri.toURL());
        } catch (final MalformedURLException murle) {
            log.warn("failed to get url from {}", uri, murle);
            return null;
        }
    }

    public static String baseUrl(final ServerHttpRequest request) {
        Objects.requireNonNull(request, "request is null");
        return baseUrl(request.getURI());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ServerHttpRequestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
