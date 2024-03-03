package com.rocket.rain.apigateaway.services;

import com.rocket.rain.apigateaway.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RegionService implements Serializable {
    @Autowired
    RegionRepository repository;

}
