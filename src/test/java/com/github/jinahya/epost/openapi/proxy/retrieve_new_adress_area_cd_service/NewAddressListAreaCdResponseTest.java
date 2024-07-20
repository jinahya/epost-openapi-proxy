package com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_service;

import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
class NewAddressListAreaCdResponseTest
        extends AbstractTypeTest<NewAddressListAreaCdResponse> {

    @DisplayName("NewAddressListAreaCd")
    @Nested
    class NewAddressListAreaCdTest
            extends AbstractTypeTest<NewAddressListAreaCdResponse.NewAddressListAreaCd> {

        NewAddressListAreaCdTest() {
            super(NewAddressListAreaCdResponse.NewAddressListAreaCd.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse.NewAddressListAreaCd> __equals(
                final SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse.NewAddressListAreaCd> verifierApi) {
            return super.__equals(verifierApi)
                    .withPrefabValues(
                            java.util.Map.class,
                            Map.of("a", new Object()),
                            Map.of("b", new Object())
                    );
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    NewAddressListAreaCdResponseTest() {
        super(NewAddressListAreaCdResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse> __equals(
            SingleTypeEqualsVerifierApi<NewAddressListAreaCdResponse> verifierApi) {
        return super.__equals(verifierApi)
                .withPrefabValues(
                        CmmMsgHeader.class,
                        new CmmMsgHeader().requestMsgId("a"),
                        new CmmMsgHeader().requestMsgId("b")
                )
                .withPrefabValues(
                        java.util.List.class,
                        List.of(new Object()),
                        List.of(new Object())
                )
                .withPrefabValues(
                        java.util.Map.class,
                        Map.of("a", new Object()),
                        Map.of("b", new Object())
                )
                .withPrefabValues(
                        NewAddressListAreaCdResponse.class,
                        new NewAddressListAreaCdResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new NewAddressListAreaCdResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("b"))
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getXmlResNameStream() {
        return Stream.of(
                "response1.xml",
                "response2.xml"
        );
    }

    @MethodSource({"getXmlResNameStream"})
    @ParameterizedTest
    void __xml(final String resName) throws Throwable {
        final var unmarshalled = applyResourceAsStreamChecked(
                resName,
                NewAddressListAreaCdResponse::unmarshalInstance
        );
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "response1.json",
                "response2.json",
                "response1_.json",
                "response2_.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                NewAddressListAreaCdResponse::deserializeInstance
        ).get();
        verifyValid(deserialized);
    }
}
