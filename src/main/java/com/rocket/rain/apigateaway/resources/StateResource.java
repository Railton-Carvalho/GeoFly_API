package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/geo/states")
public class StateResource implements Serializable {

    @Autowired
    StateService service;

    @GetMapping
    public ResponseEntity<List<RequestState>>findAll(){
        List<RequestState> listState = service.findAll();
        return ResponseEntity.ok().body(listState);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<RequestState> findById(@PathVariable String id){
        RequestState requestState = service.findStateByid(id);
        return ResponseEntity.ok().body(requestState);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UpdateState> createState(@RequestBody UpdateState updateState){
        updateState = service.createState(updateState);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(new State(updateState)).toUri();
        return ResponseEntity.created(uri).body(updateState);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteState(@PathVariable String id){
        service.deleteState(id);
        return ResponseEntity.noContent().build();
    }
}
