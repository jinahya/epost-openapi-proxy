package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
class _StatesGatewayFilterFactory
        extends AbstractGatewayFilterFactory<_StatesGatewayFilterFactory.Config> {

    // -----------------------------------------------------------------------------------------------------------------
    public static class Config {

    }

    // -----------------------------------------------------------------------------------------------------------------
    _StatesGatewayFilterFactory(final ModifyResponseBodyGatewayFilterFactory modifyResponseBodyFilterFactory) {
        super(Config.class);
        this.modifyResponseBodyFilterFactory =
                Objects.requireNonNull(modifyResponseBodyFilterFactory, "modifyResponseBodyFilterFactory is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // https://stackoverflow.com/a/45190717/330457
    // https://velog.io/@ljft183/SpringCloudGateway3
    @Override
    public GatewayFilter apply(final Config config) {
        return modifyResponseBodyFilterFactory.apply(c -> {
            c.setRewriteFunction(StateEngListResponse.class, Flux.class, (e, b) -> {
                return Mono.empty();
            });
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final ModifyResponseBodyGatewayFilterFactory modifyResponseBodyFilterFactory;
}
