package com.github.jinahya.epost.openapi.proxy.cloud.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

//@Component
// https://github.com/spring-cloud/spring-cloud-gateway/issues/3119
class ExampleFilter
        implements GlobalFilter, Ordered {

    private static final String REQUEST_URI_AUTHORITY_NO_OP = "no://op";

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
//        final var headers = exchange.getRequest().getHeaders();
        final var request = exchange.getRequest();
        final var uri = request.getURI();
        if (!REQUEST_URI_AUTHORITY_NO_OP.equals(uri.getAuthority())) {
            return chain.filter(exchange);
        }
        final var builder = UriComponentsBuilder.fromUri(uri);
        builder.host("localhost");
        builder.port(serverPort);
        final var newUri = builder.build().toUri();
        final var mutatedRequest = exchange.getRequest().mutate().uri(newUri).build();
        exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, mutatedRequest.getURI());
        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Value("${server.port:8080}")
    private int serverPort;
}
