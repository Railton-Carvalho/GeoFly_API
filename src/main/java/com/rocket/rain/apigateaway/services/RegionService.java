package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestRegion;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.repositories.RegionRepository;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class RegionService implements Serializable {
    @Autowired
    RegionRepository repository;

    @Autowired
    StateRepository externalRepository;

    public Page<RequestRegion> findAll(Pageable pageable){
        return repository.findAll(pageable).map(RequestRegion::new);
    }
    public RequestRegion findRegionById(String id){
        Optional<Region> region = repository.findById(id);
        if (region.isPresent()){
            return new RequestRegion(region.get());
        }
        return null;
    }

    public RequestRegion createRegion(RequestRegion requestRegion){
        return new RequestRegion(repository.save(new Region((requestRegion))));
    }
    public boolean insertState(String regionId, String stateId){
        Optional<State> state = externalRepository.findById(stateId);
        if(state.isPresent()){
            Optional<Region> region = repository.findById(regionId);
            if (region.isPresent()){
                state.get().setRegion(region.get());
                region.get().getStates().add(state.get());
                region.get().updateMetrics();
                region.get().getCountry().updateMetrics();
                //repository.save(region.get());
                return true;
            }
        }
        return false;
    }
    public List<RequestState> showStates(String id){
        //var states = externalRepository.findByRegionId(id).stream().map(RequestState::new).toList();
        Optional<Region> region = repository.findById(id);
        List<RequestState> states = new ArrayList<>();
        if(region.isPresent()){
            System.out.println(region.get().getStates());
            for (State state: region.get().getStates()){
                states.add(new RequestState(state));
            }
            return states;
        }
        return null;
    }
    public void totalDelete(String id){
        Optional<Region> region = repository.findById(id);
        if (region.isPresent()){
            repository.delete(region.get());
        }

    }
    public void logicalDelete(String id){
        Optional<Region> region = repository.findById(id);
        if (region.isPresent()){
            //implements later
        }

    }


}
