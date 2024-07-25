package com.github.jinahya.epost.openapi.proxy.download_area_code_service;

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
public class AreaCodeIndoRequest
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = -3775051159492651672L;

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
