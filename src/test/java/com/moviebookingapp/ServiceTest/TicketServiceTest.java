package com.moviebookingapp.ServiceTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.MovieCompositeKey;
import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.entities.SeatStatus;
import com.moviebookingapp.entities.Ticket;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.exception.TicketNotFoundException;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.repository.SeatRepository;
import com.moviebookingapp.repository.TicketRepository;
import com.moviebookingapp.sequenceGenerators.DBSequenceGenerator;
import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.impl.TicketServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	@Mock
	private TicketRepository ticketRepository;

	@Mock
	private MovieRepository movieRepository;

	@Mock
	private SeatRepository seatRepository;
	
	@Mock
	private MovieService movieService;
	
	@Mock
	private DBSequenceGenerator sequenceGenerator;

	@InjectMocks
	private TicketServiceImpl ticketService;

	private Ticket ticket;

	private Movie movie;
	
	private MovieCompositeKey movieCompositeKey;
	
	private Seat seat1;

	@BeforeEach
	void setUp() {
		movieCompositeKey = new MovieCompositeKey("movieName", "theatreName");
		movie = new Movie(1,movieCompositeKey, 100, 1, 100.0, "Available");
		seat1 = new Seat(1, 2, "Balcony", SeatStatus.Available, 100.0, movie);
		// Seat seat2 = new Seat(1, 2, "Balcony", SeatStatus.Available, 100.0, movie);
		List<Seat> seats = List.of(seat1);
		ticket = new Ticket(1, "movieName", "theatreName", 1, seats,100.0); 
	} 

	@DisplayName("Junit test for Generate Ticket method")
	@Test
	public void givenTicketObject_whenSaveTicket_thenReturnTicketObject() throws MovieNotFoundException, TicketNotFoundException, CommonException {

		when(movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(ticket.getMovieName(),
				ticket.getTheatreName())).thenReturn(movie);
		
		when(seatRepository.findBySeatNumberAndMovieId(2, 1)).thenReturn(seat1);
        ticketService.generateTicket(ticket);
        verify(movieRepository,times(1)).save(movie);
	}
	
	@DisplayName("Junit test for Generate Ticket method (Exception-> seat already booked)")
	@Test
	public void givenExistingSeat_whenSaveTicket_thenThrowException() throws CommonException {
          
		seat1.setSeatStatus(SeatStatus.Booked);
		when(movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(ticket.getMovieName(),
				ticket.getTheatreName())).thenReturn(movie);
		
		when(seatRepository.findBySeatNumberAndMovieId(2, 1)).thenReturn(seat1);
		
		CommonException e=assertThrows(CommonException.class,()->{ticketService.generateTicket(ticket);});
		
		assertEquals("2 is already booked Select new Seat",e.getMessage());
	}
	
	
	@DisplayName("Junit test for Generate Ticket method (Exception-> Movie Not Found)")
	@Test
	public void givenMovieNotFound_whenSaveTicket_thenThrowException() throws CommonException {
          
		when(movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(ticket.getMovieName(),
				ticket.getTheatreName())).thenReturn(null);
		
		
		
		MovieNotFoundException e=assertThrows(MovieNotFoundException.class,()->{ticketService.generateTicket(ticket);});
		
		assertEquals("Movie Not Found",e.getMessage());
	}
	
	@DisplayName("Junit test for Generate Ticket method (Exception-> Tickets Not available)")
	@Test
	public void givenTicketsNotAvailable_whenSaveTicket_thenThrowException() throws CommonException {
          
		when(movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(ticket.getMovieName(),
				ticket.getTheatreName())).thenReturn(movie);
		//when(movieService.updateTicketStatus(movie.getCompositeId().getMovieName(), movie.getCompositeId().getTheatreName())).thenReturn
		movie.setNoOfTicketsSold(movie.getTotalNoOfTickets());
		
		
		TicketNotFoundException e=assertThrows(TicketNotFoundException.class,()->{ticketService.generateTicket(ticket);});
		
		assertEquals("Tickets Not Available",e.getMessage()); 
	}
	
	
}
