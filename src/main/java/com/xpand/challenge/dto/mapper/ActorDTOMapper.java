package com.xpand.challenge.dto.mapper;

import java.util.Optional;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableActorDTO;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;

public class ActorDTOMapper {

    public static IdentifiableActorDTO toIdentifiableActorDTO(Actor actor) {
        return Optional.ofNullable(actor).map(m -> {
            IdentifiableActorDTO dto = new IdentifiableActorDTO();
            dto.setId(actor.getId());
            dto.setName(actor.getName());
            dto.setBdate(actor.getBdate());
            dto.setGender(actor.getGender());
            if(actor.getMovie() != null)
            	dto.setMovieId(actor.getMovie().getId());
            return dto;
        }).orElse(null);
    }
    
    public static Actor fromIdentifiableActorDTO(IdentifiableActorDTO dto, Movie movie) {
    	return Optional.ofNullable(dto).map(d -> {
    		Actor actor = new Actor();
    		actor.setId(dto.getId());
    		actor.setName(dto.getName());
    		actor.setBdate(dto.getBdate());
    		actor.setGender(dto.getGender());
    		actor.setMovie(movie);
    		return actor;
    	}).orElse(null);
    }
    
    public static Actor fromActorDTO(ActorDTO dto, Movie movie) {
    	return Optional.ofNullable(dto).map(d -> {
    		Actor actor = new Actor();
    		actor.setName(dto.getName());
    		actor.setBdate(dto.getBdate());
    		actor.setGender(dto.getGender());
    		actor.setMovie(movie);
    		return actor;
    	}).orElse(null);
    }

    private ActorDTOMapper() {}
}
