package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.StateLink;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import com.rocket.rain.apigateaway.resources.StateResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.Optional;

@Service
public class StateService implements Serializable {

    @Autowired
    private StateRepository repository;

    public Page<RequestState> findAll(Pageable pageable){
        return repository.findAllByActiveTrue(pageable).map(response -> new RequestState(response,null));
    }
    public RequestState findStateByid(String id){
        Optional<State> state = repository.findById(id);

        if(state.isPresent()){
            StateLink stateLink = new StateLink();
            stateLink.add(linkTo(methodOn(StateResource.class).findStateById(id)).withSelfRel());
            return new RequestState(state.get(),stateLink);
        }
        return null;
    }
    public RequestState findByAcronym(String acronym){
        Optional<State> state = repository.findByAcronym(acronym);
        if (state.isPresent()){
            StateLink stateLink = new StateLink();
            stateLink.add(linkTo(methodOn(StateResource.class).findByAcronym(acronym)).withSelfRel());
            return new RequestState(state.get(),stateLink);
        }
        return null;
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
