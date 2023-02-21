package com.xpand.challenge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class MovieDTO {

	@NotNull
    private String title;
	@NotNull
	private LocalDate date;
	@NotNull
	private Float rank;

	private BigDecimal revenue;
	
	public MovieDTO(@NotNull String title, @NotNull LocalDate date, @NotNull Float rank, BigDecimal revenue) {
		super();
		this.title = title;
		this.date = date;
		this.rank = rank;
		this.revenue = revenue;
	}

	public MovieDTO() {
	}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

}
