package com.moviebookingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.MovieCompositeKey;

@Repository
public interface MovieRepository extends MongoRepository<Movie, MovieCompositeKey> {

	Movie findByCompositeIdMovieNameAndCompositeIdTheatreName(String movieName, String theatreName);


	List<Movie> findAllByCompositeIdMovieName(String movieName);
	
	List<Movie> findAllByCompositeIdTheatreName(String theatreName);

	
	Movie findByMovieIdAndCompositeIdMovieName(int movieId,String movieName);
	
	Movie findByMovieId(int  MovieId);
	
	List<Movie> findAll();
	

	
	
}
