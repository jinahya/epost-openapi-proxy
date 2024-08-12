package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.hateoas;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.hateoas.AbstractWrappingModel;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_new_adress_area_cd_search_all_service.NewAddressListAreaCdSearchAllResponse;
import lombok.*;

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
    private static final long serialVersionUID = 6913841664418780248L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static Address from(final NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll wrapped) {
        Objects.requireNonNull(wrapped, "wrapped is null");
        final var instance = new Address();
        instance.wrapped = wrapped;
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ----------------------------------------------------------------------------------------------------- super.links
    public Address addLinks() {
        return this;
    }
}
