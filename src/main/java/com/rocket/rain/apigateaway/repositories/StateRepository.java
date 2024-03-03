package com.rocket.rain.apigateaway.repositories;

import com.rocket.rain.apigateaway.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State,Long> {
    State findById(String id);

    State findByAcronym(String acronym);
}
