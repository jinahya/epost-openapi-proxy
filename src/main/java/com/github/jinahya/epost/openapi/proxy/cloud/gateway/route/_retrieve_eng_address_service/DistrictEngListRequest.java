package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractRequestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class DistrictEngListRequest
        extends AbstractRequestType<DistrictEngListRequest> {

    @Serial
    private static final long serialVersionUID = -6793297919987439959L;

    // --------------------------------------------------------------------------------------------------- STATIC_FACTORY_METHODS
    public static DistrictEngListRequest of(final String serviceKey, final String stateEngName,
                                            final String cityEngName, final String districtEngFirstName) {
        final DistrictEngListRequest instance = new DistrictEngListRequest();
        instance.setServiceKey(serviceKey);
        instance.setStateEngName(stateEngName);
        instance.setCityEngName(cityEngName);
        instance.setDistrictEngFirstName(districtEngFirstName);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super DistrictEngListRequest, ? super UriBuilder> URI_CONSUMER = (s, b) -> {
        b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_NAME_LIST)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, s.getCityEngName())
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_DISTRICT_ENG_FIRST_NAME,
                            s.getDistrictEngFirstName());
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public DistrictEngListRequest() {
        super();
        setUriConsumer(URI_CONSUMER);
    }

//    public DistrictEngListRequest(final DistrictEngListRequest.DistrictEngListRequestBuilder<?, ?> builder) {
//        super(builder);
//        setUriConsumer(URI_CONSUMER);
//    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected UriBuilder set(UriBuilder builder) {
        return super.set(
                builder.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_DISTRICT_NAME_LIST)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, stateEngName)
                        .queryParam(_RetrieveEngAddressServiceConstants.PARAM_CITY_ENG_NAME, cityEngName)
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
}
