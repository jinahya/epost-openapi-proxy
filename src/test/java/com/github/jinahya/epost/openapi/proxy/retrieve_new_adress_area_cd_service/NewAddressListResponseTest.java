package com.github.jinahya.epost.openapi.proxy.retrieve_new_adress_area_cd_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.epost.openapi.proxy.common.AbstractTypeTest;
import com.github.jinahya.epost.openapi.proxy.common.CmmMsgHeader;
import com.github.jinahya.epost.openapi.proxy.misc.xml.stream.util.NoNamespaceStreamReaderDelegate;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NewAddressListResponseTest
        extends AbstractTypeTest<NewAddressListResponse> {

    @DisplayName("NewAddressListAreaCd")
    @Nested
    class NewAddressListAreaCdTest
            extends AbstractTypeTest<NewAddressListResponse.NewAddressListAreaCd> {

        NewAddressListAreaCdTest() {
            super(NewAddressListResponse.NewAddressListAreaCd.class);
        }

        @Override
        protected SingleTypeEqualsVerifierApi<NewAddressListResponse.NewAddressListAreaCd> __equals(
                final SingleTypeEqualsVerifierApi<NewAddressListResponse.NewAddressListAreaCd> verifierApi) {
            return super.__equals(verifierApi)
                    .withPrefabValues(
                            java.util.Map.class,
                            Map.of("a", new Object()),
                            Map.of("b", new Object())
                    );
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    NewAddressListResponseTest() {
        super(NewAddressListResponse.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected SingleTypeEqualsVerifierApi<NewAddressListResponse> __equals(
            SingleTypeEqualsVerifierApi<NewAddressListResponse> verifierApi) {
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
                        NewAddressListResponse.class,
                        new NewAddressListResponse().cmmMsgHeader(
                                new CmmMsgHeader().requestMsgId("a")),
                        new NewAddressListResponse().cmmMsgHeader(
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
            final var context = JAXBContext.newInstance(NewAddressListResponse.class);
            final var unmarshaller = context.createUnmarshaller();
            final var unmarshalled = (NewAddressListResponse) unmarshaller.unmarshal(reader);
            log.debug("unmarshalled: {}", unmarshalled);
            assertThat(unmarshalled.getUnknownAttributes()).isEmpty();
            assertThat(unmarshalled.getUnknownElements()).isEmpty();
            {
                final var serialized = new ObjectMapper().writeValueAsString(unmarshalled);
                log.debug("serialized: {}", serialized);
                final var deserialized = new ObjectMapper().readValue(serialized, NewAddressListResponse.class);
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
            final var unmarshalled = NewAddressListResponse.unmarshalInstance(resource);
        }
    }
}
