package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.PageInfo;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.PaginatedRequest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._RouteConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoadAddressEngSearchListRequest
        extends AbstractPairedRequestType<RoadAddressEngSearchListRequest, RoadAddressEngSearchListResponse>
        implements PaginatedRequest<RoadAddressEngSearchListRequest> {

    @Serial
    private static final long serialVersionUID = 3206249731898344984L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified arguments.
     *
     * @param serviceKey       a value for {@code serviceKey} property.
     * @param stateEngName     a value for {@code sateEngName} property.
     * @param cityEngName      a value for {@code cityEngName} property.
     * @param roadEngFirstName .
     * @param roadEngName      .
     * @param keyword          .
     * @param countPerPage     a value for {@link #getCountPerPage() countPerPage} property.
     * @param currentPage      a value for {@link #getCurrentPage() currentPage} property.
     * @return a new instance.
     */
    public static RoadAddressEngSearchListRequest of(@Nullable final String serviceKey, final String stateEngName,
                                                     final String cityEngName, @Nullable final String roadEngFirstName,
                                                     final String roadEngName, @Nullable final String keyword,
                                                     final Integer countPerPage, final Integer currentPage) {
        final var instance = of(RoadAddressEngSearchListRequest::new, serviceKey);
        instance.setStateEngName(stateEngName);
        instance.setCityEngName(cityEngName);
        instance.setRoadEngFirstName(roadEngFirstName);
        instance.setRoadEngName(roadEngName);
        instance.setKeyword(keyword);
        instance.setCountPerPage(countPerPage);
        instance.setCurrentPage(currentPage);
        return instance;
    }

    public static RoadAddressEngSearchListRequest of(@Nullable final String serviceKey, final String stateEngName,
                                                     final String cityEngName, @Nullable final String roadEngFirstName,
                                                     final String roadEngName, @Nullable final String keyword,
                                                     final PageInfo pageInfo) {
        Objects.requireNonNull(pageInfo, "pageInfo is null");
        return of(
                serviceKey,
                stateEngName,
                cityEngName,
                roadEngFirstName,
                roadEngName,
                keyword,
                pageInfo.getCountPerPage(),
                pageInfo.getCurrentPage()
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super RoadAddressEngSearchListRequest, ? super UriBuilder> URI_BUILDER =
            (s, b) -> {
                b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_ADDRESS_SEARCH)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, s.getCityEngName())
                        .queryParamIfPresent(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_FIRST_NAME,
                                             Optional.ofNullable(s.roadEngFirstName))
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_NAME, s.roadEngName)
                        .queryParamIfPresent(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_FIRST_NAME,
                                             Optional.ofNullable(s.keyword))
                        .queryParam(_RouteConstants.REQUEST_PARAM_COUNT_PER_PAGE, s.countPerPage)
                        .queryParam(_RouteConstants.REQUEST_PARAM_CURRENT_PAGE, s.currentPage);
            };
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public RoadAddressEngSearchListRequest() {
        super(RoadAddressEngSearchListResponse.class);
        setUriConfigurer(
                URI_BUILDER,
                true
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;

    @Size(max = 1)
//    @NotBlank
    private String roadEngFirstName;

    @NotBlank
    private String roadEngName;

    // -----------------------------------------------------------------------------------------------------------------
    private String keyword;

    // -----------------------------------------------------------------------------------------------------------------
    @Positive
    @NotNull
    private Integer countPerPage;

    @Positive
    @NotNull
    private Integer currentPage;
}
