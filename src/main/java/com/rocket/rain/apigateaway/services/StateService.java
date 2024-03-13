package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.dto.link.DtoLink;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import com.rocket.rain.apigateaway.resources.StateResource;
import com.rocket.rain.apigateaway.services.exceptions.RequiredObjectIsNullException;
import com.rocket.rain.apigateaway.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodWrapper;
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
        //adicionando links em todos os DTOS
        Page<RequestState> states = repository.findAllByActiveTrue(pageable).map(
                response -> new RequestState(
                        response,new DtoLink().add(linkTo(methodOn(StateResource.class).findStateById(response.getId())).withSelfRel()))
        );

        return states;
    }
    public RequestState findStateByid(String id){
        State state = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        DtoLink dtoLink = new DtoLink();
        dtoLink.add(linkTo(methodOn(StateResource.class).findStateById(id)).withSelfRel());
        return new RequestState(state, dtoLink);
    }
    public RequestState findByAcronym(String acronym){
        State state = repository.findByAcronym(acronym).orElseThrow(() -> new ResourceNotFoundException(acronym));
        DtoLink dtoLink = new DtoLink();
        dtoLink.add(linkTo(methodOn(StateResource.class).findByAcronym(acronym)).withSelfRel());
        return new RequestState(state, dtoLink);
    }
    public RequestState createState(RequestState state){
        if (state == null) throw new RequiredObjectIsNullException();
        DtoLink dtoLink = new DtoLink();
        State entity = repository.save(new State(state));
        dtoLink.add(linkTo(methodOn(StateResource.class).findStateById(entity.getId())).withSelfRel());
        return new RequestState(entity, dtoLink);
    }

    public UpdateState updateState(UpdateState requestState){
        if (requestState == null) throw new RequiredObjectIsNullException();
        State state =
                repository.findById(requestState.id()).orElseThrow(()-> new ResourceNotFoundException(requestState.id()));
        state.setPopulation(requestState.population());
        state.setArea(requestState.area());
        state.setArea(requestState.area());
        state.setCapital(requestState.capital());
        return new UpdateState(state);


    }
    public boolean totalDelete(String id){
        State state = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        repository.delete(state);
        return true;
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
