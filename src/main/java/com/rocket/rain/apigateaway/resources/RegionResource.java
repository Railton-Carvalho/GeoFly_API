package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.dto.RequestRegion;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/geo/regions")
public class RegionResource implements Serializable {
    @Autowired
    RegionService service;

    @GetMapping
    public ResponseEntity<Page<RequestRegion>> findAll(@PageableDefault(size = 2) Pageable pageable){
        Page<RequestRegion> regions = service.findAll(pageable);
        return ResponseEntity.ok().body(regions);
    }
    @GetMapping("/showStates/{id}")
    @Transactional
    public ResponseEntity<List<RequestState>> showStates(@PathVariable String id){
        List<RequestState> states = service.showStates(id);
        return ResponseEntity.ok().body(states);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RequestRegion> createRegion(@RequestBody RequestRegion requestRegion){
        requestRegion = service.createRegion(requestRegion);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(new Region(requestRegion)).toUri();
        return ResponseEntity.created(uri).body(requestRegion);
    }

    @PutMapping("insertState/{regionId}/{stateId}")
    @Transactional
    public ResponseEntity<String> insertState(@PathVariable(value = "regionId") String regionId,
                                                   @PathVariable(value = "stateId") String stateId){
        if(service.insertState(regionId, stateId)){
            return ResponseEntity.ok().body("States Upgraded !");
        }
        return ResponseEntity.ok().body("Unsucefull Operation !");

    }
    @DeleteMapping("/totalDelete/{id}")
    @Transactional
    public ResponseEntity<Void> totalDelete(@PathVariable String id){
        service.totalDelete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logicalDelete/{id}")
    @Transactional
    public ResponseEntity<Void> logicalDelete(@PathVariable String id){
        service.logicalDelete(id);
        return ResponseEntity.noContent().build();
    }

}
