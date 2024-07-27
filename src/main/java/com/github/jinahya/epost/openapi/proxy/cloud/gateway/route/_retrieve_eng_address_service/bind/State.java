package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service.bind;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route._retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.bind.BaseModel;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class State
        extends BaseModel<State> {

    @Serial
    private static final long serialVersionUID = 6294048128771686178L;

    // ------------------------------------------------------------------------------------------- STATE_FACTORY_METHODS
    public static State from(final StateEngListResponse.StateEngList stateEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        final var instance = BaseModel.newInstance(State::new);
        instance.setName(stateEngList.getStateEngName());
        return instance;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String name;
}
