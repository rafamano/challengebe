package com.xpand.challenge.validator.impl;

import java.math.BigDecimal;
import java.util.Optional;

import com.xpand.challenge.config.ChallengeConstants;
import com.xpand.challenge.dto.MovieDTO;
import com.xpand.challenge.validator.Validator;

public class MovieValidator implements Validator<MovieDTO> {

    @Override
    public void validate(MovieDTO movie) {
        Optional.ofNullable(movie.getTitle())
            .map(String::trim)
            .filter(title -> !title.isEmpty())
            .orElseThrow(() -> new IllegalArgumentException(ChallengeConstants.MESSAGE_MOVIE_TITLE_EMPTY));

        Optional.ofNullable(movie.getRank())
            .filter(rank -> rank >= 0d && rank <= 10d)
            .orElseThrow(() -> new IllegalArgumentException(ChallengeConstants.MESSAGE_MOVIE_RANK_RANGE));

        Optional.ofNullable(movie.getDate())
            .orElseThrow(() -> new IllegalArgumentException(ChallengeConstants.MESSAGE_MOVIE_DATE_EMPTY));

        Optional.ofNullable(movie.getRevenue()).ifPresent(
            revenue -> {
                if (revenue.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException(ChallengeConstants.MESSAGE_MOVIE_REVENUE_POSITIVE);
            }
        );
    }

}
