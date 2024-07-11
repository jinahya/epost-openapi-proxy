package com.github.jinahya.kr.go.epost.openapi.retrievenewadressareacdservice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@XmlRootElement(name = "NewAddressListResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString(callSuper = true)
public class NewAddressListResponse
        extends AbstractType {

    @XmlAccessorType(XmlAccessType.FIELD)
    @ToString(callSuper = true)
    public static class CmmMsgHeader
            extends AbstractType {

        // -------------------------------------------------------------------------------------------------------------
        private static final String RESPONSE_TIME_PATTERN = "uuuuMMdd:HHmmssSSS";

        private static final DateTimeFormatter RESPONSE_TIME_FORMATTER =
                DateTimeFormatter.ofPattern(RESPONSE_TIME_PATTERN);

        private static class ResponseTypeAdapter
                extends XmlAdapter<String, LocalDateTime> {

            @Override
            public LocalDateTime unmarshal(final String v) throws Exception {
                if (v == null) {
                    return null;
                }
                return LocalDateTime.parse(v, RESPONSE_TIME_FORMATTER);
            }

            @Override
            public String marshal(final LocalDateTime v) throws Exception {
                if (v == null) {
                    return null;
                }
                return v.format(RESPONSE_TIME_FORMATTER);
            }
        }

        // ------------------------------------------------------------------------------------------------ responseTime

        // --------------------------------------------------------------------------------------------------- successYN

        // -------------------------------------------------------------------------------------------------------------
        private String requestMsgId;

        private String responseMsgId;

        private String responseTime;
//        @XmlJavaTypeAdapter(ResponseTypeAdapter.class)
//        private LocalDateTime responseTime;

        //        @XmlElement(name = "successYN")
//        private String successYn;
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

    // -----------------------------------------------------------------------------------------------------------------
    private CmmMsgHeader cmmMsgHeader;

    @XmlElement(name = "newAddressListAreaCd")
    private List<NewAddressListAreaCd> newAddressListAreaCdList;

}
