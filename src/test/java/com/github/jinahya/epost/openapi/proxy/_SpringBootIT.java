package com.github.jinahya.epost.openapi.proxy;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.*;
import com.mycompany.Application;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Validator;
import lombok.AccessLevel;
import lombok.Getter;
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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

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
@Slf4j
public abstract class _SpringBootIT {

    @PostConstruct
    private void doOnPostConstruct() {
        // https://stackoverflow.com/a/48655749/330457
        // https://stackoverflow.com/a/49496309/330457
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofSeconds(16L))
                .build()
        ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void dontBother() {
        // https://youtrack.jetbrains.com/issue/IDEA-357194/Abstract-test-class-with-no-test-method-doesnt-run
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected <T extends AbstractResponseType<T>> void handle(final T value, final SynchronousSink<T> sink) {
        Objects.requireNonNull(value, "value is null");
        Objects.requireNonNull(sink, "sink is null");
        final var cmmMsgHandler = value.getCmmMsgHeader();
        if (cmmMsgHandler.isSucceeded()) {
            sink.next(value);
        } else {
            sink.error(new RuntimeException("unsuccessful response: " + value));
        }
    }

    // --------------------------------------------------------------------------------------------------- webTestClient
    protected <T extends AbstractRequestType<T>, U extends AbstractResponseType<U>> U exchange(
            final T request, final Class<U> responseClass) {
        Objects.requireNonNull(request, "request is null");
        Objects.requireNonNull(responseClass, "responseClass is null");
        return webTestClient()
                .method(request.getHttpMethod())
                .uri(request::acceptUriConsumerAndBuild)
                .headers(request::acceptHeaders)
                .exchange()
                .expectStatus().isOk()
                .expectBody(responseClass)
                .returnResult()
                .getResponseBody()
                .get();
    }

    protected <T extends AbstractPairedRequestType<T, U>, U extends AbstractPairedResponseType<U, T>> U exchange(
            final Class<T> requestClass, final T request) {
        Objects.requireNonNull(request, "request is null");
        @SuppressWarnings({"unchecked"})
        final var responseClass = (Class<U>)
                ResolvableType.forType(requestClass)
                        .as(AbstractPairedRequestType.class)
                        .getGeneric(1)
                        .resolve();
        return exchange(request, responseClass);
    }

    private <T extends AbstractPairedRequestType<T, U>, U extends AbstractPairedResponseType<U, T>> U exchangeHelp(
            final Class<T> requestClass, final Object request) {
        Objects.requireNonNull(requestClass, "requestClass is null");
        Objects.requireNonNull(request, "request is null");
        return exchange(requestClass, requestClass.cast(request));
    }

    @SuppressWarnings({"unchecked"})
    protected <T extends AbstractPairedRequestType<T, U>, U extends AbstractPairedResponseType<U, T>> U exchange(
            final T request) {
        Objects.requireNonNull(request, "request is null");
        return (U) exchangeHelp((Class<T>) request.getClass(), request);
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

    // -----------------------------------------------------------------------------------------------------------------
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

    // -----------------------------------------------------------------------------------------------------------------
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private final WebClient webClient = WebClient.builder().baseUrl(_Constants.BASE_URL_DEVELOPMENT).build();
}
