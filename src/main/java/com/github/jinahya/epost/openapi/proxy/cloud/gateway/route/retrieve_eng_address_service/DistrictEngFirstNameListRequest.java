package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractPairedRequestType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractRequestType;
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
public class DistrictEngFirstNameListRequest
        extends AbstractPairedRequestType<DistrictEngFirstNameListRequest, DistrictEngFirstNameListResponse> {

    @Serial
    private static final long serialVersionUID = -6632649622472952951L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static DistrictEngFirstNameListRequest of(final String serviceKey, final String stateEngName,
                                                     final String cityEngName) {
        final var instance = AbstractRequestType.of(DistrictEngFirstNameListRequest::new, serviceKey);
        instance.setStateEngName(stateEngName);
        instance.setCityEngName(cityEngName);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super DistrictEngFirstNameListRequest, ? super UriBuilder> URI_CONSUMER =
            (s, b) -> {
                b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_FIRST_NAME_LIST)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, s.getCityEngName())
                ;
            };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public DistrictEngFirstNameListRequest() {
        super(DistrictEngFirstNameListResponse.class);
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
}
