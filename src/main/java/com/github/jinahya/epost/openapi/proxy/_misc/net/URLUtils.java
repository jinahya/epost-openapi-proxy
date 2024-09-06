package com.github.jinahya.epost.openapi.proxy._misc.net;

import java.nio.file.FileSystems;

public final class URLUtils {

    public static String getFileName(final String first, final String... more) {
        return FileSystems.getDefault().getPath(first, more)
                .getFileName()
                .toString();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private URLUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
