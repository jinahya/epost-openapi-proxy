package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class State
        extends RepresentationModel<State> {

    static String getHref(final State state) {
        return _RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES + '/' + state.getWrapped().getStateEngName();
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    static State of(final StateEngListResponse.StateEngList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new State();
        instance.setWrapped(wrapped);
        return instance;
    }

    static String stateName(final ServerWebExchange exchange) {
        Objects.requireNonNull(exchange, "exchange is null");
        final Map<String, String> variables = exchange.getAttribute(
                ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE
        );
        assert variables != null;
        return variables.get(_RetrieveEngAddressServiceApiConstants.PATH_NAME_STATE_NAME);
    }

    static State from(final ServerWebExchange exchange) {
        final var wrapped = StateEngListResponse.StateEngList.of(stateName(exchange));
        return of(wrapped);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public State addLinks() {
//        add(Link.of(getHref(this)).withRel(IanaLinkRelations.SELF));
        add(Link.of(getHref(this) + '/' + _RetrieveEngAddressServiceApiConstants.REL_CITIES).withRel(
                _RetrieveEngAddressServiceApiConstants.REL_CITIES));
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonUnwrapped
    @NotNull
    private StateEngListResponse.StateEngList wrapped;
}