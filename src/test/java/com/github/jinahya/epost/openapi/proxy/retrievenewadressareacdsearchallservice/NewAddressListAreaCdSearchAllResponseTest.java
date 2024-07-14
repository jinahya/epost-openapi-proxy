package com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdsearchallservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.epost.openapi.proxy.bind.AbstractAddressTest;
import com.github.jinahya.epost.openapi.proxy.bind.AbstractTypeTest;
import com.github.jinahya.epost.openapi.proxy.bind.CmmMsgHeader;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
class NewAddressListAreaCdSearchAllResponseTest
        extends AbstractTypeTest<NewAddressListAreaCdSearchAllResponse> {

    @Nested
    class NewAddressListAreaCdSearchAllTest
            extends AbstractAddressTest<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll> {

        NewAddressListAreaCdSearchAllTest() {
            super(NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll> __equals(
                final SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse.NewAddressListAreaCdSearchAll> verifierApi) {
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
                            NewAddressListAreaCdSearchAllResponse.class,
                            new NewAddressListAreaCdSearchAllResponse().cmmMsgHeader(
                                    new CmmMsgHeader().requestMsgId("a")),
                            new NewAddressListAreaCdSearchAllResponse().cmmMsgHeader(
                                    new CmmMsgHeader().requestMsgId("b"))
                    );
        }
    }

    NewAddressListAreaCdSearchAllResponseTest() {
        super(NewAddressListAreaCdSearchAllResponse.class);
    }

    @Override
    protected SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse> __equals(
            SingleTypeEqualsVerifierApi<NewAddressListAreaCdSearchAllResponse> verifierApi) {
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
                        NewAddressListAreaCdSearchAllResponse.class,
                        new NewAddressListAreaCdSearchAllResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new NewAddressListAreaCdSearchAllResponse().cmmMsgHeader(
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
                r -> NewAddressListAreaCdSearchAllResponse.unmarshalInstance(xsf -> {
                    try {
                        return xsf.createXMLStreamReader(r);
                    } catch (final XMLStreamException xmlse) {
                        throw new RuntimeException(xmlse);
                    }
                })
        );
        verifyValid(unmarshalled);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Stream<String> getJsonResNameStream() {
        return Stream.of(
                "response1.json",
                "response2.json"
        );
    }

    @MethodSource({"getJsonResNameStream"})
    @ParameterizedTest
    void __json(final String resName) throws Throwable {
        final var deserialized = applyResourceAsStreamChecked(
                resName,
                r -> new ObjectMapper().readValue(r, NewAddressListAreaCdSearchAllResponse.class)
        );
        verifyValid(deserialized);
    }
}
