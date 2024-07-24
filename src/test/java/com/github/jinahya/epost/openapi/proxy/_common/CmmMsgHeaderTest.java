package com.github.jinahya.epost.openapi.proxy._common;

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.HashMap;

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
                .withPrefabValues(java.util.Map.class, new HashMap<>() {{
                    put("a", new Object());
                }}, new HashMap<>() {{
                    put("b", new Object());
                }});
    }
}
