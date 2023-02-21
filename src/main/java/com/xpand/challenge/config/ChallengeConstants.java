package com.xpand.challenge.config;

public final class ChallengeConstants {
	
	// Error messages Actor
    public static final String MESSAGE_ACTOR_NOT_FOUND = "The specified actor doesn't exit";
    public static final String MESSAGE_ACTOR_GENDER_NOT_MALE_FEMALE = "Gender must be 'male' or 'female'";
    public static final String MESSAGE_ACTOR_NAME_EMPTY = "Name must not be empty";
    public static final String MESSAGE_ACTOR_BDATE_EMPTY = "BDate must not be empty";
    public static final String MESSAGE_ACTOR_MOVIE_ID_EMPTY = "MovieId must not be empty";
    
    // Error messages Movie
    public static final String MESSAGE_MOVIE_NOT_FOUND = "The specified movie doesn't exit";
    public static final String MESSAGE_MOVIE_TITLE_EMPTY = "Title must not be empty";
    public static final String MESSAGE_MOVIE_DATE_EMPTY = "Date must not be empty";
    public static final String MESSAGE_MOVIE_RANK_RANGE = "Rank must be between 0 and 10";
    public static final String MESSAGE_MOVIE_REVENUE_POSITIVE = "Revenue must be greater than 0";
    
    
    public static final String GENDER_TYPE_MALE = "male";
    public static final String GENDER_TYPE_FEMALE = "female";
    public static final String DEFAULT_PAGE_VALUE = "0";
    public static final String DEFAULT_PAGE_SIZE_VALUE = "10";
    
    private ChallengeConstants() {}
}
