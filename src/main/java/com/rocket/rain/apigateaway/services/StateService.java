package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class StateService implements Serializable {

    @Autowired
    private StateRepository repository;

    public Page<RequestState> findAll(Pageable pageable){
        return repository.findAllByActiveTrue(pageable).map(RequestState::new);
    }
    public RequestState findStateByid(String id){
        Optional<State> state = repository.findById(id);
        if(state.isPresent()){
            return new RequestState(state.get());
        }
        return null;
    }
    public RequestState findByAcronym(String acronym){
        return new RequestState(repository.findByAcronym(acronym));
    }
    public UpdateState createState(UpdateState state){
        return new UpdateState(repository.save(new State(state)));
    }

    public boolean totalDelete(String id){
        Optional<State> state = repository.findById(id);
        if(state.isPresent()){
            repository.delete(state.get());
            return true;
        }
        return false;
    }
    public boolean logicalDelete(String id){
        Optional<State> state = repository.findById(id);
        if (state.isPresent()){
            state.get().setActive(false);
            return true;
        }
        return false;
    }
}
