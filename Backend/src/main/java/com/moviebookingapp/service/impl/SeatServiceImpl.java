package com.moviebookingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.repository.SeatRepository;
import com.moviebookingapp.service.SeatService;
@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;
	@Override
	public void addSeat(Seat seat) {
		seatRepository.save(seat);
	}

}
