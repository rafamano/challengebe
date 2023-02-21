package com.xpand.challenge.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableActorDTO;
import com.xpand.challenge.dto.mapper.ActorDTOMapper;
import com.xpand.challenge.model.Movie;
import com.xpand.challenge.repository.ActorRepository;
import com.xpand.challenge.repository.MovieRepository;
import com.xpand.challenge.service.ActorService;
import com.xpand.challenge.validator.Validator;

@Service
public class DefaultActorService implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final Validator<ActorDTO> actorValidator;

    public DefaultActorService(ActorRepository actorRepository, Validator<ActorDTO> actorValidator, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
		this.movieRepository = movieRepository;  
        this.actorValidator = actorValidator;
    }

    @Override
    public IdentifiableActorDTO createActor(ActorDTO actorDTO) {
        actorValidator.validate(actorDTO);
    	Movie movie = movieRepository.findById(actorDTO.getMovieId())
    			.orElseThrow(() -> new NoSuchElementException(ChallengeConstants.MESSAGE_MOVIE_NOT_FOUND));  	
        return ActorDTOMapper.toIdentifiableActorDTO(actorRepository.save(ActorDTOMapper.fromActorDTO(actorDTO, movie)));
    }

    @Override
    public IdentifiableActorDTO getActor(Long id) {
        return actorRepository.findById(id).map(ActorDTOMapper::toIdentifiableActorDTO).orElseThrow(() -> new NoSuchElementException(ChallengeConstants.MESSAGE_ACTOR_NOT_FOUND));
    }

    @Override
    public List<IdentifiableActorDTO> getActors(int page, int size) {
        return actorRepository.findAll(PageRequest.of(page, size)).getContent().stream().map(ActorDTOMapper::toIdentifiableActorDTO).collect(Collectors.toList());
    }    

	@Override
	public List<IdentifiableActorDTO> getActorsByMovieId(Long id) {
        return actorRepository.findByMovieId(id).stream().map(ActorDTOMapper::toIdentifiableActorDTO).collect(Collectors.toList());
	}

    @Override
    public IdentifiableActorDTO updateActor(IdentifiableActorDTO actorDTO) {
    	actorValidator.validate(actorDTO);
    	actorRepository.findById(actorDTO.getId()).orElseThrow(() -> new NoSuchElementException(ChallengeConstants.MESSAGE_ACTOR_NOT_FOUND));
    	Movie movie = movieRepository.findById(actorDTO.getMovieId()).orElseThrow(() -> new NoSuchElementException(ChallengeConstants.MESSAGE_MOVIE_NOT_FOUND));
        return ActorDTOMapper.toIdentifiableActorDTO(actorRepository.save(ActorDTOMapper.fromIdentifiableActorDTO(actorDTO, movie)));
    }

    @Override
    public void deleteActor(Long id) {
    	actorRepository.deleteById(id);
    }
    
}
