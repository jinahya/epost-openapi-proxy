package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_longitudinal_service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedResponseType;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@XmlRootElement(name = LongitudinalDomesticListResponse.ROOT_NAME)
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LongitudinalDomesticListResponse
        extends AbstractPairedResponseType<LongitudinalDomesticListResponse, LongitudinalDomesticListRequest> {

    @Serial
    private static final long serialVersionUID = 6933169509438831925L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String ROOT_NAME = "LongitudinalDomesticListResponse";

    // -----------------------------------------------------------------------------------------------------------------
    private static final String NAME_STATE_ENG_LIST = "longitudinalDomesticList";

    @Setter
    @Getter
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class LongitudinalDomesticList
            extends AbstractType<LongitudinalDomesticList> {

        @Serial
        private static final long serialVersionUID = 2060283902665793118L;

        // ---------------------------------------------------------------------------------------------------- dlvyDate
        public static final DateTimeFormatter DLVY_DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu.MM.dd");

        // ---------------------------------------------------------------------------------------------------- dlvyTime
        public static final DateTimeFormatter DLVY_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

        // -------------------------------------------------------------------------------------- STATIC_FACTORY_METHODS

        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        // ------------------------------------------------------------------------------------------------------ parent
        public LongitudinalDomesticList parent(final LongitudinalDomesticListResponse parent) {
            setParent(parent);
            return this;
        }

        // ----------------------------------------------------------------------------------------- dlvyDateAsLocalDate
        @JsonIgnore
        @XmlTransient
        public LocalDate getDlvyDateAsLocalDate() {
            return Optional.ofNullable(getDlvyDate())
                    .map(v -> java.time.LocalDate.parse(v, DLVY_DATE_FORMATTER))
                    .orElse(null);
        }

        public void setDlvyDateAsLocalDate(final LocalDate dlvyDateAsLocalDate) {
            setDlvyDate(
                    Optional.ofNullable(dlvyDateAsLocalDate)
                            .map(DLVY_DATE_FORMATTER::format).
                            orElse(null)
            );
        }

        public LongitudinalDomesticList dlvyDateAsLocalDate(final LocalDate dlvyDateAsLocalDate) {
            setDlvyDateAsLocalDate(dlvyDateAsLocalDate);
            return this;
        }

        // ----------------------------------------------------------------------------------------- dlvyTimeAsLocalTime
        @JsonIgnore
        @XmlTransient
        public LocalTime getDlvyTimeAsLocalTime() {
            return Optional.ofNullable(getDlvyTime())
                    .map(v -> LocalTime.parse(v, DLVY_TIME_FORMATTER))
                    .orElse(null);
        }

        public void setDlvyTimeAsLocalTime(final LocalTime dlvyTimeAsLocalTime) {
            setDlvyTime(
                    Optional.ofNullable(dlvyTimeAsLocalTime)
                            .map(DLVY_TIME_FORMATTER::format).
                            orElse(null)
            );
        }

        public LongitudinalDomesticList dlvyTimeAsLocalTime(final LocalTime dlvyTimeAsLocalTime) {
            setDlvyTimeAsLocalTime(dlvyTimeAsLocalTime);
            return this;
        }

        // --------------------------------------------------------------------------------------------- getDlvyDateTime
        public LocalDateTime getDlvyDateTime(LocalDate defaultDlvyDateAsLocalDate,
                                             LocalTime defaultDlvyTimeAsLocalTime) {
            if (defaultDlvyDateAsLocalDate == null) {
                defaultDlvyDateAsLocalDate = getDlvyDateAsLocalDate();
            }
            if (defaultDlvyTimeAsLocalTime == null) {
                defaultDlvyTimeAsLocalTime = getDlvyTimeAsLocalTime();
            }
            if (defaultDlvyDateAsLocalDate == null || defaultDlvyTimeAsLocalTime == null) {
                return null;
            }
            return LocalDateTime.of(defaultDlvyDateAsLocalDate, defaultDlvyTimeAsLocalTime);
        }

        @JsonIgnore
        @XmlTransient
        public LocalDateTime getDlvyDateTime() {
            return getDlvyDateTime(null, null);
        }

        public void setDlvyDateTime(final LocalDateTime dlvyDateTime) {
            setDlvyDateAsLocalDate(
                    Optional.ofNullable(dlvyDateTime)
                            .map(LocalDateTime::toLocalDate)
                            .orElse(null)
            );
            setDlvyTimeAsLocalTime(
                    Optional.ofNullable(dlvyDateTime)
                            .map(LocalDateTime::toLocalTime)
                            .orElse(null)
            );
        }

        public LongitudinalDomesticList dlvyDateTime(final LocalDateTime dlvyDateTime) {
            setDlvyDateTime(dlvyDateTime);
            return this;
        }

        // -------------------------------------------------------------------------------------------------------------
        @JsonIgnore
        private transient LongitudinalDomesticListResponse parent;

        // -------------------------------------------------------------------------------------------------------------
        @XmlElement
        private String dlvyDate;

        @XmlElement
        private String dlvyTime;

        @XmlElement
        private String nowLc;

        @XmlElement
        private String processSttus;

        @Nullable
        @XmlElement(nillable = true)
        private String detailDc;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    public LongitudinalDomesticListResponse() {
        super(LongitudinalDomesticListRequest.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String applcntNm;

    private String addrseNm;

    private String rgist;

    private String pstmtrKnd;

    @Nullable
    @XmlElement(nillable = true)
    private String trtmntSe;

    private String dlvyDe;

    private String dlvySttus;

    private List<@Valid @NotNull LongitudinalDomesticList> longitudinalDomesticList;
}
