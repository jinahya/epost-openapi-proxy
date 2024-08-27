package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
// https://github.com/junit-team/junit5/issues/1477
class AreaCodeInfoUtilsTest {

    private static Stream<String> getResNameStream() {
        return Stream.of(
                "zipcode_DB.zip",
                "areacd_chgaddr_DB.zip",
                "areacd_rangeaddr_DB.zip",
                "areacd_pobox_DB.zip"
        );
    }

    private static Stream<URL> getResourceUrlStream() {
        return getResNameStream()
                .map(AreaCodeInfoUtilsTest.class::getResource)
                .filter(Objects::nonNull);
    }

    private static Stream<File> getResourceFileStream() {
        return getResourceUrlStream()
                .map(v -> {
                    try {
                        return v.toURI();
                    } catch (final URISyntaxException urise) {
                        throw new RuntimeException("failed to get URI from " + v, urise);
                    }
                })
                .map(File::new);
    }

    private static List<String> getEntryNameList(final ZipFile zipFile) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                zipFile.entries().asIterator(),
                                Spliterator.ORDERED
                        ),
                        false
                )
                .filter(e -> !e.isDirectory())
                .map(ZipEntry::getName)
                .filter(n -> n.endsWith(".txt"))
                .toList();
    }

    private static Stream<Arguments> getResourceFileAndEntryNameArgumentsStream() {
        return getResourceFileStream()
                .flatMap(f -> {
                    try (var zipFile = new ZipFile(f, AreaCodeInfoUtils.CHARSET)) {
                        return getEntryNameList(zipFile)
                                .stream()
                                .map(n -> arguments(Named.of(f.getName(), f), n));
                    } catch (final IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("extract(stream, consumer)")
    @MethodSource({
            "getResNameStream"
    })
    @ParameterizedTest
    void extract__(final String resName) throws IOException {
        try (var resource = getClass().getResourceAsStream(resName)) {
            assumeTrue(resource != null, () -> "null resource for " + resource);
            final var flags = new HashMap<String, Boolean>();
            AreaCodeInfoUtils.extract(
                    resource,
                    (n, m) -> {
                        if (flags.compute(n, (k, v) -> v == null)) {
                            log.debug("n: {}, m: {}", n, m);
                        }
                    });
        }
    }

    @DisplayName("extract(file, predicate, consumer)")
    @MethodSource({
            "getResourceFileStream"
    })
    @ParameterizedTest
    void extract__(final File file) throws IOException {
        final var flags = new HashMap<ZipEntry, Boolean>();
        AreaCodeInfoUtils.extract(
                file,
                e -> e.getName().endsWith(".txt"),
                (e, m) -> {
                    if (flags.compute(e, (k, v) -> v == null)) {
                        log.debug("n: {}, m: {}", e, m);
                    }
                }
        );
    }

    @DisplayName("extract(file, name, consumer)")
    @MethodSource({
            "getResourceFileAndEntryNameArgumentsStream"
    })
    @ParameterizedTest
    void extract__(final File file, final String name) throws IOException {
        final var flag = new AtomicBoolean();
        AreaCodeInfoUtils.extract(
                file,
                name,
                m -> {
                    if (!flag.getAndSet(true)) {
                        log.debug("m: {}", m);
                    }
                }
        );
    }
}
