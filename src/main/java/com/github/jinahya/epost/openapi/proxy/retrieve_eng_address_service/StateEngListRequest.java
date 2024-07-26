package com.github.jinahya.epost.openapi.proxy.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StateEngListRequest
        extends AbstractRequestType {

    @Serial
    private static final long serialVersionUID = 4471882633229837577L;

    @Override
    protected UriBuilder set(final UriBuilder builder) {
        return super.set(builder);
    }
}
