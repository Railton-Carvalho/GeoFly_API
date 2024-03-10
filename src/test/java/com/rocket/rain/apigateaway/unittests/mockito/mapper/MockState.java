package com.rocket.rain.apigateaway.unittests.mockito.mapper;

import com.ctc.wstx.osgi.WstxBundleActivator;
import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;


public class MockState implements Serializable{

    public State mockEntity() {
        return mockEntity(0);
    }

    public RequestState mockVO() {
        return mockVO(0);
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
            States.add(mockVO(i));
        }
        return States;
    }

    public State mockEntity(Integer number) {
        State State = new State();
        State.setId(number.toString());
        State.setName("First Name Test" + number);
        State.setCapital("Capital Test" + number);
        State.setAcronym("Acronym Test" + number);
        State.setArea(number.doubleValue());
        State.setPopulation(((number % 2)==0) ? number*1200 : number*900);
        State.setIDH(number.floatValue());
        State.setPIB(number.floatValue());

        return State;
    }

    public RequestState mockVO(Integer number) {
        State State = new State();
        State.setId(number.toString());
        State.setName("First Name Test" + number);
        State.setCapital("Capital Test" + number);
        State.setAcronym("Acronym Test" + number);
        State.setArea(number.doubleValue());
        State.setPopulation(((number % 2)==0) ? number*1200 : number*900);
        State.setIDH(number.floatValue());
        State.setPIB(number.floatValue());
        RequestState requestState = new RequestState(State, null);
        return requestState;
    }

}
