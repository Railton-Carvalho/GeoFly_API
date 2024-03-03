package com.rocket.rain.apigateaway.repositories;

import com.rocket.rain.apigateaway.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository <Region,String> {
}
