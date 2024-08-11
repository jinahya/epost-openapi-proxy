package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuppressWarnings({
        "java:S119" // <SELF ...>
})
public abstract class AbstractPairedRequestType<
        SELF extends AbstractPairedRequestType<SELF, RESPONSE>,
        RESPONSE extends AbstractPairedResponseType<RESPONSE, SELF>>
        extends AbstractRequestType<SELF> {

    @Serial
    private static final long serialVersionUID = -6547179817137904758L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected AbstractPairedRequestType(final Class<RESPONSE> responseClass) {
        super();
        this.responseClass = Objects.requireNonNull(responseClass, "responseClass is null");
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    public Mono<RESPONSE> exchange(final WebClient webClient) {
        return exchange(webClient, responseClass)
                .map(v -> v.requestInstance((SELF) this));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @XmlTransient
    @EqualsAndHashCode.Exclude
    private final Class<RESPONSE> responseClass;
}
