package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllRequest;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Tag(name = __RetrieveNewAdressAreCdSearchAllServiceApiConstants.TAG)
@Validated
@RestController
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class _RetrieveNewAdressAreaCdSearchAllServiceApiController
        extends _ApiController {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -------------------------------------------------------------------------------------------------- /.../addresses
    @GetMapping(
            path = __RetrieveNewAdressAreCdSearchAllServiceApiConstants.REQUEST_URI_SEARCH,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<Address> search(
            final ServerWebExchange exchange,
            @NotBlank
            @RequestParam(__RetrieveNewAdressAreCdSearchAllServiceApiConstants.REQUEST_PARAM_SRCHWRD)
            final String srchwrd) {
        final var total = new AtomicReference<Integer>();
        final var count = new LongAdder();
        return Mono.just(NewAddressListAreaCdSearchAllRequest.of(
                        null,
                        srchwrd,
                        32,
                        1
                ))
                .expand(r -> Mono.just(r.forNextPage()))
                .flatMapSequential(
                        r -> r.exchange(webClient()),
                        5,
                        1
                )
                .switchOnFirst((s, f) -> {
                    if (s.hasValue()) {
                        final var cmmMsgHeader = s.get().getCmmMsgHeader();
                        total.compareAndSet(null, cmmMsgHeader.getTotalCount());
                    }
                    return f;
                })
                .flatMap(r -> {
                    final var list = r.getNewAddressListAreaCdSearchAll();
                    return Flux.fromIterable(list)
                            .map(Address::newInstance);
                })
                .<Address>handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                });
    }
}
