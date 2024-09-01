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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

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
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@SuppressWarnings({
        "java:S119"
})
public abstract class _ApiController_SpringBootTest<CONTROLLER extends _ApiController> {

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
    void dontBother() {
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
                            .as(_ApiController_SpringBootTest.class)
                            .resolveGeneric(0),
                    "failed to resolve controller class"
            );
        }
        return controllerClass;
    }

    // ------------------------------------------------------------------------------------------------------- validator
    protected <T> T assertValid(final T object) {
        Objects.requireNonNull(object, "object is null");
        assertThat(validator().validate(object))
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

    // ---------------------------------------------------------------------------------------------- controllerInstance
    protected void acceptControllerInstance(final Consumer<? super CONTROLLER> consumer) {
        consumer.accept(controllerInstance);
    }

    protected void setControllerInstanceWebClient(final WebClient webClient) {
        controllerInstance.webClient = webClient;
    }

    protected void mutateControllerInstanceWebClient(final UnaryOperator<WebClient> operator) {
        controllerInstance.mutateWebClient(operator);
    }

    protected void mutateControllerInstanceWebClientWith(final ExchangeFunction function) {
        mutateControllerInstanceWebClient(wc -> wc.mutate().exchangeFunction(function).build());
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