package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.dto.RequestCountry;
import com.rocket.rain.apigateaway.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.lang.annotation.Repeatable;

@RestController
@RequestMapping("/geo/country")
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

}
