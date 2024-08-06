package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.web.readtive.funcion.client.WebClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/getAreaCodeInfo")
@Slf4j
class getAreaCodeInfo_SpringBootIT
        extends _SpringBootIT {

    // -----------------------------------------------------------------------------------------------------------------
    static AreaCodeInfoResponse exchange(final WebTestClient client, final AreaCodeInfoRequest request) {
        Objects.requireNonNull(client, "client is null");
        Objects.requireNonNull(request, "request is null");
        final var requestSpec = client
                .method(request.getHttpMethod())
                .uri(request::acceptUriConsumerAndBuild)
                .headers(request::acceptHeaders);
        // -------------------------------------------------------------------------------------------------------- when
        final var responseSpec = requestSpec.exchange();
        // -------------------------------------------------------------------------------------------------------- then
        responseSpec.expectStatus().isOk();
        final var responseBody = Optional.ofNullable(
                        responseSpec
                                .expectBody(AreaCodeInfoResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(AbstractResponseType::get)
                .orElseThrow();
        assertThat(responseBody.getCmmMsgHeader()).isNotNull().satisfies(h -> {
            assertThat(h.isSucceeded()).isTrue();
            log.debug("responseTime: {}", h.getResponseTime());
            log.debug("responseTimeAsLocalDateTime: {}", h.getResponseTimeAsLocalDateTime());
        });
        return responseBody;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<AreaCodeInfoRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                IntStream.of(1, 2, 3, 4)
                        .mapToObj(AreaCodeInfoRequest::of)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final AreaCodeInfoRequest request) throws IOException {
        final var response = exchange(webTestClient(), request);
        log.debug("file: {}", response.getFile());
        assertValid(response);
        if (false) { // takes too long!
            WebClientUtils.download(response.getFile(), Duration.ofSeconds(10L), p -> {
                try {
                    log.debug("destination.size: {}", Files.size(p));
                    try (final var stream = new FileInputStream(p.toFile())) {
                        final var flags = new HashMap<String, Boolean>();
                        AreaCodeInfoUtils.extract(
                                stream,
                                (n, m) -> {
                                    if (flags.compute(n, (k, v) -> v == null)) {
                                        log.debug("n: {}, m: {}", n, m);
                                    }
                                });
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).block();
        }
        final var destination = Files.createTempFile(null, null);
        if (false) {
            try {
                WebClientUtils.download(response.getFile(), Duration.ofSeconds(10L), destination,
                                        StandardOpenOption.WRITE)
                        .block();
                log.debug("destination.size: {}", Files.size(destination));
                try (final var stream = new FileInputStream(destination.toFile())) {
                    final var flags = new HashMap<String, Boolean>();
                    AreaCodeInfoUtils.extract(
                            stream,
                            (n, m) -> {
                                if (flags.compute(n, (k, v) -> v == null)) {
                                    log.debug("n: {}, m: {}", n, m);
                                }
                            });
                }
            } finally {
                Files.delete(destination);
            }
        }
    }
}
