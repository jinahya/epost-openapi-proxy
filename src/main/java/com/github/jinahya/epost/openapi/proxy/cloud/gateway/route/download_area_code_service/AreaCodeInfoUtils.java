package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import org.springframework.lang.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public final class AreaCodeInfoUtils {

    static final Charset CHARSET = Charset.forName("EUC-KR");

    private static void extract(final @NonNull InputStream stream,
                                final @NonNull Consumer<? super Map<String, String>> consumer)
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
                .forEach(consumer::accept);
    }

    private static void extract(final @NonNull InputStream stream, final String name,
                                final @NonNull BiConsumer<? super String, ? super Map<String, String>> consumer)
            throws IOException {
        Objects.requireNonNull(consumer, "consumer is null");
        extract(
                stream,
                m -> consumer.accept(name, m)
        );
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
        try (var zis = new ZipInputStream(stream, CHARSET)) {
            for (ZipEntry entry; (entry = zis.getNextEntry()) != null; zis.closeEntry()) {
                final var name = entry.getName();
                if (!name.endsWith(".txt")) {
                    continue;
                }
                extract(zis, name, consumer);
            }
        }
    }

    public static void extract(final File file, final String name, final Consumer<? super Map<String, String>> consumer)
            throws IOException {
        if (!Objects.requireNonNull(file, "file is null").isFile()) {
            throw new IllegalArgumentException("file is not a regular file: " + file);
        }
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(consumer, "consumer is null");
        try (var zipFile = new ZipFile(file, CHARSET)) {
            final var entry = zipFile.getEntry(name);
            if (entry == null) {
                throw new IllegalArgumentException("no entry for '" + name + "'");
            }
            try (final var stream = zipFile.getInputStream(entry)) {
                extract(
                        stream, consumer
                );
            }
        }
    }

    @SuppressWarnings({
            "java:S112" // new RuntimeException
    })
    public static void extract(final File file, final Predicate<? super ZipEntry> predicate,
                               final BiConsumer<? super ZipEntry, ? super Map<String, String>> consumer)
            throws IOException {
        if (!Objects.requireNonNull(file, "file is null").isFile()) {
            throw new IllegalArgumentException("file is not a regular file: " + file);
        }
        Objects.requireNonNull(predicate, "predicate is null");
        Objects.requireNonNull(consumer, "consumer is null");
        try (var zipFile = new ZipFile(file, CHARSET)) {
            zipFile.stream().filter(predicate).forEach(e -> {
                try (final var stream = zipFile.getInputStream(e)) {
                    extract(stream, m -> consumer.accept(e, m));
                } catch (final IOException ioe) {
                    throw new RuntimeException("failed to extract " + file, ioe);
                }
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private AreaCodeInfoUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
