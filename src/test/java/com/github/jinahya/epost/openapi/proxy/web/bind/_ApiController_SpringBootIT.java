package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseType;
import com.mycompany.Application;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Validator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ResolvableType;
import org.springframework.hateoas.config.HypermediaWebTestClientConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract test class for testing subclasses of {@link _ApiController} class.
 *
 * @param <CONTROLLER> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Import(
        value = {
                ValidationAutoConfiguration.class
        }
)
@ContextConfiguration(
        classes = {
                Application.class
        }
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class _ApiController_SpringBootIT<CONTROLLER extends _ApiController> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @PostConstruct
    private void doOnPostConstruct() {
        // https://stackoverflow.com/a/48655749/330457
        // https://stackoverflow.com/a/49496309/330457
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofSeconds(16L))
                .build()
                .mutateWith(hypermediaWebTestClientConfigurer)
        ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    final void doNotBother() {
        // https://youtrack.jetbrains.com/issue/IDEA-357194/Abstract-test-class-with-no-test-method-doesnt-run
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the actual type of {@link CONTROLLER}.
     *
     * @return the actual type of {@link CONTROLLER}
     */
    @SuppressWarnings({"unchecked"})
    protected final Class<CONTROLLER> controllerClass() {
        if (controllerClass == null) {
            controllerClass = Objects.requireNonNull(
                    (Class<CONTROLLER>) ResolvableType.forType(getClass())
                            .as(_ApiController_SpringBootIT.class)
                            .resolveGeneric(0),
                    "failed to resolve controller class"
            );
        }
        return controllerClass;
    }

    // ------------------------------------------------------------------------------------------------------- validator
    protected final <T> T assertValid(final T object) {
        Objects.requireNonNull(object, "object is null");
        assertThat(validator().validate(object))
                .as("constraint violations of %1$s", object)
                .isEmpty();
        return object;
    }

    protected final <T extends AbstractResponseType<T>> T assertSucceeded(final T response) {
        assertThat(response.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            assertThat(h.isReturnCode00()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        return response;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Class<CONTROLLER> controllerClass;

    @Autowired
    private HypermediaWebTestClientConfigurer hypermediaWebTestClientConfigurer;

    @Autowired
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private WebTestClient webTestClient;

    @Autowired
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private Validator validator;
}
