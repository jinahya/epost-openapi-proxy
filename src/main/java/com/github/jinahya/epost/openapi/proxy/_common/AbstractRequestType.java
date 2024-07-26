package com.github.jinahya.epost.openapi.proxy._common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.io.Serial;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
// TODO: Specify response type parameter
public abstract class AbstractRequestType
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -5536339965586422829L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -----------------------------------------------------------------------------------------------------------------

    protected UriBuilder set(final UriBuilder builder) {
        Objects.requireNonNull(builder, "builder is null");
        return builder
                .queryParamIfPresent(_Constants.PARAM_SERVICE_KEY, Optional.ofNullable(serviceKey))
                ;
    }

    protected <T extends AbstractResponseType> Mono<T> exchange(final WebClient webClient, final HttpMethod httpMethod,
                                                                final Function<UriBuilder, URI> uriFunction,
                                                                Class<T> responseType) {
        Objects.requireNonNull(webClient, "webClient is null");
        Objects.requireNonNull(httpMethod, "httpMethod is null");
        Objects.requireNonNull(responseType, "responseType is null");
        return webClient
                .method(httpMethod)
                .uri(uriFunction)
                .exchangeToMono(r -> r.bodyToMono(responseType));
    }

    protected <T extends AbstractResponseType> Mono<T> exchange(final WebClient webClient, final HttpMethod httpMethod,
                                                                Class<T> responseType) {
        Objects.requireNonNull(webClient, "webClient is null");
        Objects.requireNonNull(httpMethod, "httpMethod is null");
        Objects.requireNonNull(responseType, "responseType is null");
        return exchange(webClient, httpMethod, b -> set(b).build(), responseType);
    }

    protected <T extends AbstractResponseType> Mono<T> get(final WebClient webClient, final Class<T> responseType) {
        return exchange(webClient, HttpMethod.GET, responseType);
    }

    // ------------------------------------------------------------------------------------------------------ serviceKey

    // ---------------------------------------------------------------------------------------------------------- accept

    // -----------------------------------------------------------------------------------------------------------------
    @EqualsAndHashCode.Exclude
    private String serviceKey;

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private HttpMethod httpMethod;

    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private Function<UriBuilder, URI> uriFunction;

    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private MediaType accept;

    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private String cacheControl;
}
