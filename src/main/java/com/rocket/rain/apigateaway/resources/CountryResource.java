package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.domain.Country;
import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.dto.RequestCountry;
import com.rocket.rain.apigateaway.dto.RequestRegion;
import com.rocket.rain.apigateaway.services.CountryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.lang.annotation.Repeatable;
import java.net.URI;

@RestController
@RequestMapping("/geo/countries")
@Tag(name = "Country", description = "EndPoints to Managing Country")
public class CountryResource implements Serializable {
    @Autowired
    CountryService service;

    @GetMapping
    public ResponseEntity<Page<RequestCountry>> findAll(Pageable pageable){
        Page<RequestCountry> countries = service.findAll(pageable);
        return ResponseEntity.ok().body(countries);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RequestCountry> findCountryById(@PathVariable String id){
        RequestCountry requestCountry = service.findCountryById(id);
        return ResponseEntity.ok().body(requestCountry);

    }
    @PostMapping
    @Transactional
    public ResponseEntity<RequestCountry> createCountry(@RequestBody RequestCountry requestCountry){
        requestCountry = service.createCountry(requestCountry);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(new Country(requestCountry)).toUri();
        return ResponseEntity.created(uri).body(requestCountry);
    }

    @PatchMapping("/insertRegion/{countryId}/{regionId}")
    @Transactional
    public ResponseEntity<String> insertRegion(@PathVariable(value = "countryId") String countryId,
                                               @PathVariable(value = "regionId") String regionId){
        if (service.insertRegion(countryId,regionId)){
            return ResponseEntity.ok().body("Regions Upgraded !");
        }
        return ResponseEntity.ok().body("Error !");
    }
    @PutMapping("/reloadAll/{id}")
    @Transactional
    public ResponseEntity<String> reloadAll(@PathVariable String id){
        service.reloadAll(id);
        return ResponseEntity.ok().body("Data Reloaded!");
    }
}
