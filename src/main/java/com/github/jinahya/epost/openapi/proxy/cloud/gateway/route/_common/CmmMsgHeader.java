package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static java.time.temporal.ChronoField.NANO_OF_SECOND;

/**
 * A class for binding {@code /:cmmMsgHeader} part.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class CmmMsgHeader
        extends AbstractType<CmmMsgHeader> {

    @Serial
    private static final long serialVersionUID = 4236051073314958906L;

    // ---------------------------------------------------------------------------------------------------- responseTime
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

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------------- successYN
    public static final String SUCCESS_YN_N = "N";

    public static final String SUCCESS_YN_Y = "Y";

    // ------------------------------------------------------------------------------------------------------ returnCode
    public static final String RETURN_CODE_00 = "00";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------------- requestMsgId
    // just for the prefab values
    CmmMsgHeader requestMsgId(final String requestMsgId) {
        setRequestMsgId(requestMsgId);
        return this;
    }

    // ---------------------------------------------------------------------------------------------------- responseTime

    /**
     * Returns current value of {@code responseTime} property as mapped by specified function.
     *
     * @param mapper the function for mapping value.
     * @param <R>    result type parameter
     * @return current value of {@code responseTime} property as mapped by {@code mapper}; {@code null} when the
     * {@code responseTime} property is currently {@code null}.
     */
//    @JsonIgnore
//    @XmlTransient // JAXB annotation is placed on a method that is not a JAXB property
    public <R> R getResponseTimeAsMappedBy(final Function<? super String, ? extends R> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return Optional.ofNullable(getResponseTime())
                .map(mapper)
                .orElse(null);
    }

    /**
     * Returns current value of {@code responseTime} property parsed into an instance of {@link LocalDateTime}.
     *
     * @return current value of the {@code responseTime} property parsed into an instance of {@link LocalDateTime};
     * {@code null} when rns current value of {@code responseTime} property is {@code null}.
     */
    @JsonIgnore
    @XmlTransient // JAXB annotation is placed on a method that is not a JAXB property
    public LocalDateTime getResponseTimeAsLocalDateTime() {
        return getResponseTimeAsMappedBy(v -> LocalDateTime.parse(v, RESPONSE_TIME_FORMATTER));
    }

    // ------------------------------------------------------------------------------------------------------- successYN

    /**
     * Checks whether current value of {@code successYN} property is equal to {@value #SUCCESS_YN_Y}.
     *
     * @return {@code true} if current value of {@code successYN} property is equal to {@value #SUCCESS_YN_Y};
     * {@code false} otherwise.
     */
    @JsonIgnore
    @XmlTransient
    public boolean isSucceeded() {
        return Optional.ofNullable(getSuccessYN())
                .map(v -> v.equals(SUCCESS_YN_Y))
                .orElse(false);
    }

    // ------------------------------------------------------------------------------------------------------ returnCode

    /**
     * Checks whether current value of {@code returnCode} property is equal to {@value #RETURN_CODE_00}.
     *
     * @return {@code true} if current value of {@code returnCode} property is equal to {@value #RETURN_CODE_00};
     * {@code false} otherwise.
     */
    @JsonIgnore
    @XmlTransient
    public boolean isReturnCode00() {
        return Optional.ofNullable(getReturnCode())
                .map(v -> v.equals(RETURN_CODE_00))
                .orElse(false);
    }

    // ---------------------------------------------------------------------------------------------------------- errMsg

    // -----------------------------------------------------------------------------------------------------------------
    @XmlElement
    private String requestMsgId; // 무엇에 쓰는 물건인고

    @XmlElement
    private String responseMsgId; // 무엇에 쓰는 물건인고

    // -----------------------------------------------------------------------------------------------------------------
    @XmlElement
    private String responseTime;

    // -----------------------------------------------------------------------------------------------------------------
    @XmlElement
    private String successYN;

    @XmlElement
    private String returnCode;

    @XmlElement
    private String errMsg;

    // -----------------------------------------------------------------------------------------------------------------
    @PositiveOrZero
    @XmlElement
    private Integer totalCount;

    @PositiveOrZero
    @XmlElement
    private Integer countPerPage;

    @Positive
    @XmlElement
    private Integer totalPage;

    @Positive
    @XmlElement
    private Integer currentPage;

    // -----------------------------------------------------------------------------------------------------------------
    public boolean hasNextPage() {
        return currentPage != null && totalPage != null && currentPage < totalPage;
    }
}
