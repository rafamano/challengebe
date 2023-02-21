package com.xpand.challenge.dto.identifiable;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.xpand.challenge.dto.MovieDTO;

public class IdentifiableMovieDTO extends MovieDTO {

	@NotNull
    private Long id;
	
    public IdentifiableMovieDTO(@NotNull Long id, @NotNull String title, @NotNull LocalDate date, @NotNull Float rank, BigDecimal revenue) {
		super(title, date, rank, revenue);
		this.id = id;
	}

	public IdentifiableMovieDTO() {
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
