package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.services.StateService;
import com.rocket.rain.apigateaway.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.Serializable;
import java.net.URI;
import java.util.Locale;


@RestController
@RequestMapping("/geo/states")
@Tag(name = "State", description = "EndPoints to Managing States")
public class StateResource implements Serializable {

    @Autowired
    StateService service;


    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds All States",description = "Find All Actives States",
                                            tags = {"States"},
                                            responses = {
                                                @ApiResponse(description = "Sucess", responseCode = "200",
                                                        content={
                                                            @Content(
                                                                    mediaType = "application/json",
                                                                    array =@ArraySchema(schema =
                                                                    @Schema(implementation = RequestState.class))
                                                            )
                                                        }),
                                                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                                @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)}

                                            )
    public ResponseEntity<Page<RequestState>>findAll(@PageableDefault(size = 3, sort = "name")Pageable pageable){
       Page<RequestState> listState = service.findAllByActiveTrue(pageable);
        return ResponseEntity.ok().body(listState);
    }

    @GetMapping(value = "/id/{id}",
            produces = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML})
    @Operation(summary = "Finds All States",description = "Find All Actives States",
            tags = {"States"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content={
                                    @Content(schema = @Schema(implementation = RequestState.class) )
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)}

    )
    public  ResponseEntity<RequestState> findStateById(@PathVariable String id){
        RequestState requestState = service.findStateByid(id);
        return ResponseEntity.ok().body(requestState);
    }
    @GetMapping(value = "/acronym/{acronym}",
                produces = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds States by id",description = "Find a State Actives States",
            tags = {"States"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content={
                                    @Content(schema = @Schema(implementation = RequestState.class) )
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)}

    )
    public ResponseEntity<RequestState> findByAcronym(@PathVariable String acronym){
        RequestState  requestState = service.findByAcronym(acronym);
        return ResponseEntity.ok().body(requestState);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON,//params for content negotiation
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML},

                produces = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML})
    @Transactional
    public ResponseEntity<RequestState> createState(@RequestBody @Valid RequestState requestState){
        requestState = service.createState(requestState);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(new State(requestState)).toUri();
        return ResponseEntity.created(uri).body(requestState);
    }

    @Transactional
    @PutMapping(consumes ={MediaType.APPLICATION_JSON,//params for content negotiation
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public ResponseEntity<UpdateState> updateState(@RequestBody @Valid UpdateState updateState){
        UpdateState stateDto = service.updateState(updateState);
        return ResponseEntity.ok().body(stateDto);
    }

    @DeleteMapping("/totalDelete/{id}")
    @Transactional
    public ResponseEntity<Void> totalState(@PathVariable String id){
        if (service.totalDelete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/logicalDelete/{id}")
    @Transactional
    public ResponseEntity<Void> logicalDelete(@PathVariable String id){
        if (service.logicalDelete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
