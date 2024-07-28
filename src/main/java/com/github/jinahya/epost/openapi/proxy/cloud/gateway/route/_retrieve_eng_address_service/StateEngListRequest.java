package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

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
        extends AbstractRequestType<StateEngListRequest> {

    @Serial
    private static final long serialVersionUID = 4471882633229837577L;

    // -------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public StateEngListRequest() {
        super();
        super.setUriConsumer(
                (s, b) -> {
                    b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST);
                },
                true
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected UriBuilder set(final UriBuilder builder) {
        return super.set(
                builder.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST)
        );
    }
}
