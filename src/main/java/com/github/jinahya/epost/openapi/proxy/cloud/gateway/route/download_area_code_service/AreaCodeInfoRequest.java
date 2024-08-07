package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractPairedRequestType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriBuilder;

import java.io.Serial;
import java.util.function.BiConsumer;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AreaCodeInfoRequest
        extends AbstractPairedRequestType<AreaCodeInfoRequest, AreaCodeInfoResponse> {

    @Serial
    private static final long serialVersionUID = 7432210876083090440L;

    // ---------------------------------------------------------------------------------------------------------- deldSe

    /**
     * 우편번호 전체.
     */
    public static final int DWLD_SE_1 = 1;

    /**
     * 우편번호 변경분.
     */
    public static final int DWLD_SE_2 = 2;

    /**
     * 범위 주소.
     */
    public static final int DWLD_SE_3 = 3;

    /**
     * 사서함 주소.
     */
    public static final int DWLD_SE_4 = 4;

    @XmlEnum(Integer.class)
    public enum DwldSe {

        /**
         * 우편번호 전체.
         */
        _1,

        /**
         * 우편번호 변경분.
         */
        _2,

        /**
         * 범위 주소.
         */
        _3,

        /**
         * 사서함 주소.
         */
        _4;

        public static DwldSe valueOf(final int value) {
            for (final DwldSe v : values()) {
                if (v.getValue() == value) {
                    return v;
                }
            }
            throw new IllegalArgumentException("no value for " + value);
        }

        DwldSe(final Integer value) {
            this.value = value;
        }

        DwldSe() {
            this(null);
        }

        public int getValue() {
            if (value == null) {
                return ordinal() + 1;
            }
            return value;
        }

        private final Integer value;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static AreaCodeInfoRequest of(final Integer dwldSe) {
        return of(AreaCodeInfoRequest::new)
                .dwldSe(dwldSe);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super AreaCodeInfoRequest, ? super UriBuilder> URI_CONSUMER = (s, b) -> {
        b.path(_DownloadAreaCodeServiceConstants.REQUEST_URI_GET_AREA_CODE_INFO)
                .queryParam(_DownloadAreaCodeServiceConstants.PARAM_NAME_DWLDSE, s.getDwldSe())
        ;
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public AreaCodeInfoRequest() {
        super(AreaCodeInfoResponse.class);
        setUriConsumer(
                URI_CONSUMER,
                true
        );
    }

    // ---------------------------------------------------------------------------------------------------------- dwldSe
    public AreaCodeInfoRequest dwldSe(final Integer dwldSe) {
        setDwldSe(dwldSe);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    private Integer dwldSe;
}
