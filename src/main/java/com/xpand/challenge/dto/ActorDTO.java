package com.xpand.challenge.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class ActorDTO {

	@NotNull
    private String name;
	@NotNull
    private LocalDate bdate;
	@NotNull
	private String gender;
	@NotNull
	private Long movieId;
    
	public ActorDTO(@NotNull String name, @NotNull LocalDate bdate, @NotNull String gender, @NotNull Long movieId) {
		super();
		this.name = name;
		this.bdate = bdate;
		this.gender = gender;
		this.movieId = movieId;
	}
	
	public ActorDTO() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBdate() {
		return bdate;
	}
	public void setBdate(LocalDate bdate) {
		this.bdate = bdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

    

}
