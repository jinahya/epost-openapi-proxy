package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class DbExtractor {

    private static void extract(final @NonNull InputStream stream, final String name,
                                final @NonNull BiConsumer<? super String, ? super Map<String, String>> consumer)
            throws IOException {
        Objects.requireNonNull(stream, "stream is null");
        Objects.requireNonNull(consumer, "consumer is null");
        final var reader = new BufferedReader(new InputStreamReader(stream));
        final var first = reader.readLine();
        if (first.isEmpty()) {
            return;
        }
        final var keys = List.of(first.split("\\|"));
        reader.lines()
                .map(l -> {
                    final var i = keys.iterator();
                    return Arrays.stream(l.split("\\|"))
                            .map(v -> new AbstractMap.SimpleEntry<>(i.next(), v))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y,
                                                      LinkedHashMap::new));
                })
                .forEach(m -> consumer.accept(name, m));
    }

    public static void extract(final @NonNull URL url, final @Nullable Integer connectTimeout,
                               final @Nullable Integer readTimeout,
                               final @NonNull BiConsumer<? super String, ? super Map<String, String>> consumer)
            throws IOException {
        Objects.requireNonNull(url, "url is null");
        Objects.requireNonNull(consumer, "consumer is null");
        final var connection = url.openConnection();
        Optional.ofNullable(connectTimeout).ifPresent(connection::setConnectTimeout);
        try {
            connection.connect();
            Optional.ofNullable(readTimeout).ifPresent(connection::setReadTimeout);
            try (var stream = new ZipInputStream(connection.getInputStream(), Charset.forName("EUC-KR"))) {
                for (ZipEntry entry; (entry = stream.getNextEntry()) != null; stream.closeEntry()) {
                    final var name = entry.getName();
                    if (!name.endsWith(".txt")) {
                        continue;
                    }
                    extract(stream, name, consumer);
                }
            }
        } finally {
            if (connection instanceof HttpURLConnection httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private DbExtractor() {
        throw new AssertionError("instantiation is not allowed");
    }
}