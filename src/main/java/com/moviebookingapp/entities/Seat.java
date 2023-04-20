package com.moviebookingapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="seat")
public class Seat {
	@Transient
	public static final String SEQUENCE_NAME="seat_sequence";
	@Id
	private int SeatId;
	
	private int seatNumber;
	
	private String seatType;
	
	private SeatStatus seatStatus;
	
	private double cost;
	
	private Movie movie;
	
	public void setSeatType() {
		if(this.seatNumber>0&&this.seatNumber<40) {
			this.seatType="Balcony";
			
		}else {
			this.seatType="Orchestra";
		}
	}
	
	public void setCost() {
		if(this.seatType.equalsIgnoreCase("Orchestra")) {
			this.cost=200;
		}else if(this.seatType.equalsIgnoreCase("Balcony")) {
			this.cost=100;
		}
	}
}
