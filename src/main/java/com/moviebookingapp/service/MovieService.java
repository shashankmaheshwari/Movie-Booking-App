package com.moviebookingapp.service;

import java.util.List;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.exception.MovieNotFoundException;

public interface MovieService {

	// Add a Movie
	public Movie addMovie(Movie movie) throws MovieNotFoundException;

	// Get List Of Movies
	public List<Movie> viewMovieList() throws MovieNotFoundException;

	// search a Movie Basis on the Name
	public List<Movie> searchMovie(String movieName) throws MovieNotFoundException;
	
	//Search a Movie Basis on the Id AND Name
	public Movie viewMovie(int movieId,String movieName) throws MovieNotFoundException;
	
	//Delete a Movie Basis on the Movie ID
	public Movie removeMovie(int movieId) throws MovieNotFoundException;
	

}
