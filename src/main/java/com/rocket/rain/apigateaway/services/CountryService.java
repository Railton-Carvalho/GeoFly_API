package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.Country;
import com.rocket.rain.apigateaway.dto.RequestCountry;
import com.rocket.rain.apigateaway.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Optional;

@Service
public class CountryService implements Serializable {

    @Autowired
    CountryRepository repository;

    public Page<RequestCountry> findAll(Pageable pageable){
        Page<RequestCountry> pages = repository.findAll(pageable).map(RequestCountry::new);
        return pages;
    }
    public RequestCountry findCountryById(String id){
        Optional<Country> country = repository.findById(id);
        if (country.isPresent()){
            return new RequestCountry(country.get());
        }
        return null;
    }
}
