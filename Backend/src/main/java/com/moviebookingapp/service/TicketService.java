package com.moviebookingapp.service;

import java.util.List;

import com.moviebookingapp.entities.Ticket;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.exception.TicketNotFoundException;

public interface TicketService {

	 public Ticket generateTicket(Ticket ticket) throws MovieNotFoundException,TicketNotFoundException, CommonException;
	 
	 public List<Ticket> viewTickets() throws TicketNotFoundException;

	 public List<Ticket> getTicketBasisOnCustomerId(int customerId);

	 public Ticket getTicketBasisOfTicketId(int ticketId);
}
