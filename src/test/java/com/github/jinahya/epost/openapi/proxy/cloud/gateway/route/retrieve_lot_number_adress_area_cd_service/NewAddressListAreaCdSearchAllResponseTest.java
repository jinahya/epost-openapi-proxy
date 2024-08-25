package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_lot_number_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractPairedResponseTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.AbstractResponseElementAddressTypeTest;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_lot_number_adress_area_cd_service.NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;

class NewAddressListAreaCdSearchAllResponseTest
        extends AbstractPairedResponseTypeTest<
        NewAddressListAreaCdSearchAllResponse,
        NewAddressListAreaCdSearchAllRequest> {

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class NewAddressListAreaCdSearchAllTest
            extends AbstractResponseElementAddressTypeTest<
            NewAddressListAreaCdSearchAll,
            NewAddressListAreaCdSearchAllResponse> {

        NewAddressListAreaCdSearchAllTest() {
            super(NewAddressListAreaCdSearchAll.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAll> __equals(
                final SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAll> verifierApi) {
            return super.__equals(verifierApi);
        }
    }

    NewAddressListAreaCdSearchAllResponseTest() {
        super(NewAddressListAreaCdSearchAllResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse> __equals(
            final SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse> verifierApi) {
        return super.__equals(verifierApi);
    }
}
