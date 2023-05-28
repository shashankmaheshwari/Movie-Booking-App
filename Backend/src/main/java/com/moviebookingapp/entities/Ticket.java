package com.moviebookingapp.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="ticket")
@Builder
public class Ticket {
  
	  @Transient
	  public static final String TICKET_SEQUENCE = "Ticket_sequence"; 
	  @Id
	  private int ticketId;
	  
	  @NotBlank(message="Movie name can't be empty")
	  private String  movieName;
	  
	  @NotBlank(message="Theatre Name can't be empty")
	  private String theatreName;
	  
//	  @NotNull(message="enter valid customerId")
 	  private int customerId;
	  
	  @NotNull(message="Enter number Of Tickets")
	  private int numberOfTicket;
	  
	  @NotNull(message="Enter Seats to book")
	  private List<Seat> seats;
	  
	  private double totaCost;
	 
	  
}
