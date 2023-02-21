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

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableActorDTO;
import com.xpand.challenge.model.Actor;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.repository.ActorRepository;
import com.xpand.challenge.repository.MovieRepository;
import com.xpand.challenge.service.impl.DefaultActorService;
import com.xpand.challenge.validator.Validator;

class DefaultActorServiceTest {

    private DefaultActorService actorService;

    @Mock
    private ActorRepository actorRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private Validator<ActorDTO> actorValidator;
    @Mock
    private MovieService movieService;

    private Movie movie;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        actorService = new DefaultActorService(actorRepository, actorValidator, movieRepository);
        movie = new Movie(1L, "Movie 1", LocalDate.now(), 1.5f, null, null);
    }

    @Test
    void testCreateActor() {
    	LocalDate date = LocalDate.now();
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setName("Test Actor");
        actorDTO.setBdate(date);
        actorDTO.setGender(ChallengeConstants.GENDER_TYPE_FEMALE);
        actorDTO.setMovieId(1L);
        Mockito.when(movieRepository.findById(actorDTO.getMovieId())).thenReturn(Optional.of(movie));
        Mockito.when(actorRepository.save(Mockito.any())).thenReturn(
        		new Actor(1L, "Test Actor", date, ChallengeConstants.GENDER_TYPE_FEMALE, movie));
        IdentifiableActorDTO result = actorService.createActor(actorDTO);
        assertNotNull(result);
        assertEquals("Test Actor", result.getName());
    }

    @Test
    void testGetActor() {
        Mockito.when(actorRepository.findById(1L)).thenReturn(java.util.Optional.of(new Actor()));
        IdentifiableActorDTO result = actorService.getActor(1L);
        assertNotNull(result);
    }

    @SuppressWarnings("unchecked")
	@Test
    void testGetActors() {
    	Page<Actor> actorPage = Mockito.mock(Page.class);
    	List<Actor> actorList = List.of(new Actor(1L, "Test Actor", LocalDate.now(), ChallengeConstants.GENDER_TYPE_FEMALE, null));
    	Mockito.when(actorPage.getContent()).thenReturn(actorList);
    	Mockito.when(actorRepository.findAll(PageRequest.of(0, 10))).thenReturn(actorPage);

    	List<IdentifiableActorDTO> result = actorService.getActors(0, 10);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetActorsByDate() {
        Mockito.when(actorRepository.findByMovieId(Mockito.anyLong())).thenReturn(Set.of(new Actor()));
        List<IdentifiableActorDTO> result = actorService.getActorsByMovieId(Mockito.anyLong());
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateActor() {
    	LocalDate date = LocalDate.now();
        IdentifiableActorDTO actorDTO = new IdentifiableActorDTO();
        actorDTO.setId(1L);
        actorDTO.setName("Test Actor");
        actorDTO.setBdate(date);
        actorDTO.setGender(ChallengeConstants.GENDER_TYPE_FEMALE);
        actorDTO.setMovieId(1L);
        Mockito.when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        Mockito.when(actorRepository.findById(1L)).thenReturn(Optional.of(new Actor(1L, "Test Actor", date, ChallengeConstants.GENDER_TYPE_FEMALE, movie)));
        Mockito.when(actorRepository.save(Mockito.any())).thenReturn(new Actor(1L, "Test Actor Updated", date, ChallengeConstants.GENDER_TYPE_FEMALE, movie));
        IdentifiableActorDTO result = actorService.updateActor(actorDTO);
        assertNotNull(result);
        assertEquals("Test Actor Updated", result.getName());
    }

    @Test
    void testUpdateActorNotFound() {
        IdentifiableActorDTO actorDTO = new IdentifiableActorDTO();
        actorDTO.setId(1L);
        assertThrows(NoSuchElementException.class, () -> actorService.updateActor(actorDTO));
    }

    @Test
    void testDeleteActor() {
        actorService.deleteActor(1L);
        Mockito.verify(actorRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

}
