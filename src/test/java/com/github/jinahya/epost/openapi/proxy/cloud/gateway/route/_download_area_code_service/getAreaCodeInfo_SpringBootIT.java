package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
        if (false) { // takes too long, baby...
            final Path path = Files.createTempFile(null, null);
            path.toFile().deleteOnExit();
            final var body = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true)))
                    .build()
                    .get()
                    .uri(URI.create(response.getFile()))
                    .retrieve()
                    .bodyToFlux(DataBuffer.class);
            DataBufferUtils
                    .write(body, path, StandardOpenOption.WRITE)
                    .block();
        }
    }
}
