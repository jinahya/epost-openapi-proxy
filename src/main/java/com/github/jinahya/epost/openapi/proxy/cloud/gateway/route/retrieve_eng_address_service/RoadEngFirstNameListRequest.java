package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestType;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.function.BiConsumer;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoadEngFirstNameListRequest
        extends AbstractPairedRequestType<RoadEngFirstNameListRequest, RoadEngFirstNameListResponse> {

    @Serial
    private static final long serialVersionUID = -3089723119999877810L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static RoadEngFirstNameListRequest of(@Nullable final String serviceKey, final String stateEngName,
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
        super(RoadEngFirstNameListResponse.class);
        setUriConsumer(
                URI_CONSUMER,
                true
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
