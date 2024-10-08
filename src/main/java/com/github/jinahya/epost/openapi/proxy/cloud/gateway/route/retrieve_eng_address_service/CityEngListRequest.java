package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedRequestType;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.function.BiConsumer;

/**
 * A class for binding request to the {@value _RetrieveEngAddressServiceConstants#REQUEST_URI_GET_CITY_LIST} operation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see CityEngListResponse
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CityEngListRequest
        extends AbstractPairedRequestType<CityEngListRequest, CityEngListResponse> {

    @Serial
    private static final long serialVersionUID = 2981550532310902459L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static CityEngListRequest of(@Nullable final String serviceKey, final String stateEngName) {
        final CityEngListRequest instance = of(CityEngListRequest::new, serviceKey);
        instance.setStateEngName(stateEngName);
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super CityEngListRequest, ? super UriBuilder> URI_BUILDER = (s, b) -> {
        b.path(_RetrieveEngAddressServiceConstants.REQUEST_URI_GET_CITY_LIST)
                .queryParam(_RetrieveEngAddressServiceConstants.PARAM_STATE_ENG_NAME, s.getStateEngName())
        ;
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public CityEngListRequest() {
        super(CityEngListResponse.class);
        setUriConfigurer(
                URI_BUILDER,
                true
        );
    }

    // ---------------------------------------------------------------------------------------------------- stateEngName

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String stateEngName;
}
