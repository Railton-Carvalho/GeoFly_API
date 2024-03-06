package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.domain.Country;
import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.dto.RequestCountry;
import com.rocket.rain.apigateaway.repositories.CountryRepository;
import com.rocket.rain.apigateaway.repositories.RegionRepository;
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

    @Autowired
    RegionRepository externalRepository;

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

    public RequestCountry createCountry(RequestCountry requestCountry) {
        return new RequestCountry(repository.save(new Country(requestCountry)));
    }

    public boolean insertRegion(String countryId, String regionId){
        Optional<Region> region = externalRepository.findById(regionId);
        if (region.isPresent()){
            Optional<Country> country = repository.findById(countryId);
            if (country.isPresent()){
                country.get().getRegions().add(region.get());
                region.get().setCountry(country.get());
                country.get().updateMetrics();
                return true;
            }
        }
        return false;
    }
    public void reloadAll(String id){
        Optional<Country> country = repository.findById(id);
        if (country.isPresent()){
            country.get().getRegions().forEach(region -> region.updateMetrics());
            country.get().updateMetrics();
        }

    }
}
