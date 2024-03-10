package com.rocket.rain.apigateaway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.link.StateLink;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.Links;

import java.io.Serializable;

@JsonPropertyOrder({"id","name","capital","population","area","acronym","PIB","IDH","region"})
public record RequestState(
        @NotBlank
                           String name,
        String acronym,
        @NotBlank
                           String capital ,
        Double area,
        @NotNull @JsonProperty("Habitantes")
                           int population,
        float PIB,
        float IDH,
        Region region,
        Links links
                            ) implements Serializable {

    public RequestState(State state, StateLink stateLink) {
        this(state.getName(), state.getAcronym(), state.getCapital(), state.getArea(), state.getPopulation(),
                state.getPIB(), state.getIDH(), state.getRegion(),stateLink.getLinks());
    }

}
