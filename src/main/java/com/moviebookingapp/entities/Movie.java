package com.moviebookingapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="movie")
@CompoundIndexes({@CompoundIndex(name="composite_key",def="{'movieName':1,'theatreName':1")})
public class Movie {
	
	@Transient
	public static final String MOVIE_SEQUENCE="Movie_sequence";
	@Id
	private int movieId;
	
	private String movieName;
	private String theatreName;
	private int totalNoOfTickets;
}
