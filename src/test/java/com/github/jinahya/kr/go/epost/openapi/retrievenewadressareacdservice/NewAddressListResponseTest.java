package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import com.github.jinahya.xml.stream.util.NoNamespaceStreamReaderDelegate;
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
            final var xif = XMLInputFactory.newFactory();
            final var xsr = new NoNamespaceStreamReaderDelegate(xif.createXMLStreamReader(resource));
            final var jc = JAXBContext.newInstance(NewAddressListResponse.class);
            final var unmarshaller = jc.createUnmarshaller();
            final var value = (NewAddressListResponse) unmarshaller.unmarshal(xsr);
            log.debug("value: {}", value);

        }
    }
}
