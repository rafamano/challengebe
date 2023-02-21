package com.xpand.challenge.service;

import java.util.List;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableActorDTO;

public interface ActorService {
    
    IdentifiableActorDTO createActor(ActorDTO actorDTO);

    IdentifiableActorDTO getActor(Long id);

    List<IdentifiableActorDTO> getActors(int page, int size);

    IdentifiableActorDTO updateActor(IdentifiableActorDTO actorDTO);

    void deleteActor(Long id);

	List<IdentifiableActorDTO> getActorsByMovieId(Long id);
}
