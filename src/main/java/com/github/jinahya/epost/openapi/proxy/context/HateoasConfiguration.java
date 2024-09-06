package com.github.jinahya.epost.openapi.proxy.context;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.http.MediaType;

@Configuration
class HateoasConfiguration {

    @Bean
    HalConfiguration halConfiguration() {
        return new HalConfiguration()
                .withMediaType(MediaType.APPLICATION_NDJSON)
                .withObjectMapperCustomizer(o -> {
//                    o.disable(SerializationFeature.INDENT_OUTPUT);
                    o.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
                });
    }
}
