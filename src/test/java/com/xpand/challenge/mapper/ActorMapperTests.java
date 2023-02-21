package com.xpand.challenge.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableActorDTO;
import com.xpand.challenge.dto.mapper.ActorDTOMapper;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;

class ActorMapperTests {


    @Test
    void doTestfromActorDTO() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Actor Name");
        dto.setBdate(LocalDate.now());
        dto.setGender(ChallengeConstants.GENDER_TYPE_FEMALE);
        dto.setMovieId(1L);
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("title");
        movie.setRank(5f);
        movie.setDate(LocalDate.now());
        movie.setRevenue(new BigDecimal(100000));
        Actor actor = ActorDTOMapper.fromActorDTO(dto, movie);
        assertNotNull(actor);
        assertEquals(dto.getName(), actor.getName());
        assertEquals(dto.getGender(), actor.getGender());
        assertEquals(dto.getBdate(), actor.getBdate());
        assertEquals(dto.getMovieId(), actor.getMovie().getId());
    }

    @Test
    void doTestToIdentifiableActorDTO() {
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("title");
        movie.setRank(5f);
        movie.setDate(LocalDate.now());
        movie.setRevenue(new BigDecimal(100000));
        Actor actor = new Actor();
        actor.setId(1L);
        actor.setName("Actor Name");
        actor.setBdate(LocalDate.now());
        actor.setGender(ChallengeConstants.GENDER_TYPE_FEMALE);
        actor.setMovie(movie);
        IdentifiableActorDTO dto = ActorDTOMapper.toIdentifiableActorDTO(actor);
        assertNotNull(dto);
        assertEquals(actor.getId(), dto.getId());
        assertEquals(actor.getName(), dto.getName());
        assertEquals(actor.getGender(), dto.getGender());
        assertEquals(actor.getBdate(), dto.getBdate());
        assertEquals(actor.getMovie().getId(), dto.getMovieId());
    }
    
    @Test
    void doTestFromIdentifiableActorDTO() {
        IdentifiableActorDTO dto = new IdentifiableActorDTO();
        dto.setId(2L);
        dto.setName("Actor Name");
        dto.setBdate(LocalDate.now());
        dto.setGender(ChallengeConstants.GENDER_TYPE_FEMALE);
        dto.setMovieId(1L);
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("title");
        movie.setRank(5f);
        movie.setDate(LocalDate.now());
        movie.setRevenue(new BigDecimal(100000));
        Actor actor = ActorDTOMapper.fromIdentifiableActorDTO(dto,movie);
        assertNotNull(actor);
        assertEquals(dto.getId(), actor.getId());
        assertEquals(dto.getName(), actor.getName());
        assertEquals(dto.getGender(), actor.getGender());
        assertEquals(dto.getBdate(), actor.getBdate());
        assertEquals(dto.getMovieId(), actor.getMovie().getId());
    }

    @Test
    void doTestNull() {
        assertNull(ActorDTOMapper.fromActorDTO(null, null));
        assertNull(ActorDTOMapper.toIdentifiableActorDTO(null));
    }

}
