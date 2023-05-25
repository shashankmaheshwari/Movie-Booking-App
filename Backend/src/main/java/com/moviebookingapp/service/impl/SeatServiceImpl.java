package com.moviebookingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.repository.SeatRepository;
import com.moviebookingapp.service.SeatService;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;
	@Override
	public void addSeat(Seat seat) {
		seatRepository.save(seat);
	}

	public List<Seat> getAllSeats(int movieId){
		return  seatRepository.findByMovieMovieId(movieId);
	}

}
