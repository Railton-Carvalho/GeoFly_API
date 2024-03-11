package com.rocket.rain.apigateaway.unittests.mockito.mapper;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.link.DtoLink;
import com.rocket.rain.apigateaway.resources.StateResource;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class MockState implements Serializable{

    public State mockEntity() {
        return mockEntity(0);
    }

    public RequestState mockDto() {
        return mockDto(0);
    }

    public List<State> mockEntityList() {
        List<State> States = new ArrayList<State>();
        for (int i = 0; i < 14; i++) {
            States.add(mockEntity(i));
        }
        return States;
    }

    public List<RequestState> mockVOList() {
        List<RequestState> States = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            States.add(mockDto(i));
        }
        return States;
    }

    public State mockEntity(Integer number) {
        State State = new State();
        State.setName("First Name Test" + number);
        State.setCapital("Capital Test" + number);
        State.setAcronym("Acronym Test" + number);
        State.setArea(number.doubleValue());
        State.setPopulation(((number % 2)==0) ? number*1200 : number*900);
        State.setIDH(number.floatValue());
        State.setPIB(number.floatValue());
        State.setActive(true);
        return State;
    }

    public RequestState mockDto(Integer number) {
        State State = new State();
        State.setId(number.toString());
        State.setName("First Name Test" + number);
        State.setCapital("Capital Test" + number);
        State.setAcronym("Acronym Test" + number);
        State.setArea(number.doubleValue());
        State.setPopulation(((number % 2)==0) ? number*1200 : number*900);
        State.setIDH(number.floatValue());
        State.setPIB(number.floatValue());

        DtoLink dtoLink = new DtoLink();
//        dtoLink.add(linkTo(methodOn(StateResource.class).findStateById(State.getId())).withSelfRel());
        RequestState requestState = new RequestState(State,dtoLink );
        return requestState;
    }
}
