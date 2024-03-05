package com.rocket.rain.apigateaway.repositories;

import com.rocket.rain.apigateaway.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,String> {
}
