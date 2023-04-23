package com.moviebookingapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.EmbeddedId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "movie")
public class Movie {

	@Transient
	public static final String MOVIE_SEQUENCE = "Movie_sequence";

	@Id
	private int movieId;
 
	
	@EmbeddedId
	private MovieCompositeKey compositeId;

	@Min(50)
	@Max(200)
	private int totalNoOfTickets;
	
	private int noOfTicketsSold;

	@Min(0)
	@Max(10000)
	private double movieCost;
	
	private String ticketStatus="BOOK ASAP";
}

