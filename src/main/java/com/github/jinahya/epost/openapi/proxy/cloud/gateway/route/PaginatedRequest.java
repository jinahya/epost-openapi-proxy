package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface PaginatedRequest<T extends AbstractRequestType<T> & PaginatedRequest<T>> {

    int MIN_CURRENT_PAGE = 1;

    // ---------------------------------------------------------------------------------------------------- countPerPage
    @Positive
    @NotNull
    default Integer getCountPerPage() {
        return PaginatedRequestHelper.getCountPerPage(this);
    }

    default void setCountPerPage(final Integer countPerPage) {
        PaginatedRequestHelper.setCountPerPage(this, countPerPage);
    }

    @SuppressWarnings({"unchecked"})
    default T countPerPage(final Integer countPerPage) {
        setCountPerPage(countPerPage);
        return (T) this;
    }

    // ----------------------------------------------------------------------------------------------------- currentPage
    @Positive
    @NotNull
    default Integer getCurrentPage() {
        return PaginatedRequestHelper.getCurrentPage(this);
    }

    default void setCurrentPage(final Integer currentPage) {
        PaginatedRequestHelper.setCurrentPage(this, currentPage);
    }

    @SuppressWarnings({"unchecked"})
    default T currentPage(final Integer currentPage) {
        setCurrentPage(currentPage);
        return (T) this;
    }

    default T forNextPage() {
        return currentPage(getCurrentPage() + 1);
    }
}
