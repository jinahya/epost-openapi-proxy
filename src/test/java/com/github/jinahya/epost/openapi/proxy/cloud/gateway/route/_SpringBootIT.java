package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common._Constants;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.SynchronousSink;

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
