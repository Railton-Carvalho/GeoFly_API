package com.rocket.rain.apigateaway.repositories;

import com.rocket.rain.apigateaway.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StateRepository extends JpaRepository<State,String> {
    Optional<State> findById(String id);

    Page<State> findAllByActiveTrue(Pageable pageable);
    Optional<State> findByAcronym(String acronym);

    List<State> findByRegionId(String id);
}
