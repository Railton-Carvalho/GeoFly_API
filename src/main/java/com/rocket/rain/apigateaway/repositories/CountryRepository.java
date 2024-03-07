package com.rocket.rain.apigateaway.repositories;

import com.rocket.rain.apigateaway.domain.Country;
import com.rocket.rain.apigateaway.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,String> {
    Page<Country> findAllByActiveTrue(Pageable pageable);
}
