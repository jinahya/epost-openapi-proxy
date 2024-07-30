package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common;

public interface PaginatedRequest<T extends AbstractRequestType<T> & PaginatedRequest<T>> {

    // ---------------------------------------------------------------------------------------------------- countPerPage
    default Integer getCountPerPage() {
        return PaginatedRequestHelper.getCountPerPage(this);
    }

    default void setCountPerPage(Integer countPerPage) {
        PaginatedRequestHelper.setCountPerPage(this, countPerPage);
    }

    @SuppressWarnings({"unchecked"})
    default T countPerPage(Integer countPerPage) {
        setCountPerPage(countPerPage);
        return (T) this;
    }

    // ----------------------------------------------------------------------------------------------------- currentPage
    default Integer getCurrentPage() {
        return PaginatedRequestHelper.getCurrentPage(this);
    }

    default void setCurrentPage(Integer currentPage) {
        PaginatedRequestHelper.setCurrentPage(this, currentPage);
    }

    @SuppressWarnings({"unchecked"})
    default T currentPage(Integer currentPage) {
        setCurrentPage(currentPage);
        return (T) this;
    }

    default T forNextPage() {
        return currentPage(getCurrentPage() + 1);
    }
}
