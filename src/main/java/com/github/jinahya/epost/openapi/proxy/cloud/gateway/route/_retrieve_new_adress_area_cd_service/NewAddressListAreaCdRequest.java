package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_new_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy._common._Constants;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NewAddressListAreaCdRequest
        extends AbstractRequestType<NewAddressListAreaCdRequest> {

    @Serial
    private static final long serialVersionUID = -4766029866023904965L;

    // -----------------------------------------------------------------------------------------------------------------
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

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static NewAddressListAreaCdRequest of(final String serviceKey, final SearchSe searchSe, final String srchwrd,
                                                 final Integer countPerPage, final Integer currentPage) {
        final var instance = AbstractRequestType.of(NewAddressListAreaCdRequest::new, serviceKey);
        instance.setSearchSe(searchSe);
        instance.setSrchwrd(srchwrd);
        instance.setCountPerPage(countPerPage);
        instance.setCurrentPage(currentPage);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super NewAddressListAreaCdRequest, ? super UriBuilder> URI_CONSUMER = (s, b) -> {
        b.path(_RetrieveNewAdressAreaCdServiceConstants.REQUEST_URI_GET_NEW_ADDRESS_LIST_AREA_CD)
                .queryParam(_RetrieveNewAdressAreaCdServiceConstants.PARAM_SEARCH_SE, s.getSearchSe())
                .queryParam(_RetrieveNewAdressAreaCdServiceConstants.PARAM_SRCHWRD, s.getSrchwrd())
                .queryParam(_Constants.PARAM_COUNT_PER_PAGE, s.getCountPerPage())
                .queryParam(_Constants.PARAM_CURRENT_PAGE, s.getCurrentPage())
        ;
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public NewAddressListAreaCdRequest() {
        super();
        setUriConsumer(
                URI_CONSUMER,
                true
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    private SearchSe searchSe;

    @NotBlank
    private String srchwrd;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    private Integer countPerPage;

    @Positive
    @NotNull
    private Integer currentPage;
}
