package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractTypeWrappingModel;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Slf4j
public class State
        extends AbstractTypeWrappingModel<State, StateEngListResponse.StateEngList> {

    @Serial
    private static final long serialVersionUID = -8409236918457140462L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static State newInstance(final StateEngListResponse.StateEngList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new State();
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonIgnore
    String name() {
        return Objects.requireNonNull(getWrapped(), "getWrapped() is null").getStateEngName();
    }
}
