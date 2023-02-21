package com.xpand.challenge.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpand.challenge.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	Set<Movie> findByDate(LocalDate date);
    
}
