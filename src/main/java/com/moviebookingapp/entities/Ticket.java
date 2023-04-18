package com.moviebookingapp.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Document(collection="ticket")
public class Ticket {
  
	  @Transient
	  public static final String TICKET_SEQUENCE = "Ticket_sequence"; 
	  @Id
	  private int ticketId;
	  
	  @NotNull(message="enter valid movieId")
	  private int movieId;
	  
	  @NotNull(message="enter valid customerId")
	  private int customerId;
	  
	  @NotNull(message="Enter number Of Tickets")
	  private int numberOfTicket;
	  
	  @NotNull(message="Enter Seat Numbers to book")
	  private List<Integer> SeatNumber;
	 
	  
}
