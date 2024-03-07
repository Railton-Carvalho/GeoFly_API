package com.rocket.rain.apigateaway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.rain.apigateaway.dto.RequestCountry;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_country")
public class Country implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String capital;
    private Double area;
    private int population;
    private float PIB;
    private float IDH;
    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private Set<Region> regions = new HashSet<>();

    private boolean Active;
    public Country(){}
    public Country(String name, String capital) {
        this.name = name;
        this.capital = capital;
        this.area = 0.0;
        this.population = 0;
        this.PIB = 0;
        this.IDH = 0;
        this.Active = true;
    }

    public Country(RequestCountry requestCountry){
        this(requestCountry.name(),requestCountry.capital());
    }

    public void updateMetrics(){
        //atualizando as metricas do pais
        float idhTotal = 0;
        float pibTotal = 0;
        resetMetrics();
        for (Region region: regions){
            this.population += region.getPopulation();
            this.area += region.getArea();
            idhTotal += region.getPIB();
            pibTotal += region.getIDH();
        }
        System.out.println(idhTotal/regions.size());
        this.setIDH(idhTotal/regions.size());
        this.setPIB(pibTotal/regions.size());
    }
    public void resetMetrics(){
        this.area = 0.0;
        this.population = 0;
        this.IDH = 0;
        this.PIB = 0;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }
    @JsonIgnore
    public Set<Region> getRegions() {
        return regions;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getPIB() {
        return PIB;
    }

    public void setPIB(float PIB) {
        this.PIB = PIB;
    }

    public float getIDH() {
        return IDH;
    }

    public void setIDH(float IDH) {
        this.IDH = IDH;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return population == country.population && Float.compare(PIB, country.PIB) == 0 && Float.compare(IDH, country.IDH) == 0 && Objects.equals(id, country.id) && Objects.equals(name, country.name) && Objects.equals(capital, country.capital) && Objects.equals(area, country.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capital, area, population, PIB, IDH);
    }

}
