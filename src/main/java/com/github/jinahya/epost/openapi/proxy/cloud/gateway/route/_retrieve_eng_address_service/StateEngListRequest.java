package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedRequestType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StateEngListRequest
        extends AbstractPairedRequestType<StateEngListRequest, StateEngListResponse> {

    @Serial
    private static final long serialVersionUID = 4471882633229837577L;

    // -------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public StateEngListRequest() {
        super(StateEngListResponse.class);
        setUriConsumer(
                (s, b) -> {
                    b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_STATE_LIST);
                },
                true
        );
    }
}
