package com.moviebookingapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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

	@Query("{$or:[{\"compositeId.movieName\": { $regex: ?0 , $options: 'i'}}, {\"compositeId.theatreName\": { $regex: ?0, $options: 'i' }}]}")
	List<Movie> findByMovieNameOrTheatreName(String searchKeyword);
	
	List<Movie> findAll();
	

	
	
}
