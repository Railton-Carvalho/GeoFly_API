package com.rocket.rain.apigateaway.dto;

import com.rocket.rain.apigateaway.domain.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record RequestCountry(
                             @NotBlank
                             String id,
                             @NotBlank
                             String name,
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
