package com.moviebookingapp.service;

import com.moviebookingapp.entities.Seat;

import java.util.List;

public interface SeatService {
	// ADD SEAT
	public void addSeat(Seat seat);
	public List<Seat> getAllSeats(int movieId);
}
