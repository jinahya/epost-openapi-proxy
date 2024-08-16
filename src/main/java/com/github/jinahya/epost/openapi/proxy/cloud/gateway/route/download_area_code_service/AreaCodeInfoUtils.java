package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import org.springframework.lang.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class AreaCodeInfoUtils {

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

    /**
     * Extracts specified DB file stream, and accepts entry names of {@code *.txt} and rows to specified consumer.
     *
     * @param stream   the DB file stream to extract.
     * @param consumer the consumer.
     * @throws IOException if an I/O error occurs.
     */
    public static void extract(final @NonNull InputStream stream,
                               final @NonNull BiConsumer<? super String, ? super Map<String, String>> consumer)
            throws IOException {
        Objects.requireNonNull(stream, "stream is null");
        Objects.requireNonNull(consumer, "consumer is null");
        try (var zis = new ZipInputStream(stream, Charset.forName("EUC-KR"))) {
            for (ZipEntry entry; (entry = zis.getNextEntry()) != null; zis.closeEntry()) {
                final var name = entry.getName();
                if (!name.endsWith(".txt")) {
                    continue;
                }
                extract(zis, name, consumer);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AreaCodeInfoUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
