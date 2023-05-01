package com.moviebookingapp.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.controller.TicketController;
import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.MovieCompositeKey;
import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.entities.SeatStatus;
import com.moviebookingapp.entities.Ticket;
import com.moviebookingapp.exception.ApplicationExceptionHandler;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.service.impl.TicketServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class TicketControllerTest {
	
   private MockMvc mockMvc;
   
   private ObjectMapper objectMapper=new ObjectMapper();
   
   @InjectMocks
   private TicketController ticketController;
   
   @Mock
   private TicketServiceImpl ticketService;
   
   private Ticket ticket;
   private MovieCompositeKey movieComp;
   private Movie movie;
   private Seat seat1;
   
   @Before
   public void setUp() {
		movieComp = new MovieCompositeKey("movieName", "theatreName");
		movie = new Movie(1,movieComp, 100, 1, 100.0, "Available");
		seat1 = new Seat(1, 2, "Balcony", SeatStatus.Available, 100.0, movie);
		List<Seat> seats = List.of(seat1);
		ticket = new Ticket(1, "movieName", "theatreName", 1, seats,100.0); 
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).setControllerAdvice(new  ApplicationExceptionHandler()).build();

   }
    
   @Test
   public void bookTicketTest_Booked() throws JsonProcessingException, Exception {
			MvcResult result = mockMvc 
					.perform(MockMvcRequestBuilders.post("/moviebooking/add").header("Authorization", "token")
							.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ticket)))
					.andReturn();
			assertEquals(201, result.getResponse().getStatus());  
   }
   
   @Test
   public void bookTicketTest_MovieNotFound() throws  Exception {
	   
	   Mockito.when(ticketService.generateTicket(any(Ticket.class))).thenThrow(new MovieNotFoundException("Movie Not Found"));
			MvcResult result = mockMvc 
					.perform(MockMvcRequestBuilders.post("/moviebooking/add").header("Authorization", "token")
							.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ticket)))
					.andReturn();
			assertEquals(404, result.getResponse().getStatus());  
   }
   
   @Test
   public void bookTicketTest_CommonException() throws  Exception {
	   
	   Mockito.when(ticketService.generateTicket(any(Ticket.class))).thenThrow(new CommonException("Tickets Not Available"));
			MvcResult result = mockMvc 
					.perform(MockMvcRequestBuilders.post("/moviebooking/add").header("Authorization", "token")
							.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ticket)))
					.andReturn();
			assertEquals(400, result.getResponse().getStatus());  
   }

   @Test
   public void viewTicket_success() throws Exception {
       Mockito.when(ticketService.viewTickets()).thenReturn(List.of(ticket));
       mockMvc.perform(MockMvcRequestBuilders
                       .get("/moviebooking/view/tickets")
                       .header("Authorization","Bearer "+"token"))
               .andExpect(status().isFound());
   }

   
   
   
   
}
