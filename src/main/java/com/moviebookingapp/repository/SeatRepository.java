package com.moviebookingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.entities.Seat;
@Repository
public interface SeatRepository extends MongoRepository<Seat,Integer>{
	Seat findBySeatNumber(int seatNumber);
	
	 @Query(value = "{$and:[{'seatNumber':?0},{'movie.movieId':?1}] }")
     Seat findBySeatNumberAndMovieId(int seatNumber, int id);
	
}
