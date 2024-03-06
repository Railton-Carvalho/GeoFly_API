package com.rocket.rain.apigateaway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.rain.apigateaway.dto.RequestRegion;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_region")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Double area;
    private int population;
    private float PIB;
    private float IDH;

    @ManyToOne
    @JoinTable(name = "tb_country_region", joinColumns = @JoinColumn(name = "region_id"),inverseJoinColumns =
    @JoinColumn(name = "country_id"))
    private Country country;
    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private Set<State> states = new HashSet<>();


    public Region(){}
    
    public Region(String id, String name) {
        this.id = id;
        this.name = name;
        this.area = 0.0;
        this.population = 0;
        this.PIB = 0;
        this.IDH = 0;
    }

    public void updateMetrics(){
        //atualizando as metricas da região
        float idhTotal = 0;
        float pibTotal = 0;
        resetMetrics();
        for(State state: states){
            this.population += state.getPopulation();
            this.area += state.getArea();
            idhTotal += state.getIDH();
            pibTotal += state.getPIB();
        }
        System.out.println(idhTotal/states.size());
        this.setIDH(idhTotal/states.size());
        this.setPIB(pibTotal/states.size());
    }
    public void resetMetrics(){
        this.area = 0.0;
        this.population = 0;
        this.IDH = 0;
        this.PIB = 0;
    }
    public Region(RequestRegion requestRegion){
        this(null,requestRegion.name());
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
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

    public Set<State> getStates() {
        return states;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return population == region.population && Float.compare(PIB, region.PIB) == 0 && Float.compare(IDH, region.IDH) == 0 && Objects.equals(id, region.id) && Objects.equals(name, region.name) && Objects.equals(area, region.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, area, population, PIB, IDH);
    }
}
