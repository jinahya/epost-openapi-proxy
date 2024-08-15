package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import lombok.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class State
        extends AbstractWrappingModel<State, StateEngListResponse.StateEngList> {

    @Serial
    private static final long serialVersionUID = -8409236918457140462L;

    // -----------------------------------------------------------------------------------------------------------------
    static String getHref(final String stateName) {
        return _RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES + '/' + stateName;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static State instanceOf(final StateEngListResponse.StateEngList wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new State();
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public State addLinks() {
        add(
                Link.of(getHref(wrapped.getStateEngName()))
                        .withRel(IanaLinkRelations.SELF)
        );
        add(
                Link.of(getHref(wrapped.getStateEngName()) + '/' + _RetrieveEngAddressServiceApiConstants.REL_CITIES)
                        .withRel(_RetrieveEngAddressServiceApiConstants.REL_CITIES)
        );
        return this;
    }

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonProperty(required = true)
    public String getName() {
        return Optional.ofNullable(getWrapped())
                .map(StateEngListResponse.StateEngList::getStateEngName)
                .orElse(null);
    }
}
