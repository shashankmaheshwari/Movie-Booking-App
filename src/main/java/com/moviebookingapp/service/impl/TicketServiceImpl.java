package com.moviebookingapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.Ticket;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.exception.TicketNotFoundException;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.repository.TicketRepository;
import com.moviebookingapp.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
//    @Autowired
//	private TicketRepository ticketRepository;
//    
//    @Autowired
//    private MovieRepository movieRepository;
//    
//	@Override
//	public Ticket generateTicket(Ticket ticket) throws  MovieNotFoundException,TicketNotFoundException {
//		// TODO Auto-generated method stub
//		Movie movie=movieRepository.findByMovieId(ticket.getMovieId());
//		if(movie==null) {
//			throw new MovieNotFoundException("Movie does not  exists");
//		}
//		int totalNoOfTickets=movie.getTotalNoOfTickets();
//		int balanceTickets=totalNoOfTickets-ticket.getNumberOfTicket();
//		if(balanceTickets<=0) {
//			throw new TicketNotFoundException("Ticket can't be generated");
//		}
//		return ticketRepository.save(ticket);
//		
	//}


	

}
