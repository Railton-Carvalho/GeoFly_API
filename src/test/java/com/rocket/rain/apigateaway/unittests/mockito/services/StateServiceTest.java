package com.rocket.rain.apigateaway.unittests.mockito.services;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import com.rocket.rain.apigateaway.services.StateService;
import com.rocket.rain.apigateaway.unittests.mockito.mapper.MockState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class StateServiceTest {

    MockState input;

    @InjectMocks
    private StateService service;

    @Mock
    StateRepository repository;
    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockState();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findStateByid() {
         State state = input.mockEntity(1);
         state.setId("1");
         when(repository.findById("1")).thenReturn(Optional.of(state));

         RequestState result = service.findStateByid("1");
         assertNotNull(result);
         assertNotNull(result.name());
         assertNotNull(result.links());
         assertTrue(result.toString().contains("</geo/states/id/1>;rel=\"self\"]"));

         assertEquals("First Name Test1",result.name());
         assertEquals("Capital Test1",result.capital());
         assertEquals("Acronym Test1",result.acronym());
    }

    @Test
    void findAll() {
    }
    @Test
    void findByAcronym() {
    }

    @Test
    void createState() {
    }

    @Test
    void totalDelete() {
    }

    @Test
    void logicalDelete() {
    }
}