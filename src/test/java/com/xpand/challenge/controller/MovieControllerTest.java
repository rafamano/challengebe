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
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableMovieDTO;
import com.xpand.challenge.service.MovieService;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;

    private List<IdentifiableMovieDTO> movieList;

    private IdentifiableMovieDTO movie;

    @BeforeEach
    void setUp() {
        movieList = new ArrayList<>();
        movie = new IdentifiableMovieDTO(1L, "Movie 1", LocalDate.of(1972, 3, 24), 1.5f, null);
        movieList.add(movie);
    }

    @Test
    void testGetMovies() throws Exception {
        Mockito.when(movieService.getMovies(1, 10)).thenReturn(movieList);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies?page=1&size=10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value("Movie 1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetMovie() throws Exception {
        Mockito.when(movieService.getMovie(1L)).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Movie 1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetMoviesByDate() throws Exception {
        Mockito.when(movieService.getMoviesByDate(LocalDate.of(1972, 3, 24))).thenReturn(movieList);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/date?date=1972-03-24"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value("Movie 1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testCreateMovie() throws Exception {
    	MovieDTO movieDTO = new MovieDTO("Movie 2", LocalDate.of(1972, 3, 24), 1.5f, null);;

    	IdentifiableMovieDTO createdMovie = new IdentifiableMovieDTO(1L, "Movie 2", LocalDate.of(2019, 4, 26), 1.5f, null);
        Mockito.when(movieService.createMovie(Mockito.any(MovieDTO.class))).thenReturn(createdMovie);

        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Movie 2"))
                .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    void testUpdateMovie() throws Exception {
    	IdentifiableMovieDTO movieDTO = new IdentifiableMovieDTO(5L,"Movie 5", LocalDate.of(2019, 4, 26), 4.5f, null);
    	IdentifiableMovieDTO updatedMovie = new IdentifiableMovieDTO(5L, "Movie 2", LocalDate.of(2019, 4, 26), 4.5f, null);
        Mockito.when(movieService.updateMovie(Mockito.any(IdentifiableMovieDTO.class))).thenReturn(updatedMovie);

        mockMvc.perform(MockMvcRequestBuilders.put("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Movie 2"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testDeleteMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}
