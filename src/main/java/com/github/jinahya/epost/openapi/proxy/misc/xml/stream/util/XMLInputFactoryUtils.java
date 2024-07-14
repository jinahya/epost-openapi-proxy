package com.github.jinahya.epost.openapi.proxy.misc.xml.stream.util;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.util.Objects;

// https://stackoverflow.com/a/7206666/330457
public final class XMLInputFactoryUtils {

    public static <SOURCE> XMLStreamReader createXMLStreamReader(final XMLInputFactory xmlInputFactory,
                                                                 final Class<SOURCE> sourceClass,
                                                                 final SOURCE sourceInstance) {
        for (var method : XMLInputFactory.class.getMethods()) {
            if (!method.getName().equals("createXMLStreamReader")) {
                continue;
            }
            final var parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            if (!parameterTypes[0].isAssignableFrom(sourceClass)) {
                continue;
            }
            try {
                return (XMLStreamReader) method.invoke(xmlInputFactory, sourceInstance);
            } catch (ReflectiveOperationException roe) {
                throw new RuntimeException("unable to createXMLStreamReader for " + sourceInstance, roe);
            }
        }
        throw new IllegalArgumentException("unable to createXMLStreamReader for " + sourceInstance);
    }

    private static <SOURCE> XMLStreamReader createXMLStreamReaderHelper(final XMLInputFactory xmlInputFactory,
                                                                        final Class<SOURCE> sourceClass,
                                                                        final Object sourceInstance) {
        Objects.requireNonNull(sourceClass, "sourceClass is null");
        return createXMLStreamReader(
                xmlInputFactory,
                sourceClass,
                sourceClass.cast(sourceInstance)
        );
    }

    public static XMLStreamReader createXMLStreamReader(final XMLInputFactory xmlInputFactory,
                                                        final Object sourceInstance) {
        Objects.requireNonNull(sourceInstance, "sourceInstance is null");
        return createXMLStreamReaderHelper(
                xmlInputFactory,
                sourceInstance.getClass(),
                sourceInstance
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private XMLInputFactoryUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
