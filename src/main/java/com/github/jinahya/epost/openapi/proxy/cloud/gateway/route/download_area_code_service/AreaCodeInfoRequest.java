package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.download_area_code_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.__common.AbstractRequestType;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AreaCodeInfoRequest
        extends AbstractRequestType<AreaCodeInfoRequest> {

    @Serial
    private static final long serialVersionUID = 7432210876083090440L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String PARAM_DWLDSE = "dwldSe";

    @XmlEnum(Integer.class)
    public enum DwldSe {

        _1,

        _2,

        _3,

        _4;

        public static DwldSe valueOf(final int value) {
            for (final DwldSe e : DwldSe.values()) {
                if (e.ordinal() == value) {
                    return e;
                }
            }
            throw new IllegalArgumentException("no value for " + value);
        }
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static AreaCodeInfoRequest of(final Integer dwldSe) {
        return of(AreaCodeInfoRequest::new)
                .dwldSe(dwldSe);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final BiConsumer<? super AreaCodeInfoRequest, ? super UriBuilder> URI_CONSUMER = (s, b) -> {
        b.path(_DownloadAreaCodeServiceConstants.REQUEST_URI_GET_AREA_CODE_INFO)
                .queryParam(PARAM_DWLDSE, s.getDwldSe())
        ;
    };

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public AreaCodeInfoRequest() {
        super();
        setUriConsumer(URI_CONSUMER);
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
