package com.moviebookingapp.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.entities.Movie;
import com.moviebookingapp.entities.MovieCompositeKey;
import com.moviebookingapp.exception.MovieNotFoundException;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.sequenceGenerators.DBSequence;
import com.moviebookingapp.sequenceGenerators.DBSequenceGenerator;
import com.moviebookingapp.service.impl.MovieServiceImpl;
import com.moviebookingapp.service.impl.SeatServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieServiceImpl movieService;

	@Mock
	private DBSequenceGenerator sequenceGenerator;

	@Mock
	private SeatServiceImpl seatService;

	private Movie movie;

	@BeforeEach
	public void setup() {
		MovieCompositeKey movieComp = MovieCompositeKey.builder().movieName("3idiot").theatreName("PVR").build();
		movie = Movie.builder().movieId(1).totalNoOfTickets(23).noOfTicketsSold(7).movieCost(54)
				.ticketStatus("BOOK ASAP").compositeId(movieComp).build();
	}

	@DisplayName("Junit test for add movies")
	@Test
	public void addMovieTest_Success() throws MovieNotFoundException {
		when(movieRepository.save(movie)).thenReturn(movie);
		Movie movie2 = movieService.addMovie(movie);
		assertThat(movie2).isNotNull();
	}

	@DisplayName("Junit test for add movies (Exeption Scenario)")
	@Test
	public void addMovieTest_Exception() {
		when(movieRepository.findByCompositeIdMovieNameAndCompositeIdTheatreName(movie.getCompositeId().getMovieName(),
				movie.getCompositeId().getTheatreName())).thenReturn(movie);
		MovieNotFoundException e = assertThrows(MovieNotFoundException.class, () -> {
			movieService.addMovie(movie);
		});
		verify(movieRepository, never()).save(movie);
		assertEquals("Movie already exists", e.getMessage());
	}

	@DisplayName("Junit test for getting all movies")
	@Test
	public void givenMovieList_whenGetAllMovies_thenReturnMoviesList() throws MovieNotFoundException {
		MovieCompositeKey movieComp1 = MovieCompositeKey.builder().movieName("URI").theatreName("Carnival").build();
		Movie movie1 = Movie.builder().movieId(2).totalNoOfTickets(13).noOfTicketsSold(4).movieCost(154)
				.ticketStatus("BOOK ASAP").compositeId(movieComp1).build();

		given(movieRepository.findAll()).willReturn(List.of(movie, movie1));

		List<Movie> movieList = movieService.viewMovieList();

		assertThat(movieList).isNotNull();

		assertThat(movieList.size()).isEqualTo(2);

	}

	@DisplayName("Junit test for getting all movies (negativeScenario)")
	@Test
	public void givenMovieList_whenGetAllMovies_thenReturnEmptyMoviesList() throws MovieNotFoundException {
		MovieCompositeKey movieComp1 = MovieCompositeKey.builder().movieName("URI").theatreName("Carnival").build();
		Movie movie1 = Movie.builder().totalNoOfTickets(13).noOfTicketsSold(4).movieCost(154).ticketStatus("BOOK ASAP")
				.compositeId(movieComp1).build();

		given(movieRepository.findAll()).willReturn(Collections.emptyList());

		List<Movie> movieList = movieService.viewMovieList();

		assertThat(movieList).isEmpty();

		assertThat(movieList.size()).isEqualTo(0);

	}

	@DisplayName("Junit test for getting movie based on movie Name")
	@Test
	public void givenMovieName_whenGetMovieName_thenReturnListMovieObject() throws MovieNotFoundException {

		given(movieRepository.findAllByCompositeIdMovieName("3idiot")).willReturn(List.of(movie));

		List<Movie> savedMovie = movieService.searchMovie(movie.getCompositeId().getMovieName());

		assertThat(savedMovie).isNotNull();
	}

	@DisplayName("Junit test for getting movie based on movie Name and id")
	@Test
	public void givenMovieNameMovieId_whenGetMovieNameMovieId_thenReturnMovieObject() throws MovieNotFoundException {

		given(movieRepository.findByMovieIdAndCompositeIdMovieName(1, "3idiot")).willReturn((movie));

		Movie savedMovie = movieService.viewMovie(movie.getMovieId(), movie.getCompositeId().getMovieName());

		assertThat(savedMovie).isNotNull();
	}

	@DisplayName("Junit test for getting movie based on Theatre Name")
	@Test
	public void givenTheatreName_whenGetTheatreName_thenReturnListMovieObject() throws MovieNotFoundException {

		given(movieRepository.findAllByCompositeIdTheatreName("PVR")).willReturn(List.of(movie));

		List<Movie> savedMovie = movieService.searchMovieOnBasisTheatreName(movie.getCompositeId().getTheatreName());

		assertThat(savedMovie).isNotNull();
	}

	@DisplayName("Junit test for Deleting a movie ")
	@Test
	public void givenMovieId_whenDeleteMovie_thenNothing() throws MovieNotFoundException {

		given(movieRepository.findByMovieId(1)).willReturn(movie);

		movieService.removeMovie(1);

		verify(movieRepository, times(1)).findByMovieId(1);
	}

}
