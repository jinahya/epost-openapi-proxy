package com.github.jinahya.epost.openapi.proxy.web.bind.retrieve_eng_address_service;

import com.github.jinahya.epost.openapi.proxy.cloud.gateway.route.retrieve_eng_address_service.StateEngListResponse;
import com.github.jinahya.epost.openapi.proxy.web.bind.AbstractWrappingModel2;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;

@Schema(description = "Model for wrapping StateEngList")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Slf4j
public class State2
        extends AbstractWrappingModel2<State2, StateEngListResponse.StateEngList> {

    @Serial
    private static final long serialVersionUID = 8227269351954995308L;

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS
    public static State2 newInstance(final StateEngListResponse.StateEngList stateEngList) {
        Objects.requireNonNull(stateEngList, "stateEngList is null");
        return newInstance(State2::new, stateEngList);
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ---------------------------------------------------------------------------------------------------- stateEngList
    @Override
    public void setWrapped(StateEngListResponse.StateEngList stateEngList) {
        super.setWrapped(stateEngList);
        setName(
                Optional.ofNullable(getWrapped())
                        .map(StateEngListResponse.StateEngList::getStateEngName)
                        .orElse(null)
        );
    }

    // ------------------------------------------------------------------------------------------------------------ name

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    private String name;
}
