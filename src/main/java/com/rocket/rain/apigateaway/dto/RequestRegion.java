package com.rocket.rain.apigateaway.dto;

import com.rocket.rain.apigateaway.domain.Region;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record RequestRegion(
                            @NotBlank
                            String id,
                            @NotBlank
                            String name,
                            Double area,
                            @NotNull
                            int population,
                            float PIB,
                            float IDH )implements Serializable {

    public RequestRegion(Region region){
        this(region.getId(), region.getName(), region.getArea(),region.getPopulation(), region.getPIB(),region.getIDH());
    }

}
