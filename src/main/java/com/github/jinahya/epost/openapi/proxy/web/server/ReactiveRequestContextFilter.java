package com.github.jinahya.epost.openapi.proxy.web.server;

import com.github.jinahya.epost.openapi.proxy.reactor.util.context.ContextUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
// https://github.com/reactor/reactor-core/issues/2572
// https://www.pkslow.com/docs/en/spring-webflux-get-request/#webfilter-fetch-and-save
// https://gist.github.com/Elyorbe/c7cc5f28d0fced86dbd054dce0a9e206
public class ReactiveRequestContextFilter
        implements WebFilter {

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain chain) {
        return chain.filter(exchange)
                .contextWrite(c -> ContextUtils.putRequest(c, exchange.getRequest()));
    }
}
