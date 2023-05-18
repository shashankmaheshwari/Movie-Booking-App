package com.moviebookingapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "seat")
public class Seat {

	@Transient
	public static final String SEAT_SEQUENCE = "seat_sequence";
	@Id
	private int seatId;

	private int seatNumber;

	private String seatType;

	private SeatStatus seatStatus;

	private double cost;

	private Movie movie;

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setSeatType() {
		if (this.seatNumber > 0 && this.seatNumber < 40) {
			this.seatType = "Balcony";

		} else {
			this.seatType = "Orchestra";
		}
	}

	public void setCost() {
		if (this.seatType.equalsIgnoreCase("Orchestra")) {
			this.cost = 200;
		} else if (this.seatType.equalsIgnoreCase("Balcony")) {
			this.cost = 100;
		}
	}
}
