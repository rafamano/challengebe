package com.xpand.challenge.dto.identifiable;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.xpand.challenge.dto.ActorDTO;

public class IdentifiableActorDTO extends ActorDTO {

	@NotNull
    private Long id;
	
    public IdentifiableActorDTO(@NotNull Long id, @NotNull String name, @NotNull LocalDate bdate, @NotNull String gender,
			@NotNull Long movieId) {
		super(name, bdate, gender, movieId);
		this.id = id;
	}

	public IdentifiableActorDTO() {
	}

	public Long getId() {
        return id;
    }
    
	public void setId(Long id) {
		this.id = id;
	}

}
