package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_lot_number_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.io.Serial;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewAddressListAreaCdSearchAllRequest
        extends AbstractPairedRequestType<NewAddressListAreaCdSearchAllRequest, NewAddressListAreaCdSearchAllResponse> {

    @Serial
    private static final long serialVersionUID = -9014063080411511379L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public NewAddressListAreaCdSearchAllRequest of(@Nullable final String serviceKey, final String srchwrd,
                                                   final Integer countPerPage, final Integer currentPage) {
        final var instance = of(NewAddressListAreaCdSearchAllRequest::new, serviceKey);
        instance.setSrchwrd(srchwrd);
        instance.setCountPerPage(countPerPage);
        instance.setCurrentPage(currentPage);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public NewAddressListAreaCdSearchAllRequest() {
        super(NewAddressListAreaCdSearchAllResponse.class);
    }

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
