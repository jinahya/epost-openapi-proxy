package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class NewAddressListRequest {

    public static final String QUERY_PARAM_NAME_SERVICE_KEY = "serviceKey";

    public static final String QUERY_PARAM_NAME_SERACH_SE = "searchSe";

    public static final String QUERY_PARAM_NAME_SRCHWRD = "srchwrd";

    public static final String QUERY_PARAM_NAME_COUNT_PER_PAGE = "countPerPage";

    public static final String QUERY_PARAM_NAME_CURRENT_PAGE = "currentPage";

    @SuppressWarnings({
            "java:S115" // not 'DONG' but 'dong'
    })
    public enum SearchSe {

        /**
         * 동(읍/면)명.
         */
        dong,

        /**
         * 도로명[default].
         */
        road,

        /**
         * 우편번호
         */
        post
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String serviceKey;

    // -----------------------------------------------------------------------------------------------------------------
    private SearchSe searchSe; // 검색구분

    @NotBlank
    private String srchwrd; // 검색어

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    private Integer countPerPage; // 페이지당 출력 개수

    @Positive
    @NotNull
    private Integer currentPage; // 출력될 페이지 번호
}
