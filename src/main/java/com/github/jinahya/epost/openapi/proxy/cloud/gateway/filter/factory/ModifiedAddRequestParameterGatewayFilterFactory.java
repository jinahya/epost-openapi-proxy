/*
 * Copyright 2013-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jinahya.epost.openapi.proxy.cloud.gateway.filter.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;

/**
 * A modified <a
 * href="https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-server/src/main/java/org/springframework/cloud/gateway/filter/factory/AddRequestParameterGatewayFilterFactory.java">AddRequestParameterGatewayFilterFactory.java</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a
 * href="https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-server/src/main/java/org/springframework/cloud/gateway/filter/factory/AddRequestParameterGatewayFilterFactory.java">The
 * original source</a>
 */
public class ModifiedAddRequestParameterGatewayFilterFactory
        extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(final NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
                final var request = exchange.getRequest();
                final var uri = request.getURI();
                final var builder = UriComponentsBuilder.fromUri(uri);
                request.getQueryParams().forEach(builder::replaceQueryParam);
                builder.queryParam(config.getName(), config.getValue());
                final var newUri = builder.build(true).toUri();
                final var newRequest = exchange.getRequest().mutate().uri(newUri).build();
                return chain.filter(exchange.mutate().request(newRequest).build());
            }

            @Override
            public String toString() {
                return filterToStringCreator(ModifiedAddRequestParameterGatewayFilterFactory.this)
                        .append(config.getName(), config.getValue())
                        .toString();
            }
        };
    }

}
