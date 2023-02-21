package com.xpand.challenge.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.ActorDTO;
import com.xpand.challenge.validator.impl.ActorValidator;

class ActorValidatorTests {

    static Validator<ActorDTO> validator;

    @BeforeAll
    static void setup() {
        validator = new ActorValidator();
    }

    @Test
    void doTestValidActor() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Name");
        dto.setBdate(LocalDate.now());
        dto.setGender(ChallengeConstants.GENDER_TYPE_MALE);
        dto.setMovieId(1L);
        assertDoesNotThrow(() -> validator.validate(dto));
    }

    @Test
    void doTestInvalidName() {
        ActorDTO dto = new ActorDTO();
        assertThrowsExactly(IllegalArgumentException.class, () -> validator.validate(dto));
    }

    @Test
    void doTestInvalidGender() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Name");
        dto.setBdate(LocalDate.now());
        dto.setGender("");
        dto.setMovieId(1L);
        assertThrowsExactly(IllegalArgumentException.class, () -> validator.validate(dto));
    }

    @Test
    void doTestInvalidBdate() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Name");
        dto.setGender("");
        dto.setMovieId(1L);
        assertThrowsExactly(IllegalArgumentException.class, () -> validator.validate(dto));
    }

    @Test
    void doTestInvalidMovieId() {
        ActorDTO dto = new ActorDTO();
        dto.setName("Name");
        dto.setBdate(LocalDate.now());
        dto.setGender(ChallengeConstants.GENDER_TYPE_MALE);
        assertThrowsExactly(IllegalArgumentException.class, () -> validator.validate(dto));
    }
}
