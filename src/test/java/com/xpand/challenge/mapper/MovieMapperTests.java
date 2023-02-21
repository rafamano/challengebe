package com.xpand.challenge.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableMovieDTO;
import com.xpand.challenge.dto.mapper.MovieDTOMapper;
import com.xpand.challenge.model.Movie;

class MovieMapperTests {


    @Test
    void doTestfromMovieDTO() {
        MovieDTO dto = new MovieDTO();
        dto.setTitle("title");
        dto.setRank(5f);
        dto.setDate(LocalDate.now());
        dto.setRevenue(new BigDecimal(100000));
        Movie movie = MovieDTOMapper.fromMovieDTO(dto);
        assertNotNull(movie);
        assertEquals(dto.getTitle(), movie.getTitle());
        assertEquals(dto.getRank(), movie.getRank());
        assertEquals(dto.getDate(), movie.getDate());
        assertEquals(dto.getRevenue(), movie.getRevenue());
    }

    @Test
    void doTestToIdentifiableMovieDTO() {
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setTitle("title");
        movie.setRank(5f);
        movie.setDate(LocalDate.now());
        movie.setRevenue(new BigDecimal(100000));
        IdentifiableMovieDTO dto = MovieDTOMapper.toIdentifiableMovieDTO(movie);
        assertNotNull(dto);
        assertEquals(movie.getId(), dto.getId());
        assertEquals(movie.getTitle(), dto.getTitle());
        assertEquals(movie.getRank(), dto.getRank());
        assertEquals(movie.getDate(), dto.getDate());
        assertEquals(movie.getRevenue(), dto.getRevenue());
    }
    
    @Test
    void doTestFromIdentifiableMovieDTO() {
    	IdentifiableMovieDTO dto = new IdentifiableMovieDTO();
    	dto.setId(1L);
        dto.setTitle("title");
        dto.setRank(5f);
        dto.setDate(LocalDate.now());
        dto.setRevenue(new BigDecimal(100000));
        Movie movie = MovieDTOMapper.fromIdentifiableMovieDTO(dto);
        assertNotNull(movie);
        assertEquals(dto.getId(), movie.getId());
        assertEquals(dto.getTitle(), movie.getTitle());
        assertEquals(dto.getRank(), movie.getRank());
        assertEquals(dto.getDate(), movie.getDate());
        assertEquals(dto.getRevenue(), movie.getRevenue());
    }

    @Test
    void doTestNull() {
        assertNull(MovieDTOMapper.fromMovieDTO(null));
        assertNull(MovieDTOMapper.toIdentifiableMovieDTO(null));
    }

}
