package com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.epost.openapi.proxy._common.AbstractTypeTest;
import com.github.jinahya.epost.openapi.proxy._common.CmmMsgHeader;
import com.github.jinahya.epost.openapi.proxy._misc.xml.stream.util.NoNamespaceStreamReaderDelegate;
import com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_search_all_service.GetNewAddressListAreaCdSearchAllResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class GetNewAddressListAreaCdResponseTest
        extends AbstractTypeTest<GetNewAddressListAreaCdResponse> {

    @DisplayName("NewAddressListAreaCd")
    @Nested
    class NewAddressListAreaCdTest
            extends AbstractTypeTest<GetNewAddressListAreaCdResponse.NewAddressListAreaCd> {

        NewAddressListAreaCdTest() {
            super(GetNewAddressListAreaCdResponse.NewAddressListAreaCd.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<GetNewAddressListAreaCdResponse.NewAddressListAreaCd> __equals(
                final SingleTypeEqualsVerifierApi<GetNewAddressListAreaCdResponse.NewAddressListAreaCd> verifierApi) {
            return super.__equals(verifierApi)
                    .withPrefabValues(
                            java.util.Map.class,
                            Map.of("a", new Object()),
                            Map.of("b", new Object())
                    );
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    GetNewAddressListAreaCdResponseTest() {
        super(GetNewAddressListAreaCdResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected SingleTypeEqualsVerifierApi<GetNewAddressListAreaCdResponse> __equals(
            SingleTypeEqualsVerifierApi<GetNewAddressListAreaCdResponse> verifierApi) {
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
                        GetNewAddressListAreaCdResponse.class,
                        new GetNewAddressListAreaCdResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new GetNewAddressListAreaCdResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("b"))
                );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() throws IOException, XMLStreamException, JAXBException {
        try (var resource = getClass().getResourceAsStream("response1.xml")) {
            assertThat(resource).isNotNull();
            final var factory = XMLInputFactory.newFactory();
            final var reader = new NoNamespaceStreamReaderDelegate(factory.createXMLStreamReader(resource));
            final var context = JAXBContext.newInstance(GetNewAddressListAreaCdResponse.class);
            final var unmarshaller = context.createUnmarshaller();
            final var unmarshalled = (GetNewAddressListAreaCdResponse) unmarshaller.unmarshal(reader);
            log.debug("unmarshalled: {}", unmarshalled);
            assertThat(unmarshalled.getUnknownAttributes()).isEmpty();
            assertThat(unmarshalled.getUnknownElements()).isEmpty();
            {
                final var serialized = new ObjectMapper().writeValueAsString(unmarshalled);
                log.debug("serialized: {}", serialized);
                final var deserialized = new ObjectMapper().readValue(serialized, GetNewAddressListAreaCdResponse.class);
                log.debug("deserialized: {}", deserialized);
                assertThat(deserialized.getUnknownProperties()).isEmpty();
                assertThat(deserialized.getCmmMsgHeader()).satisfies(h -> {
                    assertThat(h.getSuccessYN()).isEqualTo(unmarshalled.getCmmMsgHeader().getSuccessYN());
                });
                assertThat(deserialized).isEqualTo(unmarshalled);
            }
        }
    }

    @Test
    void unmarshalInstance__() throws IOException, JAXBException {
        try (var resource = getClass().getResourceAsStream("response1.xml")) {
            final var unmarshalled = GetNewAddressListAreaCdResponse.unmarshalInstance(resource);
        }
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
                GetNewAddressListAreaCdResponse::unmarshalInstance
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
                GetNewAddressListAreaCdResponse::deserializeInstance
        );
        verifyValid(deserialized);
    }
}
