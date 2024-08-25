package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.Serial;
import java.util.Objects;

/**
 * An abstract request class paired to a specific type of {@link AbstractPairedResponseType} class.
 *
 * @param <SELF>     self type parameter
 * @param <RESPONSE> the type of paired {@link AbstractPairedResponseType}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see AbstractPairedResponseType
 */
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
