package com.rocket.rain.apigateaway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rocket.rain.apigateaway.domain.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@JsonPropertyOrder({"id","name","capital_state"})
public record RequestCountry(
                             @NotBlank
                             String id,
                             @NotBlank
                             String name,
                             @JsonProperty("capital_state")
                             String capital,
                             Double area,
                             @NotNull
                             int population,
                             float PIB,
                             float IDH) implements Serializable {

    public RequestCountry(Country country){
        this(country.getId(), country.getName(), country.getCapital(),country.getArea(),country.getPopulation(), country.getPIB(),
                country.getIDH());
    }
}
