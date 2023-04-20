package com.moviebookingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.sequenceGenerators.MovieSequenceGenerator;
import com.moviebookingapp.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieSequenceGenerator movieSequenceGenerator;
	
	
	

	@Override
	public Movie addMovie(Movie movie) throws MovieNotFoundException {
		if (movie != null) {
			if (movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(movie.getCompositeId().getMovieName(),movie.getCompositeId().getTheatreName())!=null) {
				throw new MovieNotFoundException("Movie already exists");
			} else {
				movie.setMovieId(movieSequenceGenerator.getSequenceNumber(Movie.MOVIE_SEQUENCE));
				movieRepository.save(movie);
			}
		}
		return movie;

	}

	@Override
	public List<Movie> viewMovieList() throws MovieNotFoundException {
		List<Movie> movies = movieRepository.findAll();
		return movies;
	}

	@Override
	public List<Movie> searchMovie(String movieName) throws MovieNotFoundException {
		List<Movie> movies = movieRepository.findAllByCompositeIdMovieName(movieName);
		if (movies.size() == 0) {
			throw new MovieNotFoundException(" Movie does not exists");
		}
		return movies;
	}

	@Override
	public Movie removeMovie(int movieId) throws MovieNotFoundException {
		Movie movie=movieRepository.findByMovieId(movieId);
		movieRepository.delete(movie);
		
		return movie;
	}

	@Override
	public Movie viewMovie(int movieId,String movieName) throws MovieNotFoundException {
		
		Movie movie= movieRepository.findByMovieIdAndCompositeIdMovieName(movieId,movieName);
		if(movie==null) {
			throw new MovieNotFoundException("Movie with this id and Name does not exist");
		}
		return movie;
	}

	@Override
	public void  updateTicketStatus(String movieName, String theatreName) throws MovieNotFoundException{
		Movie movie= movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(movieName,theatreName);
		if(movie==null) {
			throw new MovieNotFoundException("Movie with this id and Name does not exist");
		}
		movie.setTicketStatus(" SOLD OUT ");
		return ;
		
	}

}
