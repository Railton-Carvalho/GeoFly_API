package com.rocket.rain.apigateaway.resources;

import com.rocket.rain.apigateaway.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/geo/regions")
public class RegionResource implements Serializable {
    @Autowired
    RegionService service;
}
