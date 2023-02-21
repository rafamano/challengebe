package com.xpand.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.validator.Validator;
import com.xpand.challenge.validator.impl.ActorValidator;
import com.xpand.challenge.validator.impl.MovieValidator;

@Configuration
public class AppConfiguration {
    
    @Bean
    Validator<MovieDTO> movieValidator() {
        return new MovieValidator();
    }
    
    @Bean
    Validator<ActorDTO> actorValidator() {
    	return new ActorValidator();
    }

}
