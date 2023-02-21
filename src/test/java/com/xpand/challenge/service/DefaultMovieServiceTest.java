package com.xpand.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableMovieDTO;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.repository.MovieRepository;
import com.xpand.challenge.service.impl.DefaultMovieService;
import com.xpand.challenge.validator.Validator;

class DefaultMovieServiceTest {

    private DefaultMovieService movieService;

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private Validator<MovieDTO> movieValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieService = new DefaultMovieService(movieRepository, movieValidator);
    }

    @Test
    void testCreateMovie() {
    	LocalDate date = LocalDate.now();
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle("Test Movie");
        movieDTO.setDate(date);
        movieDTO.setRank(6.7f);
        Mockito.when(movieRepository.save(Mockito.any())).thenReturn(new Movie(1L, "Test Movie", date, 1.7f, null, null));
        IdentifiableMovieDTO result = movieService.createMovie(movieDTO);
        assertNotNull(result);
        assertEquals("Test Movie", result.getTitle());
    }

    @Test
    void testGetMovie() {
        Mockito.when(movieRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Movie()));
        IdentifiableMovieDTO result = movieService.getMovie(1L);
        assertNotNull(result);
    }

    @SuppressWarnings("unchecked")
	@Test
    void testGetMovies() {
    	Page<Movie> moviePage = Mockito.mock(Page.class);
    	List<Movie> movieList = List.of(new Movie(1L, "Test Movie", LocalDate.now(), 1.7f, null, null));
    	Mockito.when(moviePage.getContent()).thenReturn(movieList);
    	Mockito.when(movieRepository.findAll(PageRequest.of(0, 10))).thenReturn(moviePage);

    	List<IdentifiableMovieDTO> result = movieService.getMovies(0, 10);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetMoviesByDate() {
        Mockito.when(movieRepository.findByDate(Mockito.any())).thenReturn(Set.of(new Movie()));
        List<IdentifiableMovieDTO> result = movieService.getMoviesByDate(LocalDate.now());
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateMovie() {
    	LocalDate date = LocalDate.now();
        IdentifiableMovieDTO movieDTO = new IdentifiableMovieDTO();
        movieDTO.setId(1L);
        movieDTO.setTitle("Test Movie");
        movieDTO.setDate(date);
        movieDTO.setRank(1.7f);
        Mockito.when(movieRepository.findById(1L)).thenReturn(Optional.of(new Movie(1L, "Test Movie", date, 1.7f, null, null)));
        Mockito.when(movieRepository.save(Mockito.any())).thenReturn(new Movie(1L, "Test Movie Updated", date, 1.7f, null, null));
        IdentifiableMovieDTO result = movieService.updateMovie(movieDTO);
        assertNotNull(result);
        assertEquals("Test Movie Updated", result.getTitle());
    }

    @Test
    void testUpdateMovieNotFound() {
        IdentifiableMovieDTO movieDTO = new IdentifiableMovieDTO();
        movieDTO.setId(1L);
        assertThrows(NoSuchElementException.class, () -> movieService.updateMovie(movieDTO));
    }

    @Test
    void testDeleteMovie() {
        movieService.deleteMovie(1L);
        Mockito.verify(movieRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

}
