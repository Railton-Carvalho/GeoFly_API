package com.rocket.rain.apigateaway.repositories;

import com.rocket.rain.apigateaway.domain.Region;
import com.rocket.rain.apigateaway.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository <Region,String> {
    Optional<Region> findById(String id);
}
