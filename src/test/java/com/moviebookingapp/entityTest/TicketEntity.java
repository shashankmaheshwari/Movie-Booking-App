package com.moviebookingapp.entityTest;

import com.moviebookingapp.entities.Ticket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketEntity {
    Ticket ticket = new Ticket();
    @Test
    public void setTicketId_Test(){
        ticket.setTicketId(1);
        assertEquals(1,ticket.getTicketId());
    }
    @Test
    public void setMovieName_Test(){
        ticket.setMovieName("movieName");
        assertEquals("movieName",ticket.getMovieName());
    }
    @Test
    public void setTheatreName_Test(){
        ticket.setTheatreName("theatreName");
        assertEquals("theatreName",ticket.getTheatreName());
    }
    @Test
    public void setTotalCost_Test(){
        ticket.setTotaCost(100);
        assertEquals(100,ticket.getTotaCost());
    }
    @Test
    public void setNoOfTickets_Test(){
        ticket.setNumberOfTicket(2);
        assertEquals(2,ticket.getNumberOfTicket());
    }
    @Test
    public void setSeats_Test(){
        ticket.setSeats(List.of());
        assertEquals(List.of(),ticket.getSeats());
    }


}
