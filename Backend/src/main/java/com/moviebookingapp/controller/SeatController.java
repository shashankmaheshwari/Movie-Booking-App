package com.moviebookingapp.controller;

import com.moviebookingapp.entities.Seat;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.repository.SeatRepository;
import com.moviebookingapp.service.impl.SeatServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "*")
public class SeatController {
    Logger logger = LoggerFactory.getLogger(TicketController.class);
    @Autowired
   private SeatServiceImpl seatService ;

    @GetMapping("/seats/{movieId}")
    public ResponseEntity<?>  getAllSeats(@PathVariable  int  movieId) throws CommonException {
        ResponseEntity<?> response = null;

        List<Seat> getSeats=seatService.getAllSeats(movieId);
        if(getSeats.size()==0){
            throw new CommonException("Seat not found for this particular movie");
        }
        logger.info("-------Seats Found---------");
        return new ResponseEntity<>(getSeats, HttpStatus.OK);
    }

}
