package com.moviebookingapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.repository.SeatRepository;

@Service
public class KafkaListner {

	@Autowired
	private SeatRepository seatRepository;

	@KafkaListener(topics="ticket-status",groupId="movie-group")
	public void listenTopic(Movie movie) {
		int id=movie.getMovieId();
		List<Seat> seats=seatRepository.findByMovieMovieId(id);
		for(Seat s:seats) {
			//s.setMovie(s.getMovie());
			s.setMovie(movie);

		}
		seatRepository.saveAll(seats);


	}
}
