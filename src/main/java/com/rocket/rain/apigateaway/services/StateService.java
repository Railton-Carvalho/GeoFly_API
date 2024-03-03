package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class StateService implements Serializable {

    @Autowired
    private StateRepository repository;

    public List<RequestState> findAll(){
        return repository.findAll().stream().map(RequestState::new).toList();
    }
    public RequestState findStateByid(String id){
        return new RequestState(repository.findById(id));
    }
    public UpdateState createState(UpdateState state){
        return new UpdateState(repository.save(new State(state)));
    }

    public void deleteState(String id){
        State state = repository.findById(id);
        repository.delete(state);
    }
}
