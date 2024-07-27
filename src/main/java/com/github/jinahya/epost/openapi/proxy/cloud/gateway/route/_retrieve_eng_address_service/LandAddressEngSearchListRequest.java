package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import com.github.jinahya.epost.openapi.proxy._common._Constants;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.Optional;
import java.util.function.BiConsumer;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LandAddressEngSearchListRequest
        extends AbstractRequestType<LandAddressEngSearchListRequest> {

    @Serial
    private static final long serialVersionUID = -1827246138318731047L;

//    // -----------------------------------------------------------------------------------------------------------------
//    public static LandAddressEngSearchListRequest.LandAddressEngSearchListRequestBuilder<?, ?> builderFrom(
//            final StateEngListResponse.StateEngList stateEngList,
//            final CityEngListResponse.CityEngList cityEngList,
//            final DistrictEngFirstNameListResponse.DistrictEngFirstNameList districtEngFirstNameList,
//            final DistrictEngListResponse.DistrictEngList districtEngList) {
//        Objects.requireNonNull(stateEngList, "stateEngList is null");
//        Objects.requireNonNull(cityEngList, "cityEngList is null");
//        Objects.requireNonNull(districtEngFirstNameList, "districtEngFirstNameList is null");
//        Objects.requireNonNull(districtEngList, "districtEngList is null");
//        return builder()
//                .stateEngName(stateEngList.getStateEngName())
//                .cityEngName(cityEngList.getCityEngName())
//                .districtEngFirstName(districtEngFirstNameList.getDistrictEngFirstName())
//                .districtEngName(districtEngList.getDistrictEngName());
//    }
//
//    public static LandAddressEngSearchListRequest from(
//            final StateEngListResponse.StateEngList stateEngList,
//            final CityEngListResponse.CityEngList cityEngList,
//            final DistrictEngFirstNameListResponse.DistrictEngFirstNameList districtEngFirstNameList,
//            final DistrictEngListResponse.DistrictEngList districtEngList) {
//        return builderFrom(stateEngList, cityEngList, districtEngFirstNameList, districtEngList)
//                .build();
//    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    static LandAddressEngSearchListRequest of(final @Nullable String serviceKey, final String stateEngName,
                                              final String cityEngName, final String districtEngFirstName,
                                              final String districtEngName, final @Nullable String keyword,
                                              final Integer countPerPage, final Integer currentPage) {
        final LandAddressEngSearchListRequest instance = new LandAddressEngSearchListRequest();
        instance.setServiceKey(serviceKey);
        instance.setStateEngName(stateEngName);
        instance.setCityEngName(cityEngName);
        instance.setDistrictEngFirstName(districtEngFirstName);
        instance.setDistrictEngName(districtEngName);
        instance.setKeyword(keyword);
        instance.setCountPerPage(countPerPage);
        instance.setCurrentPage(currentPage);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super LandAddressEngSearchListRequest, ? super UriBuilder> URI_CONSUMER = (s, b) -> {
        b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_LAND_ADDRESS_SEARCH)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.stateEngName)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, s.cityEngName)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_DISTRICT_ENG_FIRST_NAME,
                            s.districtEngFirstName)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_DISTRICT_ENG_NAME, s.districtEngName)
                .queryParamIfPresent(_RetrieveEngAddressServiceConstants.PARAM_KEYWORD,
                                     Optional.ofNullable(s.keyword))
                .queryParam(_Constants.PARAM_COUNT_PER_PAGE, s.countPerPage)
                .queryParam(_Constants.PARAM_CURRENT_PAGE, s.currentPage);
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    public LandAddressEngSearchListRequest() {
        super();
        setUriConsumer(URI_CONSUMER);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected UriBuilder set(final UriBuilder builder) {
        return super.set(
                builder.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_LAND_ADDRESS_SEARCH)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, stateEngName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, cityEngName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_DISTRICT_ENG_FIRST_NAME,
                                    districtEngFirstName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_DISTRICT_ENG_NAME, districtEngName)
                        .queryParamIfPresent(_RetrieveEngAddressServiceConstants.PARAM_KEYWORD,
                                             Optional.ofNullable(keyword))
                        .queryParam(_Constants.PARAM_COUNT_PER_PAGE, countPerPage)
                        .queryParam(_Constants.PARAM_CURRENT_PAGE, currentPage)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;

    @Size(max = 1)
    @NotBlank
    private String districtEngFirstName;

    @NotBlank
    private String districtEngName;

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
