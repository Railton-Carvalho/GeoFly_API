package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class StateService implements Serializable {

    @Autowired
    private StateRepository repository;

    public List<State> findAll(){
        return repository.findAll();
    }
    public State findByid(Long id){
        return null;
    }
}
