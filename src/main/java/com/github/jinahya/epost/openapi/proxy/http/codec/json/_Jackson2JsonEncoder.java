package com.github.jinahya.epost.openapi.proxy.http.codec.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.core.ResolvableType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.MimeType;

import java.util.Map;

public class _Jackson2JsonEncoder
        extends Jackson2JsonEncoder {

    // -----------------------------------------------------------------------------------------------------------------
    public _Jackson2JsonEncoder() {
        super();
    }

    public _Jackson2JsonEncoder(final ObjectMapper mapper, final MimeType... mimeTypes) {
        super(mapper, mimeTypes);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected ObjectWriter customizeWriter(final ObjectWriter writer, final MimeType mimeType,
                                           final ResolvableType elementType,
                                           final Map<String, Object> hints) {
        return writer;
//        return super.customizeWriter(writer, mimeType, elementType, hints);
    }
}
