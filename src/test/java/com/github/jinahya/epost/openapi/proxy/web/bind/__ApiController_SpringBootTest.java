package com.github.jinahya.epost.openapi.proxy.web.bind;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseType;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract test class for testing subclasses of {@link __ApiController} class.
 *
 * @param <CONTROLLER> subclass type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Import(value = {
        ValidationAutoConfiguration.class
})
@SpringBootTest
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@SuppressWarnings({
        "java:S119"
})
public abstract class __ApiController_SpringBootTest<CONTROLLER extends __ApiController> {

    static final String RESOURCE_PREFIX_ROUTE = "/com/github/jinahya/epost/openapi/proxy/cloud/gateway/route";

    protected static Flux<DataBuffer> resourceDataPublisher(final String name) {
        final var resource = __ApiController_SpringBootTest.class.getResourceAsStream(name);
        assertThat(resource)
                .as("resource for '" + name + "'")
                .isNotNull();
        return DataBufferUtils.read(
                        new InputStreamResource(resource),
                        DefaultDataBufferFactory.sharedInstance,
                        1024
                )
                .doFinally(s -> {
                    try {
                        resource.close();
                    } catch (final IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                });
    }

    /**
     * Loads the resource of specified name under the {@value #RESOURCE_PREFIX_ROUTE} path.
     *
     * @param name the sub-resource name. e.g. {@code /some/other}.
     * @return a data flux of specified resource.
     */
    protected static Flux<DataBuffer> routeResourceDataPublisher(final String name) {
        return resourceDataPublisher(RESOURCE_PREFIX_ROUTE + name);
    }

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

    @SuppressWarnings({
            "java:S6204" // STREAM#collect(Collectors.toList()) <> Stream#toList()
    })
    protected static <T> T getRandomContent(final Flux<EntityModel<T>> list) {
        return list.collectList().map(l -> getRandomContent(l)).block();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @PostConstruct
    private void doOnPostConstruct() {
        // https://stackoverflow.com/a/48655749/330457
        // https://stackoverflow.com/a/49496309/330457
//        webTestClient = webTestClient.mutate()
//                .responseTimeout(Duration.ofSeconds(16L))
//                .build()
//                .mutateWith(hypermediaWebTestClientConfigurer)
//        ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    final void doNotBother() {
        // https://youtrack.jetbrains.com/issue/IDEA-357194/Abstract-test-class-with-no-test-method-doesnt-run
        log.debug("controllerInstance: {}", controllerInstance);
    }

    // ------------------------------------------------------------------------------------------------- controllerClass

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
                            .as(__ApiController_SpringBootTest.class)
                            .resolveGeneric(0),
                    "failed to resolve controller class"
            );
        }
        return controllerClass;
    }

//    protected Flux<DataBuffer> resourceDataPublisher(final String name) {
//        final var resource = controllerClass().getResourceAsStream(name);
//        assertThat(resource)
//                .as("resource for '" + name + "'")
//                .isNotNull();
//        return DataBufferUtils.read(
//                        new InputStreamResource(resource),
//                        DefaultDataBufferFactory.sharedInstance,
//                        1024
//                )
//                .doFinally(s -> {
//                    try {
//                        resource.close();
//                    } catch (final IOException ioe) {
//                        throw new RuntimeException(ioe);
//                    }
//                });
//    }
//
//    /**
//     * Loads the resource of specified name under the {@value #RESOURCE_PREFIX_ROUTE} path.
//     *
//     * @param name the sub-resource name. e.g. {@code /some/other}.
//     * @return a data flux of specified resource.
//     */
//    protected Flux<DataBuffer> routeResourceDataPublisher(final String name) {
//        return resourceDataPublisher(RESOURCE_PREFIX_ROUTE + name);
//    }

    // ------------------------------------------------------------------------------------------------------- validator
    protected <T> T assertValid(final T object) {
        Objects.requireNonNull(object, "object is null");
        assertThat(validator().validate(object))
                .as("constraint violations of %1$s", object)
                .isEmpty();
        return object;
    }

    protected <T extends AbstractResponseType<T>> T assertSucceeded(final T response) {
        assertThat(response.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            assertThat(h.isReturnCode00()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        return response;
    }

    protected <T> void validate(final T content) {
        Objects.requireNonNull(content, "content is null");
        assertValid(content);
    }

    protected <T> void validate(final EntityModel<T> model) {
        Objects.requireNonNull(model, "model is null");
        validate(Objects.requireNonNull(model.getContent(), "model.content is null"));
    }

    // ---------------------------------------------------------------------------------------------- controllerInstance

    /**
     * {@link __ApiController#mutateWebClient(UnaryOperator) mutates} {@link #controllerInstance() controllerInstance}'s
     * {@code webClient} with specified operator.
     *
     * @param operator the operator.
     */
    protected final void mutateControllerInstanceWebClient(final UnaryOperator<WebClient> operator) {
        Objects.requireNonNull(operator, "operator is null");
        controllerInstance.mutateWebClient(operator);
    }

    /**
     * {@link #mutateControllerInstanceWebClient(UnaryOperator) Mutates}
     * {@link #controllerInstance() controllerInstance}'s {@code webClient} with specified exchange function.
     *
     * @param function the exchange function.
     */
    protected final void mutateControllerInstanceWebClientWith(final ExchangeFunction function) {
        Objects.requireNonNull(function, "function is null");
        mutateControllerInstanceWebClient(
                wc -> wc.mutate().exchangeFunction(function).build()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Class<CONTROLLER> controllerClass;

    @Autowired
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private Validator validator;

    // -----------------------------------------------------------------------------------------------------------------
    @Autowired
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private CONTROLLER controllerInstance;
}
