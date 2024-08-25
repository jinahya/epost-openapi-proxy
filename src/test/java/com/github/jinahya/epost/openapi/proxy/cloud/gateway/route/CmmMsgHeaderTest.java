package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.Map;

@DisplayName("CmmMsgHeader")
@Nested
class CmmMsgHeaderTest
        extends AbstractTypeTest<CmmMsgHeader> {

    CmmMsgHeaderTest() {
        super(CmmMsgHeader.class);
    }

    @Override
    protected SingleTypeEqualsVerifierApi<CmmMsgHeader> __equals(
            SingleTypeEqualsVerifierApi<CmmMsgHeader> verifierApi) {
        return super.__equals(verifierApi)
                .withPrefabValues(
                        Map.class,
                        Map.of("a", new Object()),
                        Map.of("b", new Object())
                );
    }
}
