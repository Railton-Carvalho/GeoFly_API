package com.rocket.rain.apigateaway.unittests.mockito.services;

import com.rocket.rain.apigateaway.domain.State;
import com.rocket.rain.apigateaway.dto.RequestState;
import com.rocket.rain.apigateaway.dto.UpdateState;
import com.rocket.rain.apigateaway.repositories.StateRepository;
import com.rocket.rain.apigateaway.services.StateService;
import com.rocket.rain.apigateaway.services.exceptions.RequiredObjectIsNullException;
import com.rocket.rain.apigateaway.unittests.mockito.mapper.MockState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("findState was sucessfull")
    void findStateByid() {
        //simulando uma entity do banco
         State state = input.mockEntity(1);
         state.setId("1");

         //comportamento do findById no banco simulado
         when(repository.findById("1")).thenReturn(Optional.of(state));

         RequestState stateOne = service.findStateByid("1");
         assertNotNull(stateOne);
         assertNotNull(stateOne.name());
         assertNotNull(stateOne.links());
         assertTrue(stateOne.toString().contains("</geo/states/id/1>;rel=\"self\"]"));

         assertEquals("First Name Test1",stateOne.name());
         assertEquals("Capital Test1",stateOne.capital());
         assertEquals("Acronym Test1",stateOne.acronym());
    }

    @Test
    void createState() {
        //simulando uma entity recebida no RequestBody
        State entity = input.mockEntity(1);
        //simulando a entidade dps que o repository for chamado

        State persisted = input.mockEntity(1);
        persisted.setId("1");

        //simulando o casting de EntityToDto
        RequestState requestState = input.mockDto(1);

        when(repository.save(any(State.class))).thenReturn(persisted);


        var stateOne = service.createState(requestState);
//        State stateOne1 = repository.save(entity);
//        DtoLink link = new DtoLink();
//        link.add(linkTo(methodOn(StateResource.class).findStateById(stateOne1.getId())).withSelfRel());
//
//        RequestState stateOne = new RequestState(stateOne1,link);
        assertNotNull(stateOne);
        assertNotNull(stateOne.name());
        assertNotNull(stateOne.links());
        assertTrue(stateOne.toString().contains("</geo/states/id/1>;rel=\"self\"]"));

        assertEquals("First Name Test1",stateOne.name());
        assertEquals("Capital Test1",stateOne.capital());
        assertEquals("Acronym Test1",stateOne.acronym());
    }
    @Test
    @DisplayName("Null Create State")
    void createNullState() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()->{
            service.createState(null);
        });
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void updateState(){
        State entity = input.mockEntity(1);
        entity.setId("1");

        UpdateState vo = input.mockUpdateDto(1);

        when(repository.findById("1")).thenReturn(Optional.of(entity));

        UpdateState stateOne = service.updateState(vo);

        assertNotNull(stateOne);
        assertNotNull(stateOne.id());
        assertNotNull(stateOne.name());

        assertEquals("First Name Test1",stateOne.name());
        assertEquals("Capital Test1",stateOne.capital());
        assertEquals("Acronym Test1",stateOne.acronym());
    }
    @Test
    @DisplayName("Null Update State")
    void updateNullState(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()->{
            service.updateState(null);
        });
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    @Test
    void findAll() {
        List<State> list = input.mockEntityList();

        PageRequest pageRequest = PageRequest.of(0, 114);

        // Converta a lista em uma página usando o PageImpl
        Page<State> page = new PageImpl<>(list, pageRequest, list.size());


        when(repository.findAllByActiveTrue(page.getPageable())).thenReturn(page);

        var states = service.findAllByActiveTrue(page.getPageable());

        assertNotNull(states);
        assertEquals(14, states.getTotalElements());

        int i = 0;
        for(RequestState requestState:states.getContent()){
            assertNotNull(requestState);
            assertNotNull(requestState.name());
            assertNotNull(requestState.links());

            assertTrue(requestState.toString().contains("</geo/states/id/"+i+">;rel=\"self\"]"));

            assertEquals("First Name Test"+i, requestState.name());
            assertEquals("Capital Test"+i, requestState.capital());
            assertEquals("Acronym Test"+i, requestState.acronym());
            i++;
        }

    }
    @Test
    void findByAcronym() {
    }

    @Test
    void totalDelete() {
    }

    @Test
    void logicalDelete() {
    }
}