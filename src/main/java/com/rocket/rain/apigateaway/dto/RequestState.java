package com.rocket.rain.apigateaway.dto;

import com.rocket.rain.apigateaway.domain.State;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record RequestState(
                           @NotBlank
                           String name,
                           String acronym,
                           @NotBlank
                           String capital ,
                           Double area,
                           @NotNull
                           int population,
                           float PIB,
                           float IDH) implements Serializable {

    public RequestState(State state){
        this(state.getName(), state.getAcronym(), state.getCapital(),state.getArea(),state.getPopulation(),state.getPIB(),state.getIDH());
    }
}
