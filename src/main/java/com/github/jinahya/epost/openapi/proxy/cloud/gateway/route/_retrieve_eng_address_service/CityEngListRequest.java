package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestType;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CityEngListRequest
        extends AbstractRequestType<CityEngListRequest> {

    @Serial
    private static final long serialVersionUID = 2981550532310902459L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static CityEngListRequest of(final String serviceKey, final String stateEngName) {
        final CityEngListRequest instance = new CityEngListRequest();
        instance.setServiceKey(serviceKey);
        instance.setStateEngName(stateEngName);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super CityEngListRequest, ? super UriBuilder> URI_CONSUMER = (s, b) -> {
        b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NM, s.getStateEngName())
//                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
        ;
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    public CityEngListRequest() {
        super();
        setUriConsumer(
                URI_CONSUMER,
                true
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    public UriBuilder set(final UriBuilder builder) {
        return super.set(
                builder
                        .path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST)
                        .replaceQueryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NM, stateEngName)
        );
    }

    // ---------------------------------------------------------------------------------------------------- stateEngName

    // ----------------------------------------------------------------------------------------------------- cityEngName

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;
}
