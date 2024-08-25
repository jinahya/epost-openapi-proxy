package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "epost.openapi.proxy")
@Setter(AccessLevel.PACKAGE)
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EpostOpenapiProxyConfiguration {

    // -----------------------------------------------------------------------------------------------------------------
    private String serviceKey;
}
