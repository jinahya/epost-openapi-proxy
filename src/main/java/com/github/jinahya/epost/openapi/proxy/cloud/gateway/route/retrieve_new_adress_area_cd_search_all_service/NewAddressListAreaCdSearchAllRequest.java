package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common._Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.function.BiConsumer;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewAddressListAreaCdSearchAllRequest
        extends AbstractRequestType<NewAddressListAreaCdSearchAllRequest> {

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
    public static NewAddressListAreaCdSearchAllRequest of(final String serviceKey, final String srchwrd,
                                                          final Integer countPerPage, final Integer currentPage) {
        final var instance = of(NewAddressListAreaCdSearchAllRequest::new, serviceKey);
        instance.setSrchwrd(srchwrd);
        instance.setCountPerPage(countPerPage);
        instance.setCurrentPage(currentPage);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super NewAddressListAreaCdSearchAllRequest, ? super UriBuilder> URI_CONSUMER =
            (s, b) -> {
                b.path(_RetrieveNewAddressAreaCdSearchAllServiceConstants.
                               REQUEST_URI_GET_NEW_ADDRESS_LIST_AREA_CD_SEARCH_ALL)
                        .queryParam(_RetrieveNewAddressAreaCdSearchAllServiceConstants.REQUEST_PARAM_SRCHWRD, s.getSrchwrd())
                        .queryParam(_Constants.REQUEST_PARAM_COUNT_PER_PAGE, s.getCountPerPage())
                        .queryParam(_Constants.REQUEST_PARAM_CURRENT_PAGE, s.getCurrentPage())
                ;
            };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public NewAddressListAreaCdSearchAllRequest() {
        super();
        setUriConsumer(
                URI_CONSUMER,
                true
        );
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
