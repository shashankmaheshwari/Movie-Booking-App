package com.moviebookingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.entities.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

	Movie findByMovieNameAndTheatreName(String movieName, String theatreName);

	boolean existsByMovieNameAndTheatreName(String movieName, String theatreName);

	List<Movie> findAllByMovieName(String movieName);
	
	Movie findByMovieIdAndMovieName(int  MovieId,String movieName);
	
	Movie findByMovieId(int  MovieId);
}
