package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseType;
import com.mycompany.Application;
import jakarta.annotation.Nullable;
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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.HypermediaWebTestClientConfigurer;
import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract test class for testing subclasses of {@link __ApiController} class.
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
@SuppressWarnings({
        "java:S119" // <CONTROLLER ...>
})
public abstract class __ApiController_SpringBootIT<CONTROLLER extends __ApiController> {

    // -----------------------------------------------------------------------------------------------------------------
    protected static <T> T getRandom(final List<T> list) {
        Collections.shuffle(list);
        return list.getFirst();
    }

    @SuppressWarnings({
            "java:S6204" // STREAM#collect(Collectors.toList()) <> Stream#toList()
    })
    protected static <T> T getRandomContent(final List<EntityModel<T>> list) {
        return getRandom(list.stream().map(EntityModel::getContent).collect(Collectors.toList()));
    }

    // -----------------------------------------------------------------------------------------------------------------

    // 왜 안되는지 잘 모르겠다.
    // https://stackoverflow.com/q/78942661/330457
    // https://github.com/spring-projects/spring-hateoas/issues/2211
    protected static <T> List<EntityModel<T>> readList(final Class<T> contentType, final WebTestClient client,
                                                       final Function<UriBuilder, URI> uriFunction,
                                                       @Nullable final String accept,
                                                       final TypeReferences.EntityModelType<T> modelType) {
        final var responseBody = client
                .get()
                .uri(uriFunction)
                .headers(h -> {
                    Optional.ofNullable(accept)
                            .map(MediaType::valueOf)
                            .map(List::of)
                            .ifPresent(h::setAccept);
                })
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(modelType)
                .returnResult()
                .getResponseBody();
        return Objects.requireNonNull(responseBody, "responseBody is null");
    }

    protected static <T> EntityModel<T> readSingle(final Class<T> contentType, final WebTestClient client,
                                                   final Function<UriBuilder, URI> uriFunction,
                                                   @Nullable final String accept,
                                                   final TypeReferences.EntityModelType<T> modelType) {
        final var responseBody = client
                .get()
                .uri(uriFunction)
                .headers(h -> {
                    Optional.ofNullable(accept)
                            .map(MediaType::valueOf)
                            .map(List::of)
                            .ifPresent(h::setAccept);
                })
                .exchange()
                .expectStatus().isOk()
                .expectBody(modelType)
                .returnResult()
                .getResponseBody();
        return Objects.requireNonNull(responseBody, "responseBody is null");
    }

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
                            .as(__ApiController_SpringBootIT.class)
                            .resolveGeneric(0),
                    "failed to resolve controller class"
            );
        }
        return controllerClass;
    }

    // --------------------------------------------------------------------------------------------------- webTestClient
    // https://stackoverflow.com/q/78942661/330457
    protected <T> List<EntityModel<T>> readList(final Class<T> contentType, final Function<UriBuilder, URI> uriFunction,
                                                @Nullable final String acceptValue,
                                                final TypeReferences.EntityModelType<T> modelType) {
        return readList(
                contentType,
                webTestClient(),
                uriFunction,
                acceptValue,
                modelType
        );
    }

    protected <T> EntityModel<T> readSingle(final Class<T> contentType, final Function<UriBuilder, URI> uriFunction,
                                            @Nullable final String acceptValue,
                                            final TypeReferences.EntityModelType<T> modelType) {
        return readSingle(
                contentType,
                webTestClient(),
                uriFunction,
                acceptValue,
                modelType
        );
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
