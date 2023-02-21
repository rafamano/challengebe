package com.xpand.challenge.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableActorDTO;
import com.xpand.challenge.service.ActorService;

@WebMvcTest(ActorController.class)
class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActorService actorService;

    private List<IdentifiableActorDTO> actorList;

    private IdentifiableActorDTO actor;

    @BeforeEach
    void setUp() {
        actorList = new ArrayList<>();
        actor = new IdentifiableActorDTO(1L, "Actor 1", LocalDate.of(1972, 3, 24), ChallengeConstants.GENDER_TYPE_MALE, 1L);
        actorList.add(actor);
    }

    @Test
    void testGetActors() throws Exception {
        Mockito.when(actorService.getActors(1, 10)).thenReturn(actorList);

        mockMvc.perform(MockMvcRequestBuilders.get("/actors?page=1&size=10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Actor 1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetActor() throws Exception {
        Mockito.when(actorService.getActor(1L)).thenReturn(actor);

        mockMvc.perform(MockMvcRequestBuilders.get("/actors/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Actor 1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetActorsByMovieId() throws Exception {
        Mockito.when(actorService.getActorsByMovieId(1L)).thenReturn(actorList);

        mockMvc.perform(MockMvcRequestBuilders.get("/actors/movie/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Actor 1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testCreateActor() throws Exception {
    	ActorDTO actorDTO = new ActorDTO("Actor 2", LocalDate.of(2019, 4, 26), ChallengeConstants.GENDER_TYPE_FEMALE, 3L);

    	IdentifiableActorDTO createdActor = new IdentifiableActorDTO(1L, "Actor 2", LocalDate.of(2019, 4, 26), ChallengeConstants.GENDER_TYPE_FEMALE, 3L);
        Mockito.when(actorService.createActor(Mockito.any(ActorDTO.class))).thenReturn(createdActor);

        mockMvc.perform(MockMvcRequestBuilders.post("/actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actorDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Actor 2"))
                .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    void testUpdateActor() throws Exception {
    	IdentifiableActorDTO actorDTO = new IdentifiableActorDTO(5L,"Actor 5", LocalDate.of(2019, 4, 26), ChallengeConstants.GENDER_TYPE_FEMALE, 5L);
    	IdentifiableActorDTO updatedActor = new IdentifiableActorDTO(5L, "Actor 2", LocalDate.of(2019, 4, 26), ChallengeConstants.GENDER_TYPE_FEMALE, 5L);
        Mockito.when(actorService.updateActor(Mockito.any(IdentifiableActorDTO.class))).thenReturn(updatedActor);

        mockMvc.perform(MockMvcRequestBuilders.put("/actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actorDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Actor 2"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testDeleteActor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/actors/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}
