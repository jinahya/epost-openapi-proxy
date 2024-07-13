package com.github.jinahya.cloud.gateway.filter.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdservice.NewAddressListResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RequestHeaderSizeGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
// https://stackoverflow.com/a/72162154/330457
class JsonFilter
        extends AbstractGatewayFilterFactory<JsonFilter.Config> {

    @Autowired
    private RequestHeaderSizeGatewayFilterFactory requestHeaderSizeGatewayFilterFactory;

    static class Config {

    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    JsonFilter() {
        super(Config.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @PostConstruct
    private void doOnPostConstruct() {
        objectMapper = objectMapperBuilder.build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public GatewayFilter apply(final Config ignored) {
        return modifyResponseBodyGatewayFilterFactory.apply(c -> {
            c.setRewriteFunction(
                    NewAddressListResponse.class,
                    NewAddressListResponse.class,
                    (ex, iv) -> {
                        return Mono.just(iv);
                    }
            );
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private ModifyRequestBodyGatewayFilterFactory modifyRequestBodyGatewayFilterFactory;

    @Autowired
    private ModifyResponseBodyGatewayFilterFactory modifyResponseBodyGatewayFilterFactory;

    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    private ObjectMapper objectMapper;

    // -----------------------------------------------------------------------------------------------------------------
    private List<MediaType> requestAccepts;
}
