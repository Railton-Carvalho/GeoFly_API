package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.List;

@RequestMapping("geo/states")
public class StateResource implements Serializable {

    @Autowired
    StateService service;

    @GetMapping
    public ResponseEntity<List<State>>findAll(){
        List<State> listState = service.findAll();
        return ResponseEntity.ok().body(listState);
    }
}
