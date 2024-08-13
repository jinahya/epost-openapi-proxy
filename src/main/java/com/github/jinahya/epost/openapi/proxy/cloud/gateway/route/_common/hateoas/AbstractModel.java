package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._common.hateoas;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractModel<SELF extends AbstractModel<SELF>>
        extends RepresentationModel<SELF>
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1775734949340833035L;

    // ----------------------------------------------------------------------------------------------------- super.links
}
