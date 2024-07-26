package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy._common._Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class RoadAddressEngSearchListRequest
        extends AbstractRequestType {

    @Serial
    private static final long serialVersionUID = 3206249731898344984L;

    // -----------------------------------------------------------------------------------------------------------------
    public static RoadAddressEngSearchListRequest.RoadAddressEngSearchListRequestBuilder<?, ?> builderFrom(
            final StateEngListResponse.StateEngList stateEngList,
            final CityEngListResponse.CityEngList cityEngList,
            final RoadEngFirstNameListResponse.RoadEngFirstNameList roadEngFirstNameList,
            final RoadEngListResponse.RoadEngList roadEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        Objects.requireNonNull(cityEngList, "cityEngList is null");
        Objects.requireNonNull(roadEngFirstNameList, "roadEngFirstNameList is null");
        Objects.requireNonNull(roadEngList, "roadEngList is null");
        return builder()
                .stateEngName(stateEngList.getStateEngName())
                .cityEngName(cityEngList.getCityEngName())
                .roadEngFirstName(roadEngFirstNameList.getRoadEngFirstName());
    }

    public static RoadAddressEngSearchListRequest from(final StateEngListResponse.StateEngList stateEngList,
                                                       final CityEngListResponse.CityEngList cityEngList,
                                                       final RoadEngFirstNameListResponse.RoadEngFirstNameList roadEngFirstNameList,
                                                       final RoadEngListResponse.RoadEngList roadEngList) {
        return builderFrom(stateEngList, cityEngList, roadEngFirstNameList, roadEngList)
                .build();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected UriBuilder set(final UriBuilder builder) {
        return super.set(
                builder.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_ADDRESS_SEARCH)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, stateEngName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, cityEngName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_FIRST_NAME, roadEngFirstName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_NAME, roadEngName)
                        .queryParamIfPresent(_RetrieveEngAddressServiceConstants.PARAM_KEYWORD,
                                             Optional.ofNullable(keyword))
                        .queryParam(_Constants.PARAM_COUNT_PER_PAGE, countPerPage)
                        .queryParam(_Constants.PARAM_CURRENT_PAGE, currentPage)
        );
    }

    public Mono<RoadAddressEngSearchListResponse> get(final WebClient webClient) {
        return get(webClient, RoadAddressEngSearchListResponse.class);
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
