package com.moviebookingapp.service;

import java.util.List;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.exception.MovieFoundException;
import com.moviebookingapp.exception.MovieNotFoundException;

public interface MovieService {

	// Add a Movie
	public Movie addMovie(Movie movie) throws MovieNotFoundException, MovieFoundException;

	// Get List Of Movies
	public List<Movie> viewMovieList() throws MovieNotFoundException;

	// search a Movie Basis on the Name
	public List<Movie> searchMovie(String movieName) throws MovieNotFoundException;
	
	//search a movie Basis on the theatre Name
	public List<Movie> searchMovieOnBasisTheatreName(String theatreName);

	
	//Search a Movie Basis on the Id AND Name
	public Movie viewMovie(int movieId,String movieName) throws MovieNotFoundException;
	
	//Delete a Movie Basis on the Movie ID
	public Movie removeMovie(int movieId) throws MovieNotFoundException;
	
	//check ticket Status
	public void  updateTicketStatus(String movieName,String theatreName) throws MovieNotFoundException;


	List<Movie> searchByMovieOrTheatreNames(String searchKeyword);

	Movie searchByCompositeId(String movieName, String theatreName);
}
