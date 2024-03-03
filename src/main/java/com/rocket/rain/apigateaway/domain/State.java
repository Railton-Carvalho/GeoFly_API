package com.rocket.rain.apigateaway.domain;

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


    public State(String id, String name, String acronym, String capital, Double area, int population, float PIB, float IDH) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.PIB = PIB;
        this.IDH = IDH;
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
