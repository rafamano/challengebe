package com.xpand.challenge.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.identifiable.IdentifiableActorDTO;
import com.xpand.challenge.service.ActorService;

@RestController
@RequestMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<IdentifiableActorDTO>> getActors(
    		@RequestParam(value = "page", defaultValue = ChallengeConstants.DEFAULT_PAGE_VALUE, required = false) int page, 
    		@RequestParam(value = "size", defaultValue = ChallengeConstants.DEFAULT_PAGE_SIZE_VALUE, required = false) int size) {
        return ResponseEntity.ok().body(actorService.getActors(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentifiableActorDTO> getActor(@PathVariable Long id) {
        return ResponseEntity.ok().body(actorService.getActor(id));
    }
    
    @GetMapping("/movie/{id}")
    public ResponseEntity<List<IdentifiableActorDTO>> getActorsByMovieId(@PathVariable Long id) {
    	return ResponseEntity.ok().body(actorService.getActorsByMovieId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdentifiableActorDTO> createActor(@RequestBody @Valid ActorDTO actorDTO) {
        return ResponseEntity.ok().body(actorService.createActor(actorDTO));
    }

    @PutMapping
    public ResponseEntity<IdentifiableActorDTO> updateActor(@RequestBody @Valid IdentifiableActorDTO actorDTO) {
        return ResponseEntity.ok().body(actorService.updateActor(actorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
    	actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}


