package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import jakarta.validation.constraints.NotBlank;
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
public class RoadEngFirstNameListRequest
        extends AbstractRequestType<RoadEngFirstNameListRequest> {

    @Serial
    private static final long serialVersionUID = -3089723119999877810L;

//    // -----------------------------------------------------------------------------------------------------------------
//    public static RoadEngFirstNameListRequest.RoadEngFirstNameListRequestBuilder<?, ?> builderFrom(
//            final StateEngListResponse.StateEngList stateEngList,
//            final CityEngListResponse.CityEngList cityEngList) {
//        Objects.requireNonNull(stateEngList, "stateEngList is null");
//        Objects.requireNonNull(cityEngList, "cityEngList is null");
//        return builder()
//                .stateEngName(stateEngList.getStateEngName())
//                .cityEngName(cityEngList.getCityEngName());
//    }
//
//    public static RoadEngFirstNameListRequest from(final StateEngListResponse.StateEngList stateEngList,
//                                                   final CityEngListResponse.CityEngList cityEngList) {
//        return builderFrom(stateEngList, cityEngList)
//                .build();
//    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static RoadEngFirstNameListRequest of(final String serviceKey, final String stateEngName,
                                                 final String cityEngName) {
        final var instance = AbstractRequestType.of(RoadEngFirstNameListRequest::new, serviceKey);
        instance.setStateEngName(stateEngName);
        instance.setCityEngName(cityEngName);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super RoadEngFirstNameListRequest, ? super UriBuilder> URI_CONSUMER =
            (s, b) -> {
                b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_FIRST_NAME_LIST)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, s.getCityEngName())
                ;
            };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    public RoadEngFirstNameListRequest() {
        super();
        setUriConsumer(
                URI_CONSUMER,
                true

        );
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected UriBuilder set(final UriBuilder builder) {
        return super.set(
                builder.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_FIRST_NAME_LIST)
                        .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, stateEngName)
                        .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, cityEngName)
        );
    }

    // ---------------------------------------------------------------------------------------------------- stateEngName

    // ----------------------------------------------------------------------------------------------------- cityEngName
    public RoadEngFirstNameListRequest cityEngName(final String cityEngName) {
        setCityEngName(cityEngName);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;

    @NotBlank
    private String cityEngName;
}
