package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.hateoas;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.AbstractType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractWrappingModel<
        SELF extends AbstractWrappingModel<SELF, WRAPPED>,
        WRAPPED extends AbstractType<WRAPPED>>
        extends AbstractModel<SELF> {

    @Serial
    private static final long serialVersionUID = 2185796969322484068L;

    // -----------------------------------------------------------------------------------------------------------------
    @JsonUnwrapped
    @NotNull
    protected WRAPPED wrapped;
}
