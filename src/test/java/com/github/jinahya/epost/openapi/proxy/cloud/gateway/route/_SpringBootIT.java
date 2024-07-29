package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestType;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    protected static final String SYSTEM_PROPERTY_SERVICE_KEY = "serviceKey";

    // -----------------------------------------------------------------------------------------------------------------
    @PostConstruct
    private void doOnPostConstruct() {
        log.debug("serviceKey: {}", serviceKey);
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
    protected void assertValid(final Object object) {
        Objects.requireNonNull(object, "object is null");
        assertThat(validator().validate(object))
                .isEmpty();
    }

    // ----------------------------------------------------------------------------------------------------- serviceKey
    protected <T extends AbstractRequestType<T>> T serviceKey(final T request) {
        return request.serviceKey(serviceKey);
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

    @Value("#{systemProperties['" + SYSTEM_PROPERTY_SERVICE_KEY + "']}")
    @Accessors(fluent = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private String serviceKey;
}
