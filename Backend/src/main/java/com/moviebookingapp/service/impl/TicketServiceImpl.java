package com.moviebookingapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Movie;
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
import com.moviebookingapp.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieServiceImpl movieService;
	@Autowired
	private SeatRepository seatRepository;
	@Autowired
	private DBSequenceGenerator sequenceGenerator;
    
	@Override
	public Ticket generateTicket(Ticket ticket) throws  MovieNotFoundException,TicketNotFoundException, CommonException {
		
		Movie movie=movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(ticket.getMovieName(), ticket.getTheatreName());
		//MOVIE IS PRESENT
		if(movie!=null) {
			int availableTickets=movie.getTotalNoOfTickets()-movie.getNoOfTicketsSold();
			// BOOK TICKET
			if(availableTickets>ticket.getNumberOfTicket()) {
				List<Seat> listOfSeat=ticket.getSeats();
				
				if(listOfSeat.size()!=ticket.getNumberOfTicket()) {
					throw new TicketNotFoundException("Add appropraite Seats");
				} 
				List<Seat> seats=new ArrayList<>();
				
				for(Seat s:listOfSeat) {
					Seat seat=seatRepository.findBySeatNumberAndMovieId(s.getSeatNumber(), movie.getMovieId());
				    seats.add(seat);
				}
				double cost=0;
				for(Seat s:seats) {
					// ADDED SEAT IS ALREDY BOOKED
					if(s.getSeatStatus().equals(SeatStatus.Booked)) {
						throw new CommonException(s.getSeatNumber()+" is already booked Select new Seat");
					}else {
						s.setSeatStatus(SeatStatus.Booked);
						cost+=s.getCost();
						//CHANGING SEAT STATUS 
						seatRepository.save(s); 
					}
				}
				// ADDING THE COST OF TICKET
				ticket.setTotaCost(ticket.getNumberOfTicket()*movie.getMovieCost()+cost);
				// UPDATING SOLD TICKETS
				movie.setNoOfTicketsSold(movie.getNoOfTicketsSold()+ticket.getNumberOfTicket());
				
				movieRepository.save(movie);
				ticket.setTicketId(sequenceGenerator.getSequenceNumber(Ticket.TICKET_SEQUENCE));
				return ticketRepository.save(ticket);	
				
			}
			// CAN'T BOOK
			else {
				//movieService.updateTicketStatus(movie.getCompositeId().getMovieName(), movie.getCompositeId().getTheatreName());
			    throw new TicketNotFoundException("Tickets Not Available");
			}
			
		}
		//MOVIE IS NOT PRESENT
		else {
			throw new MovieNotFoundException("Movie Not Found");
		}
		
		
		
	
	   
	 }

	@Override
	public List<Ticket> viewTickets() throws TicketNotFoundException {
		List<Ticket> tickets=ticketRepository.findAll();
		if(tickets.size()==0) {
			throw new TicketNotFoundException("Tickets Not Available");
		}else {
			return tickets;
		}
		
	}

}
