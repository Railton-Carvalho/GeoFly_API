package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/geo/states")
public class StateResource implements Serializable {

    @Autowired
    StateService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RequestState>>findAll(@PageableDefault(size = 3, sort = "name")Pageable pageable){
       Page<RequestState> listState = service.findAll(pageable);
        return ResponseEntity.ok().body(listState);
    }

    @GetMapping(value = "/id/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE})
    public  ResponseEntity<RequestState> findStateById(@PathVariable String id){
        RequestState requestState = service.findStateByid(id);
        return ResponseEntity.ok().body(requestState);
    }
    @GetMapping(value = "/acronym/{acronym}",
                produces = {MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RequestState> findByAcronym(@PathVariable String acronym){
        RequestState  requestState = service.findByAcronym(acronym);
        return ResponseEntity.ok().body(requestState);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,//params for content negotiation
                            MediaType.APPLICATION_XML_VALUE},

                produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
    @Transactional
    public ResponseEntity<UpdateState> createState(@RequestBody UpdateState updateState){
        updateState = service.createState(updateState);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(new State(updateState)).toUri();
        return ResponseEntity.created(uri).body(updateState);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> totalState(@PathVariable String id){
        if (service.totalDelete(id)){
            return ResponseEntity.noContent().build();
        }
        return null;
    }
    @DeleteMapping("/logicalDelete/{id}")
    @Transactional
    public ResponseEntity<Void> logicalDelete(@PathVariable String id){
        if (service.logicalDelete(id)){
            return ResponseEntity.noContent().build();
        }
        return null;
    }
}
