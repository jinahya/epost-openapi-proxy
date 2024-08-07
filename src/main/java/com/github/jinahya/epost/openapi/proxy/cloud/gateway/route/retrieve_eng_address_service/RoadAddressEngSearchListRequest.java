package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common._Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;
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
        extends AbstractPairedRequestType<RoadAddressEngSearchListRequest, RoadAddressEngSearchListResponse> {

    @Serial
    private static final long serialVersionUID = 3206249731898344984L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    /**
     * Creates a new instance with specified arguments.
     *
     * @param serviceKey       a value for {@link #getServiceKey() serviceKey} property.
     * @param stateEngName     a value for {@link #getStateEngName() sateEngName} property.
     * @param cityEngName      a value for {@link #getCityEngName() cityEngName} property.
     * @param roadEngFirstName
     * @param roadEngName
     * @param keyword
     * @param countPerPage     a value for {@link #getCountPerPage() countPerPage} property.
     * @param currentPage
     * @return a new instance.
     */
    public static RoadAddressEngSearchListRequest of(final @Nullable String serviceKey, final String stateEngName,
                                                     final String cityEngName, final String roadEngFirstName,
                                                     final String roadEngName, final @Nullable String keyword,
                                                     final Integer countPerPage, final Integer currentPage) {
        final var instance = AbstractRequestType.of(RoadAddressEngSearchListRequest::new, serviceKey);
        instance.setStateEngName(stateEngName);
        instance.setCityEngName(cityEngName);
        instance.setRoadEngFirstName(roadEngFirstName);
        instance.setRoadEngName(roadEngName);
        instance.setKeyword(keyword);
        instance.setCountPerPage(countPerPage);
        instance.setCurrentPage(currentPage);
        return instance;
    }

    public static RoadAddressEngSearchListRequest from(
            final @NonNull RoadEngFirstNameListRequest roadEngFirstNameListRequest,
            final @NonNull RoadEngFirstNameListResponse.RoadEngFirstNameList roadEngFirstNameList,
            final String roadEngName, final @org.springframework.lang.Nullable String keyword,
            final Integer countPerPage, final Integer currentPage) {
        Objects.requireNonNull(roadEngFirstNameListRequest, "roadEngFirstNameListRequest is null");
        Objects.requireNonNull(roadEngFirstNameList, "roadEngFirstNameList is null");
        return of(
                roadEngFirstNameListRequest.getServiceKey(),
                roadEngFirstNameListRequest.getStateEngName(),
                roadEngFirstNameListRequest.getCityEngName(),
                roadEngFirstNameList.getRoadEngFirstName(),
                roadEngName,
                keyword,
                countPerPage,
                currentPage
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super RoadAddressEngSearchListRequest, ? super UriBuilder> URI_CONSUMER =
            (s, b) -> {
                b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_ADDRESS_SEARCH)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, s.getCityEngName())
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_FIRST_NAME, s.roadEngFirstName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_NAME, s.roadEngName)
                        .queryParamIfPresent(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_FIRST_NAME,
                                             Optional.ofNullable(s.keyword))
                        .queryParam(_Constants.REQUEST_PARAM_COUNT_PER_PAGE, s.countPerPage)
                        .queryParam(_Constants.REQUEST_PARAM_CURRENT_PAGE, s.currentPage);
            };
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    public RoadAddressEngSearchListRequest() {
        super(RoadAddressEngSearchListResponse.class);
        setUriConsumer(
                URI_CONSUMER,
                true
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;

    @Size(max = 1)
    @NotBlank
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
