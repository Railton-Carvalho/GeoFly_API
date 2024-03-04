package com.rocket.rain.apigateaway.repositories;

import com.rocket.rain.apigateaway.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State,Long> {
    Optional<State> findById(String id);

    State findByAcronym(String acronym);
}
