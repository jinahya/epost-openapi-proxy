package com.github.jinahya.epost.openapi.proxy.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Optional;
import java.util.function.Function;

import static java.time.temporal.ChronoField.NANO_OF_SECOND;

// -----------------------------------------------------------------------------------------------------------------
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CmmMsgHeader
        extends AbstractType {

    @Serial
    private static final long serialVersionUID = 4236051073314958906L;

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

    // -------------------------------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------------------ requestMsgId
    // just for the prefab values
    public CmmMsgHeader requestMsgId(final String requestMsgId) {
        setRequestMsgId(requestMsgId);
        return this;
    }

    // ------------------------------------------------------------------------------------------------ responseTime

    /**
     * Returns current value of {@code responseTime} property as mapped with specified function.
     *
     * @param mapper the function for mapping value.
     * @param <R>    result type parameter
     * @return current value of {@code responseTime} property as mapped with {@code mapper}; {@code null} when the
     * {@code responseTime} property is currently {@code null}.
     */
    @JsonIgnore
    public <R> R getResponseTimeAs(final Function<? super String, ? extends R> mapper) {
        return Optional.ofNullable(getResponseTime())
                .map(mapper)
                .orElse(null);
    }

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
