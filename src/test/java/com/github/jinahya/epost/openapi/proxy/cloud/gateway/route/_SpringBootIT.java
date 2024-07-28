package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import com.mycompany.Application;
import jakarta.validation.Validator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

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

//    protected static <REQUEST extends AbstractRequestType<REQUEST>, RESPONSE extends AbstractResponseType<RESPONSE>>
//    StateEngListResponse exchange(final WebTestClient client, final REQUEST request, Class<RESPONSE> responseClass) {
//        Objects.requireNonNull(client, "client is null");
//        Objects.requireNonNull(request, "request is null");
//        final var requestSpec = client
//                .method(request.getHttpMethod())
//                .uri(request::acceptUriConsumerAndBuild)
//                .headers(request::acceptHeaders);
//        // -------------------------------------------------------------------------------------------------------- when
//        final var responseSpec = requestSpec.exchange();
//        // -------------------------------------------------------------------------------------------------------- then
//        responseSpec.expectStatus().isOk();
//        final var responseBody = Optional.ofNullable(
//                        responseSpec
//                                .expectBody(responseClass)
//                                .returnResult()
//                                .getResponseBody()
//                )
//                .orElseThrow();
//        assertThat(responseBody.getCmmMsgHeader()).isNotNull().satisfies(h -> {
//            assertThat(h.isSucceeded()).isTrue();
//            log.debug("responseTime: {}", h.getResponseTime());
//            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
//        });
//        responseBody.getStateEngList().forEach(e -> {
//            log.debug("roadEngFirstName: {}", e);
//        });
//        return responseBody;
//    }

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
}
