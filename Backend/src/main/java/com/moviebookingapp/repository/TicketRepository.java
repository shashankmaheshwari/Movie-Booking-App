package com.moviebookingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.entities.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Integer>{




    List<Ticket> findByCustomerId(int customerId);


    Ticket findByTicketId(int ticketId);
}
