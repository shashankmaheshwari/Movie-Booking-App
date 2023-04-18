package com.moviebookingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/moviebooking")
public class MovieController {
	@Autowired
	private MovieService movieService;

	// ADD MOVIE
	@PostMapping("/addMovie")
	public ResponseEntity<Movie> addMovie(@RequestBody @Valid Movie movie) throws MovieNotFoundException {
		movie = movieService.addMovie(movie);
		return new ResponseEntity<>(movie, HttpStatus.CREATED);
	}

	// GET ALL MOVIES
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> viewMovieList() throws MovieNotFoundException {
		return ResponseEntity.ok(movieService.viewMovieList());

	}

	// SEARCH A MOVIE BASIS ON THE MOVIE NAME
	@GetMapping("/movies/search/{movieName}")
	public ResponseEntity<List<Movie>> searchMovie(@PathVariable String movieName) throws MovieNotFoundException {
		ResponseEntity<List<Movie>> response = null;

		List<Movie> movies = movieService.searchMovie(movieName);
		response = new ResponseEntity<>(movies, HttpStatus.OK);

		return response;

	}

	// SEARCH A MOVIE ON THE BASIS OF ID
	@GetMapping("/viewMovie/{movieId}/{movieName}")
	public ResponseEntity<Movie> viewMovie(@PathVariable int movieId, @PathVariable String movieName)
			throws MovieNotFoundException {
		ResponseEntity<Movie> response = null;

		Movie movie = movieService.viewMovie(movieId, movieName);
		response = new ResponseEntity<>(movie, HttpStatus.OK);

		return response;
	}

	// DELETE A MOVIE ON THE BASIS ON ID AND NAME
	@DeleteMapping("/{movieName}/delete/{id}")
	public ResponseEntity<Movie> removeMovie(@PathVariable int id, @PathVariable String movieName)
			throws MovieNotFoundException {

		ResponseEntity<Movie> response = null;
		Movie movie = movieService.viewMovie(id, movieName);
		if (movie == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			movieService.removeMovie(id);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
		}
		return response;
	}

}
