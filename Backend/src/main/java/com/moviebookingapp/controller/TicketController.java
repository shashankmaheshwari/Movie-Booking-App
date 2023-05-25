package com.moviebookingapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.moviebookingapp.entities.Ticket;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.exception.TicketNotFoundException;
import com.moviebookingapp.service.impl.TicketServiceImpl;

@RestController
@RequestMapping("/moviebooking")
@CrossOrigin("*")
public class TicketController {
	
	Logger logger =LoggerFactory.getLogger(TicketController.class);
	
	@Autowired
	private TicketServiceImpl ticketService;
  
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) throws MovieNotFoundException, TicketNotFoundException, CommonException{
		ResponseEntity<Ticket> response = null;
 
		
		Ticket ticket1=ticketService.generateTicket(ticket);
		response =new ResponseEntity<Ticket>(ticket1, HttpStatus.CREATED);
		logger.info("-------Ticked Created Successfully---------"); 
		return response; 
	}
	@GetMapping("/view/tickets")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?>viewTickets() throws TicketNotFoundException{
		ResponseEntity<List<Ticket>> response = null;
		
		List<Ticket>tickets=ticketService.viewTickets();
		response=new  ResponseEntity<List<Ticket>>(tickets,HttpStatus.OK);
		logger.info("------- View All Tickets ---------");
		return response;
		
	}
}
