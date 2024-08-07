package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._SpringBootIT;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestTypeTestUtils;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractResponseType;
import com.github.jinahya.epost.openapi.proxy.web.readtive.funcion.client.WebClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@DisplayName("/getAreaCodeInfo")
@Slf4j
class GetAreaCodeInfo_SpringBootIT
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
        return Optional.ofNullable(
                        responseSpec
                                .expectBody(AreaCodeInfoResponse.class)
                                .returnResult()
                                .getResponseBody()
                )
                .map(AbstractResponseType::get)
                .orElseThrow();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<AreaCodeInfoRequest> getRequestStream() {
        return AbstractRequestTypeTestUtils.mapMediaType(
                IntStream.of(1, 2, 3, 4)
                        .mapToObj(AreaCodeInfoRequest::of)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Disabled
    @MethodSource({
            "getRequestStream"
    })
    @ParameterizedTest
    void __(final AreaCodeInfoRequest request) {
        final var response = exchange(webTestClient(), request);
        log.debug("file: {}", response.getFile());
        assertValid(response);
        assertSucceeded(response);
        if (true) { // takes too long!
            final var flags = new HashMap<String, Boolean>();
            WebClientUtils.download(
                    response.getFile(),
                    (n, m) -> {
                        if (flags.compute(n, (k, v) -> v == null)) {
                            log.debug("n: {}, m: {}", n, m);
                        }
                    }
            ).block();
        }
    }

    @Test
    void __() {
        Flux.fromStream(getRequestStream())
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .map(r -> exchange(webTestClient(), r))
                .doOnNext(r -> {
                    log.debug("file: {}", r.getFile());
                    assertValid(r);
                    assertSucceeded(r);
                })
                .flatMap(r -> {
                    final var flags = new HashMap<String, Boolean>();
                    return WebClientUtils.download(
                            r.getFile(),
                            (n, m) -> {
                                if (flags.compute(n, (k, v) -> v == null)) {
                                    log.debug("n: {}, m: {}", n, m);
                                }
                            }
                    );
                })
                .sequential()
                .blockLast();
        ;
    }
}
