package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind._ApiController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
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
//    private Flux<NewAddressListAreaCdSearchAllResponse> exchange(final String srchwrd) {
//        return Mono.just(NewAddressListAreaCdSearchAllRequest.of(
//                        null,
//                        srchwrd,
//                        32,
//                        1
//                ))
//                .expand(r -> Mono.just(r.forNextPage()))
//                .flatMapSequential(
//                        r -> r.exchange(webClient()),
//                        5,
//                        1
//                );
//    }
//
//    private Flux<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll> publisher(
//            final NewAddressListAreaCdSearchAllRequest request) {
//        return exchange(request)
//                .flatMapMany(r -> Flux.fromIterable(r.getNewAddressListAreaCdSearchAll()));
//    }
//
    private Iterable<Link> links(final NewAddressListAreaCdSearchAllRequest request,
                                 final NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll element) {
        return List.of(

        );
    }

    private RepresentationModel<EntityModel<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll>> model(
            final NewAddressListAreaCdSearchAllRequest request,
            final NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll element) {
        return HalModelBuilder
                .halModelOf(element)
                .links(links(request, element))
                .build();
    }

    @GetMapping(
            path = __RetrieveNewAdressAreCdSearchAllServiceApiConstants.REQUEST_URI_SEARCH,
            produces = {
                    MediaType.APPLICATION_NDJSON_VALUE
            }
    )
    Flux<RepresentationModel<EntityModel<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll>>> search(
            final ServerWebExchange exchange,
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
                .flatMap(r -> Flux.fromIterable(r.getNewAddressListAreaCdSearchAll()))
                .<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll>handle((e, s) -> {
                    count.increment();
                    final var t = total.get();
                    final var c = count.sum();
                    if (t == null || c <= t) {
                        s.next(e);
                    } else {
                        s.complete();
                    }
                })
                .map(e -> model(null, e));
    }
}
