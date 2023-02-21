package com.xpand.challenge.validator.impl;

import java.util.Optional;

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.validator.Validator;

public class ActorValidator implements Validator<ActorDTO> {

    @Override
    public void validate(ActorDTO actor) {
        Optional.ofNullable(actor.getName())
            .map(String::trim)
            .filter(name -> !name.isEmpty())
            .orElseThrow(() -> new IllegalArgumentException(ChallengeConstants.MESSAGE_ACTOR_NAME_EMPTY));

        Optional.ofNullable(actor.getBdate())
            .orElseThrow(() -> new IllegalArgumentException(ChallengeConstants.MESSAGE_ACTOR_BDATE_EMPTY));

        Optional.ofNullable(actor.getGender())
	        .map(String::trim).ifPresent(
	        		gender -> {
	                    if (!gender.trim().equalsIgnoreCase(ChallengeConstants.GENDER_TYPE_MALE) &&
	                    		!gender.trim().equalsIgnoreCase(ChallengeConstants.GENDER_TYPE_FEMALE)) 
	                    	throw new IllegalArgumentException(ChallengeConstants.MESSAGE_ACTOR_GENDER_NOT_MALE_FEMALE);
	                }
        );
        Optional.ofNullable(actor.getMovieId())
        .orElseThrow(() -> new IllegalArgumentException(ChallengeConstants.MESSAGE_ACTOR_MOVIE_ID_EMPTY));
    }
}
