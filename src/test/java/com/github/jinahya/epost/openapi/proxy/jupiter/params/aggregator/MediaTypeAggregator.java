package com.github.jinahya.epost.openapi.proxy.jupiter.params.aggregator;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.stream.Stream;

public class MediaTypeAggregator
        implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(final ArgumentsAccessor accessor, final ParameterContext context)
            throws ArgumentsAggregationException {
        final var array = accessor.toArray();
        final var length = array.length;
        return Stream.of(
                        null,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_JSON
                )
                .map(mt -> {
                    final var copy = Arrays.copyOf(array, length + 1);
                    copy[array.length - 1] = mt;
                    return Arguments.of(copy);
                });
    }
}
