package com.xpand.challenge.service;

import java.time.LocalDate;
import java.util.List;

import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableMovieDTO;

public interface MovieService {
    
    IdentifiableMovieDTO createMovie(MovieDTO movieDTO);

    IdentifiableMovieDTO getMovie(Long id);

    List<IdentifiableMovieDTO> getMovies(int page, int size);

    List<IdentifiableMovieDTO> getMoviesByDate(LocalDate date);

    IdentifiableMovieDTO updateMovie(IdentifiableMovieDTO movieDTO);

    void deleteMovie(Long id);
}
