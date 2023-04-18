package com.moviebookingapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.entities.Ticket;

//@RestController
//@RequestMapping("/moviebooking")
public class TicketController {
  
	@PostMapping("/generate-ticket/{movieId}")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
		
		return null;
	}
}
