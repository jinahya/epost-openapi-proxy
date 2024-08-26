package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractRequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class RoadEngListRequest
        extends AbstractPairedRequestType<RoadEngListRequest, RoadEngListResponse> {

    @Serial
    private static final long serialVersionUID = 1235816016916872587L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static RoadEngListRequest of(@Nullable final String serviceKey, final String stateEngName,
                                        final String cityEngName, final String roadEngFirstName) {
        final var instance = AbstractRequestType.of(RoadEngListRequest::new, serviceKey);
        instance.setStateEngName(stateEngName);
        instance.setCityEngName(cityEngName);
        instance.setRoadEngFirstName(roadEngFirstName);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super RoadEngListRequest, ? super UriBuilder> URI_BUILDER = (s, b) -> {
        b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_ROAD_NAME_LIST)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, s.getCityEngName())
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_ROAD_ENG_FIRST_NAME, s.getRoadEngFirstName())
        ;
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public RoadEngListRequest() {
        super(RoadEngListResponse.class);
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
    @NotBlank
    private String roadEngFirstName;
}
