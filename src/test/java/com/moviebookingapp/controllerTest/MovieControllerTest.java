package com.moviebookingapp.controllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.MovieCompositeKey;
import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.impl.MovieServiceImpl;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieServiceImpl movieService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenMovieObject_whenMovie_thenReturnSavedMovie() throws JsonProcessingException, Exception {
		
		
		
		MovieCompositeKey movieComp=MovieCompositeKey.builder()
				                      .movieName("3idiot")
				                      .theatreName("PVR")
				                      .build();
		Movie movie=Movie.builder()
				    .totalNoOfTickets(23)
				    .noOfTicketsSold(7)
				    .movieCost(54)
				    .ticketStatus("BOOK ASAP")
				    .compositeId(movieComp)
				    .build();
		
		given(movieService.addMovie(any(Movie.class)))
		      .willAnswer((invocation)->invocation.getArgument(0));
		
		ResultActions response=mockMvc.perform(post("/moviebooking/addMovie")
				                .contentType(MediaType.APPLICATION_JSON)
				                .content(objectMapper.writeValueAsString(movie)));
		
		response.andDo(print()).
		andExpect(status().isCreated())
        .andExpect(jsonPath("$.totalNoOfTickets",
       		 is(movie.getTotalNoOfTickets())))
        .andExpect(jsonPath("$.noOfTicketsSold",
       		 is(movie.getNoOfTicketsSold())))
        .andExpect(jsonPath("$.movieCost",
       		 is(movie.getMovieCost())))
        .andExpect(jsonPath("$.compositeId",
          		 is(movie.getCompositeId())))
        .andExpect(jsonPath("$.ticketStatus",
          		 is(movie.getTicketStatus())));
		
	}

}
