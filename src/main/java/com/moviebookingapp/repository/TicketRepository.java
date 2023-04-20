package com.moviebookingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Integer>{
}
