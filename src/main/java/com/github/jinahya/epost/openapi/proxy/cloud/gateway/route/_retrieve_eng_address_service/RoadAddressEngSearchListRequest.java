package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common._Constants;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.io.Serial;
import java.util.Optional;
import java.util.function.BiConsumer;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoadAddressEngSearchListRequest
        extends AbstractRequestType<RoadAddressEngSearchListRequest> {

    @Serial
    private static final long serialVersionUID = 3206249731898344984L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
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
                        .queryParam(_Constants.PARAM_COUNT_PER_PAGE, s.countPerPage)
                        .queryParam(_Constants.PARAM_CURRENT_PAGE, s.currentPage);
                ;
            };
    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    public RoadAddressEngSearchListRequest() {
        super();
        setUriConsumer(URI_CONSUMER, true);
    }

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
