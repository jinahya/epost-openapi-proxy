package com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.hateoas;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class State
        extends RepresentationModel<State> {

    public static State from(final StateEngListResponse.StateEngList stateEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        final var instance = new State();
        instance.setName(stateEngList.getStateEngName());
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String name;
}
