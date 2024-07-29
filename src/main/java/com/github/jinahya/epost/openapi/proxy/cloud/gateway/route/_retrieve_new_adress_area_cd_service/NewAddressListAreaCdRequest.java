package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_new_adress_area_cd_service;

import com.fasterxml.jackson.annotation.JsonValue;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common._Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
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
    @XmlEnum(String.class)
    public enum SearchSe {

        /**
         * 동(읍/면)명.
         */
        @XmlEnumValue("dong")
        DONG("dong"),

        /**
         * 도로명[default].
         */
        @XmlEnumValue("road")
        ROAD,

        /**
         * 우편번호
         */
        @XmlEnumValue("post")
        POST;

        // -------------------------------------------------------------------------------------------------------------
        public static SearchSe valueOfText(final String text) {
            for (final SearchSe e : SearchSe.values()) {
                if (e.text().equals(text)) {
                    return e;
                }
            }
            throw new IllegalArgumentException("no value for '" + text + "'");
        }

        // -------------------------------------------------------------------------------------------------------------
        SearchSe(final String text) {
            this.text = text;
        }

        // -------------------------------------------------------------------------------------------------------------
        SearchSe() {
            this(null);
        }

        // -------------------------------------------------------------------------------------------------------------
        @JsonValue
        public String text() {
            if (text != null) {
                return text;
            }
            return name().toLowerCase();
        }

        // -------------------------------------------------------------------------------------------------------------
        private final String text;
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
