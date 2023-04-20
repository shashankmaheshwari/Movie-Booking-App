package com.moviebookingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.Ticket;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.exception.TicketNotFoundException;
import com.moviebookingapp.service.impl.TicketServiceImpl;

@RestController
@RequestMapping("/moviebooking")
public class TicketController {
	
	@Autowired
	private TicketServiceImpl ticketService;
  
	@PostMapping("/add")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) throws MovieNotFoundException, TicketNotFoundException{
		ResponseEntity<Ticket> response = null;

		
		Ticket ticket1=ticketService.generateTicket(ticket);
		response =new ResponseEntity<Ticket>(ticket1, HttpStatus.CREATED);
		return response;
	}
}
