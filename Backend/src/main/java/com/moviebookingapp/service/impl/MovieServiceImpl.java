 package com.moviebookingapp.service.impl;

import java.util.List;

import com.moviebookingapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.entities.SeatStatus;
import com.moviebookingapp.exception.MovieFoundException;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.sequenceGenerators.DBSequenceGenerator;
import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.SeatService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private SeatRepository seatRepository;
	@Autowired
	private DBSequenceGenerator sequenceGenerator;

	@Autowired
	private SeatService seatService;

	@Autowired
	private KafkaTemplate<String, Movie> kafkaTemplate;

	@Override
	public Movie addMovie(Movie movie) throws MovieNotFoundException, MovieFoundException {
		if (movie != null) {
			if (movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(
					movie.getCompositeId().getMovieName(), movie.getCompositeId().getTheatreName()) != null) {
				throw new MovieFoundException("Movie already exists");
			} else {
				movie.setMovieId(sequenceGenerator.getSequenceNumber(Movie.MOVIE_SEQUENCE));
				int seats = movie.getTotalNoOfTickets();
				for (int i = 1; i <= seats; i++) {
					Seat seat = new Seat();
					// SEQUENCE
					seat.setSeatId(sequenceGenerator.getSequenceNumber(Seat.SEAT_SEQUENCE));
					seat.setSeatNumber(i);
					seat.setSeatStatus(SeatStatus.Available);
					seat.setSeatType();
					seat.setCost();
					seat.setMovie(movie); 
					// ADD MOVIE IN THE SEAT
					seatService.addSeat(seat);
				}

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
	public List<Movie> searchMovie(String movieName)  {
		List<Movie> movies = movieRepository.findAllByCompositeIdMovieName(movieName);
		
		return movies;
	}

	public List<Movie> searchMovieOnBasisTheatreName(String theatreName)  {
		List<Movie> movies = movieRepository.findAllByCompositeIdTheatreName(theatreName);

		return movies;
	}

	@Override
	public Movie removeMovie(int movieId) throws MovieNotFoundException {
		Movie movie = movieRepository.findByMovieId(movieId);
		List<Seat> seats = seatRepository.findByMovieMovieId(movieId);
		seatRepository.deleteAll(seats);
		movieRepository.delete(movie);


		return movie;
	}

	@Override
	public Movie viewMovie(int movieId, String movieName) throws MovieNotFoundException {

		Movie movie = movieRepository.findByMovieIdAndCompositeIdMovieName(movieId, movieName);
		
		return movie; 
	}

	@Override
	public void updateTicketStatus(String movieName, String theatreName) throws MovieNotFoundException {
		Movie movie = movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(movieName, theatreName);
		if (movie == null) {
			throw new MovieNotFoundException("Movie with this id and Name does not exist");
		}
		if (movie.getNoOfTicketsSold() >= movie.getTotalNoOfTickets()) {
			movie.setTicketStatus("SOLD OUT");
			movieRepository.save(movie);
			kafkaTemplate.send("ticket-status", movie);  
		}

		return; 

	}
	@Override
	public List<Movie> searchByMovieOrTheatreNames(String searchKeyword) {
		return movieRepository.findByMovieNameOrTheatreName(searchKeyword);
	}

	@Override
	public Movie searchByCompositeId(String movieName, String theatreName) {
		return movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(movieName,theatreName);
	}

}
