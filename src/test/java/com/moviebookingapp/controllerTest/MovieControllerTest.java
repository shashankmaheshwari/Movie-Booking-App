package com.moviebookingapp.controllerTest;

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
import com.moviebookingapp.controller.MovieController;
import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.MovieCompositeKey;
import com.moviebookingapp.exception.ApplicationExceptionHandler;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.exception.MovieFoundException;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.service.impl.MovieServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;



@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class MovieControllerTest {

	private MockMvc mockMvc;

	@Mock
	private MovieServiceImpl movieService;

	@InjectMocks
	private MovieController movieController;

	private ObjectMapper objectMapper = new ObjectMapper();

	private Movie movie;

	private MovieCompositeKey movieComp;
	
    @Before
	public void setUp() {

		movieComp = MovieCompositeKey.builder().movieName("3idiot").theatreName("PVR").build();
		movie = Movie.builder().totalNoOfTickets(73).noOfTicketsSold(7).movieCost(54).ticketStatus("BOOK ASAP")
				.compositeId(movieComp).movieId(1).build();
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).setControllerAdvice(new  ApplicationExceptionHandler()).build();

	}

	@Test
	public void addMovie_successTest() throws Exception {
        Mockito.when(movieService.addMovie(any(Movie.class))).thenReturn(movie);
		MvcResult result = mockMvc 
				.perform(MockMvcRequestBuilders.post("/moviebooking/addMovie").header("Authorization", "token")
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(movie)))
				.andReturn();
		assertEquals(201, result.getResponse().getStatus());  

	}
	@Test
	public void addMovie_MovieFoundException() throws  Exception {
		Mockito.when(movieService.addMovie(movie)).thenThrow(new MovieFoundException("Movie already exists"));
		mockMvc.perform(MockMvcRequestBuilders.post("/moviebooking/addMovie")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(objectMapper.writeValueAsString(movie)))
		            .andExpect(status().isBadRequest())
		            .andExpect(result->assertTrue(result.getResolvedException()instanceof MovieFoundException))
		            .andExpect(jsonPath("$.errorMessage").value("Movie already exists"));
		            
	}
	@Test
	public void getAllMovies_FoundTest() throws Exception {
		Mockito.when(movieService.viewMovieList()).thenReturn(List.of(movie));
		mockMvc.perform(MockMvcRequestBuilders
		               .get("/moviebooking/all")
		               .header("Authorization","Bearer "+"token"))
		               .andExpect(status().isFound());
	}
	
	@Test
	public void getAllMovies_NotFoundTest() throws Exception {
		Mockito.when(movieService.viewMovieList()).thenReturn(List.of());
		mockMvc.perform(MockMvcRequestBuilders
		               .get("/moviebooking/all")
		               .header("Authorization","Bearer "+"token"))
		               .andExpect(status().isNotFound())
		               .andExpect(content().string("Movie Not Found"));
	}
	
	@Test
	public void searchByMovieName_FoundTest() throws Exception {
		Mockito.when(movieService.searchMovie(movieComp.getMovieName())).thenReturn(List.of(movie));
		mockMvc.perform(MockMvcRequestBuilders
	               .get("/moviebooking/movies/search/3idiot")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isFound());
	}
	
	@Test
	public void searchByMovieName_NotFoundTest() throws Exception {
		Mockito.when(movieService.searchMovie(movieComp.getMovieName())).thenReturn(List.of());
		mockMvc.perform(MockMvcRequestBuilders
	               .get("/moviebooking/movies/search/3idiot")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isNotFound())
	               .andExpect(content().string("Movie Not Found"));
	}
	@Test
	public void searchByMovieIdMovieName_FoundTest() throws Exception {
		Mockito.when(movieService.viewMovie(movie.getMovieId(),movieComp.getMovieName())).thenReturn((movie));
		mockMvc.perform(MockMvcRequestBuilders
	               .get("/moviebooking/viewMovie/1/3idiot")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isFound());
	}
	@Test
	public void searchByMovieIdMovieName_NotFoundTest() throws Exception {
		Mockito.when(movieService.viewMovie(movie.getMovieId(),movieComp.getMovieName())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders
	               .get("/moviebooking/viewMovie/1/3idiot")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isNotFound());
	}
	@Test
	public void deleteMovie_SuccessTest() throws Exception {
		Mockito.when(movieService.viewMovie(movie.getMovieId(),movieComp.getMovieName())).thenReturn((movie));
		Mockito.when(movieService.removeMovie(movie.getMovieId())).thenReturn(movie);
		mockMvc.perform(MockMvcRequestBuilders
	               .delete("/moviebooking/3idiot/delete/1")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isOk());
	               
	}
	@Test
	public void deleteMovie_MovieNotFoundTest() throws Exception {
		Mockito.when(movieService.viewMovie(movie.getMovieId(),movieComp.getMovieName())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders
	               .delete("/moviebooking/3idiot/delete/1")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isNotFound());
	               
	}
	@Test
	public void updateTicketStatus_sucessTest() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.put("/moviebooking/3idiot/PVR/update/ticket")
				 .header("Authorization", "token")
				 .contentType(MediaType.APPLICATION_JSON))
				   .andExpect(status().isOk());
	}
	@Test
	public void updateTicketStatus_ExceptionTest() throws Exception {
		 doThrow(new MovieNotFoundException("Movie with this id and Name does not exist")).when(movieService).updateTicketStatus(movieComp.getMovieName(), movieComp.getTheatreName());
		 mockMvc.perform(MockMvcRequestBuilders.put("/moviebooking/3idiot/PVR/update/ticket")
				 .header("Authorization", "token")
				 .contentType(MediaType.APPLICATION_JSON))
				   .andExpect(status().isNotFound());
	}
	@Test
	public void searchMovieOnTheBasisOfTheatre() throws Exception {
		Mockito.when(movieService.searchMovieOnBasisTheatreName(movieComp.getTheatreName())).thenReturn(List.of(movie));
		mockMvc.perform(MockMvcRequestBuilders
	               .get("/moviebooking/movies/search/theatre/PVR")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isFound());

	}
	
	@Test
	public void searchMovieOnTheBasisOfTheatre_ExceptionTest() throws Exception {
		Mockito.when(movieService.searchMovieOnBasisTheatreName(movieComp.getTheatreName())).thenReturn(List.of());
		mockMvc.perform(MockMvcRequestBuilders
	               .get("/moviebooking/movies/search/theatre/PVR")
	               .header("Authorization","Bearer "+"token"))
	               .andExpect(status().isNotFound());  

	}

}
