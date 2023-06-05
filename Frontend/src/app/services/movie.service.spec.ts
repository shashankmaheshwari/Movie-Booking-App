// import { TestBed } from '@angular/core/testing';

// import { MovieService } from './movie.service';

// describe('MovieService', () => {
//   let service: MovieService;

//   beforeEach(() => {
//     TestBed.configureTestingModule({});
//     service = TestBed.inject(MovieService);
//   });

//   it('should be created', () => {
//     expect(service).toBeTruthy();
//   });
// });
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MovieService } from './movie.service';

describe('MovieService', () => {
  let service: MovieService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MovieService]
    });

    service = TestBed.inject(MovieService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all movies', () => {
    const dummyMovies = [
      { compositeId: {movieName:"movie1",theatreName:"theatre1"}, movieCost: 100,movieId:1,movieImage:"url1",noOfTicketSold:50,ticketStatus:"SOLD OUT",totalNoOfTickets:50 },
      { compositeId: {movieName:"movie2",theatreName:"theatre2"}, movieCost: 200,movieId:2,movieImage:"url2",noOfTicketSold:55,ticketStatus:"SOLD OUT",totalNoOfTickets:50 }
    ];

    service.getAllMovies().subscribe(movies => {
      expect(movies).toEqual(dummyMovies);
    });

    const req = httpMock.expectOne('http://localhost:8080/moviebooking/all');
    expect(req.request.method).toBe('GET');
    req.flush(dummyMovies);
  });

  it('should get movies based on movie name', () => {
    const searchKey = 'Movie 1';
    const dummyMovies = [
      { compositeId: {movieName:"movie1",theatreName:"theatre1"}, movieCost: 100,movieId:1,movieImage:"url1",noOfTicketSold:50,ticketStatus:"SOLD OUT",totalNoOfTickets:50 },
      { compositeId: {movieName:"movie2",theatreName:"theatre2"}, movieCost: 200,movieId:2,movieImage:"url2",noOfTicketSold:55,ticketStatus:"SOLD OUT",totalNoOfTickets:50 }
    ];

    service.getMovieBasedMovieName(searchKey).subscribe(movies => {
      expect(movies).toEqual(dummyMovies);
    });

    const req = httpMock.expectOne(`http://localhost:8080/moviebooking/movies/search?searchKeyword=${searchKey}`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyMovies);
  });

  // Add more test cases for other methods in MovieService
  it('should delete a movie', () => {
    const movieId = 1;
    const movieName = 'Movie1';

    service.deleteMovie(movieId, movieName).subscribe(response => {
      expect(response).toBeTruthy();
    });

    const req = httpMock.expectOne(`http://localhost:8080/moviebooking/${movieName}/delete/${movieId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });

  it('should get all tickets for admin', () => {
    const dummyTickets = [
      { id: 1, movie: 'Movie 1', user: 'User 1' },
      { id: 2, movie: 'Movie 2', user: 'User 2' }
    ];

    service.getAllTicketsAdmin().subscribe(tickets => {
      expect(tickets).toEqual(dummyTickets);
    });

    const req = httpMock.expectOne('http://localhost:8080/moviebooking/view/tickets');
    expect(req.request.method).toBe('GET');
    req.flush(dummyTickets);
  });

  // Add more test cases for the remaining methods in Movi test cases for the other methods in the MovieService class using a similar structure. Remember to mock the HTTP requests, verify the request methods, provide appropriate mock data, and assert the expected behavior of the service methods.








});
