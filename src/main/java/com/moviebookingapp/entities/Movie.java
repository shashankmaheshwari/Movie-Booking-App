package com.moviebookingapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.EmbeddedId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movie")
public class Movie {

	@Transient
	public static final String MOVIE_SEQUENCE = "Movie_sequence";

	@Id
	private int movieId;
  
//	@NotBlank(message = "Movie Name can't be empty")
//	private String movieName;
//  
//	@NotBlank(message = "Theatre Name can't be empty")
//	private String theatreName;
	
	@EmbeddedId
	private MovieCompositeKey compositeId;

	@Min(0)
	@Max(500)
	private int totalNoOfTickets;

	@Min(0)
	@Max(10000)
	double movieCost;
}

