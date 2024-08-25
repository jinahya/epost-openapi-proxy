package com.github.jinahya.epost.openapi.proxy.web.bind;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.io.Serial;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class AbstractModel<SELF extends AbstractModel<SELF>>
        extends RepresentationModel<SELF>
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1775734949340833035L;

    // -----------------------------------------------------------------------------------------------------------------
    protected static String baseUrl(final ServerHttpRequest request) {
        Objects.requireNonNull(request, "request is null");
        try {
            final var url = request.getURI().toURL();
            return url.getProtocol() + "://" + url.getAuthority();
        } catch (final MalformedURLException murle) {
            throw new RuntimeException("failed to get url from " + request, murle);
        }
    }

    // ----------------------------------------------------------------------------------------------------- super.links
}
