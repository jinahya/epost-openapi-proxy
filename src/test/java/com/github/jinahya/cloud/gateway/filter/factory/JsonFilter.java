package com.github.jinahya.cloud.gateway.filter.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RequestHeaderSizeGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
        final var config = new ModifyResponseBodyGatewayFilterFactory.Config();
        config.setRewriteFunction(
                String.class,
                String.class,
                (ex, iv) -> {
                    log.debug("inValue: {}", iv);
                    final var request = ex.getRequest();
                    final var accepts = request.getHeaders().getAccept();
                    final var response = ex.getResponse();
                    final var contentType = response.getHeaders().getContentType();
//                    if (accepts.contains(MediaTypes.HAL_JSON)) {
//                        if (contentType == null || contentType.isCompatibleWith(MediaType.APPLICATION_XML)) {
//                            try {
//                                final var unmarshalled = NewAddressListResponse.unmarshalInstance(new StringReader(iv));
//                                log.debug("unmarshalled: {}", unmarshalled);
//                                ex.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                                return Mono.just(serialized);
//                            } catch (final Exception e) {
//                                throw new RuntimeException("failed to unmarshal " + iv, e);
//                            }
//                        } else if (contentType.isCompatibleWith(MediaType.APPLICATION_JSON)) {
//                            try {
//                                final var serialized = unmarshalled.toJson(objectMapper);
//                                log.debug("serialized: {}", serialized);
//                                ex.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                                return Mono.just(serialized);
//                            } catch (final Exception e) {
//                                throw new RuntimeException("failed to unmarshal " + iv, e);
//                            }
//                        }
//                    }
                    return Mono.just(iv);
                }
        );
//        config.setNewContentType(MediaType.APPLICATION_JSON_VALUE);
        return filterFactory.apply(config);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    private ModifyResponseBodyGatewayFilterFactory filterFactory;

    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    private ObjectMapper objectMapper;
}
