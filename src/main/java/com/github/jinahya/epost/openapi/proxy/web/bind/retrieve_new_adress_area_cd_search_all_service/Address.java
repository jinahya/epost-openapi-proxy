package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_new_adress_area_cd_search_all_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Address
        extends AbstractWrappingModel<Address, NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll> {

    @Serial
    private static final long serialVersionUID = -4131507370713536523L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static Address newInstance(
            final NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new Address();
        instance.setWrapped(wrapped);
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links

    // --------------------------------------------------------------------------------------------------- super.wrapped
}
