package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyDecoder;
import org.springframework.cloud.gateway.filter.factory.rewrite.MessageBodyEncoder;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.http.codec.ServerCodecConfigurer;

import java.util.Set;

public class SomeFilterFactory
        extends ModifyResponseBodyGatewayFilterFactory {

    public SomeFilterFactory(
            ServerCodecConfigurer codecConfigurer,
            Set<MessageBodyDecoder> messageBodyDecoders,
            Set<MessageBodyEncoder> messageBodyEncoders) {

        super(codecConfigurer.getReaders(), messageBodyDecoders, messageBodyEncoders);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return super.apply(config);
    }
}
