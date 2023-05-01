package com.moviebookingapp.entityTest;

import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.MovieCompositeKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieEntityTest {
    Movie movie = new Movie();
    MovieCompositeKey movieId = new MovieCompositeKey("movieName","theatreName");
    @Test
    public void setMovieCompTest(){
        movie.setCompositeId(movieId);
        assertEquals(movieId,movie.getCompositeId());
    }
    @Test
    public void setMovieIdTest(){
        movie.setMovieId(1);
        assertEquals(1,movie.getMovieId());
    }

    @Test
    public void setMovieCostTest(){
        movie.setMovieCost(100);
        assertEquals(100,movie.getMovieCost());
    }
    @Test
    public void setNoOfTicketsTest(){
        movie.setTotalNoOfTickets(10);
        assertEquals(10,movie.getTotalNoOfTickets());
    }
    @Test
    public void setNoOfTicketsSold_Test(){
        movie.setNoOfTicketsSold(5);
        assertEquals(5,movie.getNoOfTicketsSold());
    }
    @Test
    public void setTicketStatus_Test(){
        assertEquals("BOOK ASAP",movie.getTicketStatus());
    }

}
