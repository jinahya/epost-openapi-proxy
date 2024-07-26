package com.github.jinahya.epost.openapi.proxy.jupiter.params.aggregator;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.springframework.http.MediaType;

import java.util.stream.Stream;

public class AbstractRequestTypeMediaTypeAggregator
        implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(final ArgumentsAccessor accessor, final ParameterContext context)
            throws ArgumentsAggregationException {
        final var request = accessor.get(0, AbstractRequestType.class);
        return Stream.of(
                        null,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_JSON
                )
                .map(mt -> {
                    request.setAccept(mt);
                    return request;
                });
    }
}
