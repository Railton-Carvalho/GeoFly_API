package com.rocket.rain.apigateaway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_state")
public class State implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String acronym;
    private String capital;
    private Double area;
    private int population;
    private float PIB;
    private float IDH;

    @ManyToOne
    @JoinTable(name = "tb_state_region", joinColumns = @JoinColumn(name = "state_id"),inverseJoinColumns =
    @JoinColumn(name = "region_id"))
    private Region region;
    private boolean active;
    public State(){}
    public State(String id, String name, String acronym, String capital, Double area, int population, float PIB, float IDH) {
        this.active = true;
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.PIB = PIB;
        this.IDH = IDH;

    }
    public State(RequestState requestState){
        this(null,requestState.name(),requestState.acronym(),requestState.capital(),requestState.area(),
                requestState.population(),requestState.PIB(),requestState.IDH());
    }
    public State(UpdateState updateState){
        this(updateState.id(),updateState.name(),updateState.acronym(),updateState.capital(),updateState.area(),
                updateState.population(),updateState.PIB(),updateState.IDH());
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setIDH(float IDH) {
        this.IDH = IDH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return population == state.population && Float.compare(PIB, state.PIB) == 0 && Float.compare(IDH, state.IDH) == 0 && Objects.equals(id, state.id) && Objects.equals(name, state.name) && Objects.equals(acronym, state.acronym) && Objects.equals(capital, state.capital) && Objects.equals(area, state.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, acronym, capital, area, population, PIB, IDH);
    }
}
