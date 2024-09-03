package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public final class PageInfo { // -data 꺼 쓸까?

    // -----------------------------------------------------------------------------------------------------------------
    @Max(PaginatedRequest.MAX_COUNT_PER_PAGE)
    @Positive
    @NotNull
    public final int countPerPage;

    @Positive
    public final int currentPage;
}
