package com.github.jinahya.epost.openapi.proxy.retrievenewadressareacdservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.epost.openapi.proxy.xml.stream.util.NoNamespaceStreamReaderDelegate;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static java.time.temporal.ChronoField.NANO_OF_SECOND;

//@JsonRootName(value = NewAddressListResponse.ROOT_NAME)
@XmlRootElement(name = NewAddressListResponse.ROOT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
public class NewAddressListResponse
        extends AbstractType {

    static final String ROOT_NAME = "NewAddressListResponse";

    private static final String NAME_NEW_ADDRESS_LIST_AREA_CD = "newAddressListAreaCd";

    // -----------------------------------------------------------------------------------------------------------------
    @XmlAccessorType(XmlAccessType.FIELD)
    @Setter
    @Getter
    @ToString(callSuper = true)
    public static class CmmMsgHeader
            extends AbstractType {

        // ------------------------------------------------------------------------------------------------ responseTime
//        private static final String RESPONSE_TIME_PATTERN = "uuuuMMdd:HHmmssSSS"; // SSS 부분이 가변인 듯!

        private static final DateTimeFormatter RESPONSE_TIME_FORMATTER =
                new DateTimeFormatterBuilder()
                        .appendValue(ChronoField.YEAR, 4)
                        .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                        .appendValue(ChronoField.DAY_OF_MONTH, 2)
                        .appendLiteral(':')
                        .appendValue(ChronoField.HOUR_OF_DAY, 2)
                        .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                        .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
                        .optionalStart()
                        .appendFraction(NANO_OF_SECOND, 0, 9, false)
                        .toFormatter();

//        private static class ResponseTimeLocalDateTimeXmlAdapter
//                extends XmlAdapter<String, LocalDateTime> {
//
//            @Override
//            public LocalDateTime unmarshal(final String v) throws Exception {
//                if (v == null) {
//                    return null;
//                }
//                return LocalDateTime.parse(v, RESPONSE_TIME_FORMATTER);
//            }
//
//            @Override
//            public String marshal(final LocalDateTime v) throws Exception {
//                if (v == null) {
//                    return null;
//                }
//                return v.format(RESPONSE_TIME_FORMATTER);
//            }
//        }

        // --------------------------------------------------------------------------------------------------- successYN
        public static final String SUCCESS_YN_N = "N";

        public static final String SUCCESS_YN_Y = "Y";

        // -------------------------------------------------------------------------------------- STATIC_FACTORY_METHODS

        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        // ------------------------------------------------------------------------------------------------ responseTime

        /**
         * Returns current value of {@code responseTime} property as mapped with specified function.
         *
         * @param mapper the function for mapping value.
         * @param <R>    result type parameter
         * @return current value of {@code responseTime} property as mapped with {@code mapper}; {@code null} when the
         * {@code responseTime} property is currently {@code null}.
         */
        @JsonbTransient
        @JsonIgnore
        public <R> R getResponseTimeAs(final Function<? super String, ? extends R> mapper) {
            return Optional.ofNullable(getResponseTime())
                    .map(mapper)
                    .orElse(null);
        }

        @JsonbTransient
        @JsonIgnore
        public LocalDateTime getResponseTimeAsLocalDateTime() {
            return getResponseTimeAs(v -> LocalDateTime.parse(v, RESPONSE_TIME_FORMATTER));
        }

        // --------------------------------------------------------------------------------------------------- successYN

        /**
         * Returns current value of {@code successYN} property as a {@code boolean}.
         *
         * @return {@code true} if current value of {@code successYN} property is equal to {@value #SUCCESS_YN_Y}.
         */
        @JsonbTransient
        @JsonIgnore
        @XmlTransient
        public boolean isSucceeded() {
            return Optional.ofNullable(getSuccessYN())
                    .map(v -> v.equals(SUCCESS_YN_Y))
                    .orElse(Boolean.FALSE);
        }

        // -------------------------------------------------------------------------------------------------------------
        private String requestMsgId;

        private String responseMsgId;

        private String responseTime;

        private String successYN;

        private String returnCode;

        private String errMsg;

        @PositiveOrZero
        private Integer totalCount;

        @PositiveOrZero
        private Integer countPerPage;

        @Positive
        private Integer totalPage;

        @Positive
        private Integer currentPage;
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    public static class NewAddressListAreaCd
            extends AbstractType {

        @Pattern(regexp = "\\d{5}")
        @NotNull
        private String zipNo;

        private String lnmAdres;

        private String rnAdres;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @Valid
    private CmmMsgHeader cmmMsgHeader;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty(NAME_NEW_ADDRESS_LIST_AREA_CD)
    @XmlElement(name = NAME_NEW_ADDRESS_LIST_AREA_CD)
    private List<@Valid @NotNull NewAddressListAreaCd> newAddressListAreaCdList;

    // -----------------------------------------------------------------------------------------------------------------
    public NewAddressListResponse get() {
        return Optional.ofNullable(wrapped).orElse(this);
    }

    @Valid
    @JsonProperty(ROOT_NAME)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private NewAddressListResponse wrapped;

    // -----------------------------------------------------------------------------------------------------------------
    private static final JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(NewAddressListResponse.class);
        } catch (final JAXBException jaxbe) {
            throw new ExceptionInInitializerError(jaxbe);
        }
    }

    private static final XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newFactory();

    public static NewAddressListResponse unmarshalInstance(final Reader reader)
            throws XMLStreamException, JAXBException {
        return (NewAddressListResponse) JAXB_CONTEXT.createUnmarshaller()
                .unmarshal(new NoNamespaceStreamReaderDelegate(XML_INPUT_FACTORY.createXMLStreamReader(reader)));
    }

    public static NewAddressListResponse deserializeInstance(final ObjectMapper objectMapper, final String json)
            throws JsonProcessingException {
        Objects.requireNonNull(objectMapper, "objectMapper is null");
        Objects.requireNonNull(json, "json is null");
        return objectMapper.readValue(json, NewAddressListResponse.class);
    }
}
