package com.xpand.challenge.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableMovieDTO;
import com.xpand.challenge.dto.mapper.MovieDTOMapper;
import com.xpand.challenge.repository.MovieRepository;
import com.xpand.challenge.service.MovieService;
import com.xpand.challenge.validator.Validator;

@Service
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;
    private final Validator<MovieDTO> movieValidator;

    public DefaultMovieService(MovieRepository movieRepository, Validator<MovieDTO> movieValidator) {
        this.movieRepository = movieRepository;
        this.movieValidator = movieValidator;
    }

    @Override
    public IdentifiableMovieDTO createMovie(MovieDTO movieDTO) {
        movieValidator.validate(movieDTO);
        return MovieDTOMapper.toIdentifiableMovieDTO(movieRepository.save(MovieDTOMapper.fromMovieDTO(movieDTO)));
    }

    @Override
    public IdentifiableMovieDTO getMovie(Long id) {
        return movieRepository.findById(id).map(MovieDTOMapper::toIdentifiableMovieDTO).orElseThrow(() -> new NoSuchElementException(ChallengeConstants.MESSAGE_MOVIE_NOT_FOUND));
    }

    @Override
    public List<IdentifiableMovieDTO> getMovies(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size)).getContent().stream().map(MovieDTOMapper::toIdentifiableMovieDTO).collect(Collectors.toList());
    }

    @Override
    public List<IdentifiableMovieDTO> getMoviesByDate(LocalDate date) {
        return movieRepository.findByDate(date).stream().map(MovieDTOMapper::toIdentifiableMovieDTO).collect(Collectors.toList());
    }

    @Override
    public IdentifiableMovieDTO updateMovie(IdentifiableMovieDTO movieDTO) {
        movieRepository.findById(movieDTO.getId()).orElseThrow(() -> new NoSuchElementException(ChallengeConstants.MESSAGE_MOVIE_NOT_FOUND));
        movieValidator.validate(movieDTO);
        return MovieDTOMapper.toIdentifiableMovieDTO(movieRepository.save(MovieDTOMapper.fromIdentifiableMovieDTO(movieDTO)));
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    
}
