package com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.epost.openapi.proxy.xml.stream.util.NoNamespaceStreamReaderDelegate;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NewAddressListResponseTest {

    @Test
    void __() throws IOException, XMLStreamException, JAXBException {
        try (var resource = getClass().getResourceAsStream("response1.xml")) {
            assertThat(resource).isNotNull();
            final var factory = XMLInputFactory.newFactory();
            final var reader = new NoNamespaceStreamReaderDelegate(factory.createXMLStreamReader(resource));
            final var context = JAXBContext.newInstance(NewAddressListResponse.class);
            final var unmarshaller = context.createUnmarshaller();
            final var value = (NewAddressListResponse) unmarshaller.unmarshal(reader);
            log.debug("value: {}", value);
            final var json = new ObjectMapper().writeValueAsString(value);
            log.debug("json: {}", json);
        }
    }
}
