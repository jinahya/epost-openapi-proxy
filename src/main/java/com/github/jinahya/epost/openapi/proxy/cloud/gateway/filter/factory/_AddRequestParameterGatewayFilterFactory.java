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
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * A modified <a
 * href="https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-server/src/main/java/org/springframework/cloud/gateway/filter/factory/AddRequestParameterGatewayFilterFactory.java">AddRequestParameterGatewayFilterFactory.java</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a
 * href="https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-server/src/main/java/org/springframework/cloud/gateway/filter/factory/AddRequestParameterGatewayFilterFactory.java">The
 * original source</a>
 */
@Component
class _AddRequestParameterGatewayFilterFactory
        extends AbstractNameValueGatewayFilterFactory {

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    @Override
    public GatewayFilter apply(final NameValueConfig config) {
        return (e, c) -> {
            final var request = e.getRequest();
            final var uri = request.getURI();
            final var builder = UriComponentsBuilder.fromUri(uri);
            request.getQueryParams().forEach((k, l) -> builder.replaceQueryParam(
                    URLEncoder.encode(k, CHARSET),
                    l.stream().map(v -> URLEncoder.encode(v, CHARSET)).toList()
            ));
            builder.queryParam(
                    URLEncoder.encode(config.getName(), CHARSET),
                    URLEncoder.encode(config.getValue(), CHARSET)
            );
            final var newUri = builder.build(true).toUri();
            final var newRequest = request.mutate().uri(newUri).build();
            return c.filter(e.mutate().request(newRequest).build());
        };
    }

}
