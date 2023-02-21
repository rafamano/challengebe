package com.xpand.challenge.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpand.challenge.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
	
	Set<Actor> findByMovieId(Long id);
    
}
