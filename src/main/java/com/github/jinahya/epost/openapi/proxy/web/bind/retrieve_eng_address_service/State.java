package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Slf4j
public class State
        extends AbstractWrappingModel<State, StateEngListResponse.StateEngList> {

    @Serial
    private static final long serialVersionUID = -8409236918457140462L;

    // -----------------------------------------------------------------------------------------------------------------
//    static String getHref(final String stateName) {
//        return __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES + '/' + stateName;
//    }

    static String getHref(final String baseUrl, final String stateName) {
        return baseUrl + __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES + '/' + stateName;
    }

    static String getHref(final ServerHttpRequest request, final String stateName) {
        return baseUrl(request) + __RetrieveEngAddressServiceApiConstants.REQUEST_URI_STATES + '/' + stateName;
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
//    public State addLinks() {
//        add(
//                Link.of(getHref(wrapped.getStateEngName()))
//                        .withRel(IanaLinkRelations.SELF)
//        );
//        add(
//                Link.of(getHref(wrapped.getStateEngName()) + '/' + __RetrieveEngAddressServiceApiConstants.REL_CITIES)
//                        .withRel(__RetrieveEngAddressServiceApiConstants.REL_CITIES)
//        );
//        return this;
//    }

    public State addLinks(final ServerHttpRequest request) {
        final var baseUrl = baseUrl(request);
        final var stateEngName = wrapped.getStateEngName();
        add(
                Link.of(getHref(baseUrl, stateEngName))
                        .withSelfRel()
        );
        add(
                Link.of(getHref(baseUrl, stateEngName) + '/' + __RetrieveEngAddressServiceApiConstants.REL_CITIES)
                        .withRel(__RetrieveEngAddressServiceApiConstants.REL_CITIES)
        );
        return this;
    }

    public State addLinks(final String requestBaseUrl) {
        final var stateEngName = wrapped.getStateEngName();
        add(
                Link.of(getHref(requestBaseUrl, stateEngName))
                        .withSelfRel()
        );
        add(
                Link.of(getHref(requestBaseUrl, stateEngName)
                                + '/' + __RetrieveEngAddressServiceApiConstants.REL_CITIES)
                        .withRel(__RetrieveEngAddressServiceApiConstants.REL_CITIES)
        );
        return this;
    }

    // --------------------------------------------------------------------------------------------------- super.wrapped
    @JsonIgnore
    String name() {
        return Objects.requireNonNull(getWrapped(), "getWrapped() is null").getStateEngName();
    }
}
