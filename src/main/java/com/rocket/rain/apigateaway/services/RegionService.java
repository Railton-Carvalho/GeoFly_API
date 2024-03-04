package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestRegion;
import com.rocket.rain.apigateaway.repositories.RegionRepository;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;


@Service
public class RegionService implements Serializable {
    @Autowired
    RegionRepository repository;

    @Autowired
    StateRepository externalRepository;

    public Page<RequestRegion> findAll(Pageable pageable){
        return repository.findAll(pageable).map(RequestRegion::new);
    }

    public RequestRegion createRegion(RequestRegion requestRegion){
        return new RequestRegion(repository.save(new Region((requestRegion))));
    }

    public boolean insertState(String regionId, String stateId){
        Optional<State> state = externalRepository.findById(stateId);
        if(state.isPresent()){
            Optional<Region> region = repository.findById(regionId);
            region.get().getStates().add(state.get());
            region.get().updatePopulation();
            return true;
        }
        return false;
    }

}
