package com.moviebookingapp.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.entities.Ticket;
import com.moviebookingapp.repository.TicketRepository;
import com.moviebookingapp.service.impl.TicketServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	@Mock
	private TicketRepository ticketRepository;
	
	@InjectMocks
	private TicketServiceImpl ticketService;
	
	private Ticket ticket;
	
	@BeforeEach
	public void setup() {
		Seat seat1=Seat.builder()
				   .seatId(5)
				   .seatNumber(12)
				   .cost(40)
				   .build();
		Seat seat2=Seat.builder()
				   .seatId(6)
				   .seatNumber(10)
				   .cost(30)
				   .build();
		ticket=Ticket.builder()
				   .ticketId(25)
				   .movieName("3idiot")
				   .theatreName("PVR")
				   .numberOfTicket(50)
				   .seats(List.of(seat1,seat2))
				   .totaCost(45.0)
				   .build();
	}
	
	
	 @DisplayName("Junit test for Generate Ticket method")
	   @Test
	   public void givenTicketObject_whenSaveTicket_thenReturnTicketObject() {
		   
		   
		   given(ticketRepository.save(ticket)).willReturn(ticket);
		   
		   Ticket savedTicket=ticketRepository.save(ticket);
		   
		   assertThat(savedTicket).isNotNull();
	   }
}
