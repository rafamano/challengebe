package com.xpand.challenge.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableMovieDTO;
import com.xpand.challenge.service.MovieService;

@RestController
@RequestMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<IdentifiableMovieDTO>> getMovies(
    		@RequestParam(value = "page", defaultValue = ChallengeConstants.DEFAULT_PAGE_VALUE, required = false) int page, 
    		@RequestParam(value = "size", defaultValue = ChallengeConstants.DEFAULT_PAGE_SIZE_VALUE, required = false) int size) {
        return ResponseEntity.ok().body(movieService.getMovies(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentifiableMovieDTO> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.getMovie(id));
    }

    @GetMapping("/date")
    public ResponseEntity<List<IdentifiableMovieDTO>> getMoviesByDate(@RequestParam(required = true, value= "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok().body(movieService.getMoviesByDate(date));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdentifiableMovieDTO> createMovie(@RequestBody @Valid MovieDTO movieDTO) {
        return ResponseEntity.ok().body(movieService.createMovie(movieDTO));
    }

    @PutMapping
    public ResponseEntity<IdentifiableMovieDTO> updateMovie(@RequestBody @Valid IdentifiableMovieDTO movieDTO) {
        return ResponseEntity.ok().body(movieService.updateMovie(movieDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}


