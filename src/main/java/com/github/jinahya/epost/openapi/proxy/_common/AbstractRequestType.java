package com.github.jinahya.epost.openapi.proxy._common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.io.Serial;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractRequestType<SELF extends AbstractRequestType<SELF>>
        extends AbstractType<SELF> {

    @Serial
    private static final long serialVersionUID = -5536339965586422829L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    protected static <T extends AbstractRequestType<?>> T of(final Supplier<? extends T> initializer,
                                                             final String serviceKey) {
        final var instance = AbstractType.of(initializer);
        instance.setServiceKey(serviceKey);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected AbstractRequestType() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -----------------------------------------------------------------------------------------------------------------
    public <T extends AbstractResponseType<T>> Mono<T> exchange(final WebClient webClient,
                                                                final Class<T> responseType) {
        Objects.requireNonNull(webClient, "webClient is null");
        Objects.requireNonNull(responseType, "responseType is null");
        @SuppressWarnings({"unchecked"})
        final var self = (SELF) this;
        return webClient
                .method(httpMethod)
                .uri(b -> {
                    uriConsumer.accept(self, b);
                    return b.build();
                })
                .headers(headersConsumer)
                .exchangeToMono(r -> r.bodyToMono(responseType));
    }

    protected UriBuilder set(final UriBuilder builder) {
        Objects.requireNonNull(builder, "builder is null");
        return builder
                .queryParamIfPresent(_Constants.PARAM_SERVICE_KEY, Optional.ofNullable(serviceKey))
                ;
    }

    protected <T extends AbstractResponseType<T>> Mono<T> exchange(final WebClient webClient,
                                                                   final HttpMethod httpMethod,
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

    protected <T extends AbstractResponseType<T>> Mono<T> exchange(final WebClient webClient,
                                                                   final HttpMethod httpMethod,
                                                                   final Class<T> responseType) {
        Objects.requireNonNull(webClient, "webClient is null");
        Objects.requireNonNull(httpMethod, "httpMethod is null");
        Objects.requireNonNull(responseType, "responseType is null");
        return exchange(webClient, httpMethod, b -> set(b).build(), responseType);
    }

    protected <T extends AbstractResponseType<T>> Mono<T> get(final WebClient webClient, final Class<T> responseType) {
        return exchange(webClient, HttpMethod.GET, responseType);
    }

    // ------------------------------------------------------------------------------------------------------ serviceKey

    // ------------------------------------------------------------------------------------------------------ httpMethod

    // ----------------------------------------------------------------------------------------------------- uriFunction
    public BiConsumer<? super SELF, ? super UriBuilder> getUriConsumer() {
        return uriConsumer;
    }

    public void setUriConsumer(final BiConsumer<? super SELF, ? super UriBuilder> uriConsumer) {
        this.uriConsumer = Objects.requireNonNull(uriConsumer, "uriConsumer is null");
    }

    protected void setUriConsumer(final BiConsumer<? super SELF, ? super UriBuilder> uriConsumer,
                                  final boolean wrap) {
        Objects.requireNonNull(uriConsumer, "uriConsumer is null");
        if (!wrap) {
            setUriConsumer(uriConsumer);
            return;
        }
        final var wrapper = getUriConsumer();
        this.uriConsumer = (s, b) -> {
            uriConsumer.accept(s, b);
            wrapper.accept(s, b);
        };
    }

    public UriBuilder acceptUriConsumer(final UriBuilder builder) {
        @SuppressWarnings({"unchecked"})
        final var self = (SELF) this;
        getUriConsumer().accept(self, builder);
        return builder;
    }

    public URI acceptUriConsumerAndBuild(final UriBuilder builder) {
        return acceptUriConsumer(builder).build();
    }

    // ------------------------------------------------------------------------------------------------- headersConsumer
    public void acceptHeaders(final HttpHeaders headers) {
        headersConsumer.accept(headers);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String serviceKey;

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private HttpMethod httpMethod = HttpMethod.GET;

    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private BiConsumer<? super SELF, ? super UriBuilder> uriConsumer = (s, b) -> {
        b.queryParamIfPresent(_Constants.PARAM_SERVICE_KEY, Optional.ofNullable(s.getServiceKey())).build();
    };

    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private Consumer<HttpHeaders> headersConsumer = httpHeaders -> {
    };
}
