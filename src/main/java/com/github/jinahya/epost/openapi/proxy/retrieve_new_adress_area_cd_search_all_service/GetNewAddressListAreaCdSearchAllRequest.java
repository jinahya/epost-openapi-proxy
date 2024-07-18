package com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GetNewAddressListAreaCdSearchAllRequest
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -7468923602062739458L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String QUERY_PARAM_NAME_SERVICE_KEY = "serviceKey";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String QUERY_PARAM_NAME_SRCHWRD = "srchwrd";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String QUERY_PARAM_NAME_COUNT_PER_PAGE = "countPerPage";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String QUERY_PARAM_NAME_CURRENT_PAGE = "currentPage";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String serviceKey;

    // -----------------------------------------------------------------------------------------------------------------
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
