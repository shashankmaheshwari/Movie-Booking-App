package com.moviebookingapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.exception.MovieFoundException;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/moviebooking")
public class MovieController {

	Logger logger = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private MovieService movieService;

	// ADD MOVIE
	@PostMapping("/addMovie")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Movie> addMovie(@RequestBody @Valid Movie movie)
			throws MovieNotFoundException, MovieFoundException {
		movie = movieService.addMovie(movie);
		logger.info("-------Movie Added Successfully---------");
		return new ResponseEntity<>(movie, HttpStatus.CREATED);
	}

	// GET ALL MOVIES
	@GetMapping("/all")
	public ResponseEntity<?> viewMovieList() throws MovieNotFoundException {
		List<Movie> movie = movieService.viewMovieList();
		if (movie.size() != 0) {
			logger.info("-------Movie List Fetched---------");
			return new ResponseEntity<>(movieService.viewMovieList(), HttpStatus.FOUND);
		} else {
			logger.info("-------Movie Not Found---------");
			//throw new MovieNotFoundException("Movie Not Found");
			return new ResponseEntity<>("Movie Not Found", HttpStatus.NOT_FOUND);
		}

	}

	// SEARCH A MOVIE BASIS ON THE MOVIE NAME
	@GetMapping("/movies/search/{movieName}")
	public ResponseEntity<?> searchMovie(@PathVariable String movieName) throws MovieNotFoundException {
		ResponseEntity<?> response = null;

		List<Movie> movies = movieService.searchMovie(movieName);
		if (movies.size() != 0) {
			logger.info("-------Movie With Movie Name " + movieName + " Found---------");
			return new ResponseEntity<>(movies, HttpStatus.FOUND);

		} else {
			logger.info("-------Movie Not Found---------");
			//throw new MovieNotFoundException("Movie Not Found");
			return new ResponseEntity<>("Movie Not Found", HttpStatus.NOT_FOUND);
		}

	}

	// SEARCH A MOVIE ON THE BASIS OF ID
	@GetMapping("/viewMovie/{movieId}/{movieName}")
	public ResponseEntity<?> viewMovie(@PathVariable int movieId, @PathVariable String movieName)
			throws MovieNotFoundException {
		ResponseEntity<?> response = null;

		Movie movie = movieService.viewMovie(movieId, movieName);
		if (movie != null) {
			logger.info("-------Movie With Movie id " + movieId + " and Movie Name " + movieName + " Found---------");
			return new ResponseEntity<>(movie, HttpStatus.FOUND);

		}else {
			logger.info("-------Movie With Movie id " + movieId + " and Movie Name " + movieName + " Not Found---------");
			//throw new MovieNotFoundException("Movie Not Found");
			return new ResponseEntity<>("Movie Not Found",HttpStatus.NOT_FOUND);
		}
		
	}

	// DELETE A MOVIE ON THE BASIS ON ID AND NAME
	@DeleteMapping("/{movieName}/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> removeMovie(@PathVariable int id, @PathVariable String movieName)
			throws MovieNotFoundException {

		ResponseEntity<?> response = null;
		Movie movie = movieService.viewMovie(id, movieName);
		if (movie == null) {
			//throw new MovieNotFoundException("Movie Not Found");
			response = new ResponseEntity<>("Movie Not Found",HttpStatus.NOT_FOUND);
		} else {
			
			movieService.removeMovie(id);
			logger.info("-------Movie With Movie id " + id + " Deleted---------");
			response = new ResponseEntity<>(movie, HttpStatus.OK);
		}
		
		return response;
	}

	// UPDATE TICKET STATUS
	@PutMapping("/{movieName}/{theatreName}/update/ticket")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> updateTicketStatus(@PathVariable String movieName, @PathVariable String theatreName)
			throws MovieNotFoundException {
		ResponseEntity<?> response = null;
		movieService.updateTicketStatus(movieName, theatreName);
		response = new ResponseEntity<>("Ticket Updated ",HttpStatus.OK);
		logger.info("-------Movie Ticket Status Updated  -----------------");
		return response;

	} 

	// FIND ALL MOVIES BASED ON THEATRE
	@GetMapping("/movies/search/theatre/{theatreName}")

	public ResponseEntity<?> searchMovieOnBasisTheatre(@PathVariable String theatreName) throws MovieNotFoundException {

		ResponseEntity<List<Movie>> response = null;

		List<Movie> movies = movieService.searchMovieOnBasisTheatreName(theatreName);
		if(movies.size()>0) {
			response = new ResponseEntity<>(movies, HttpStatus.FOUND);
			logger.info("-------Movies  in theatre  " + theatreName + " Found---------");
			return response;

		}else {
			return new ResponseEntity<>("Movie Not Foud",HttpStatus.NOT_FOUND);
		}
		
	}

}
