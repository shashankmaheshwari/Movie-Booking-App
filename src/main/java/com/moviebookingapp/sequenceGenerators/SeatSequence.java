package com.moviebookingapp.sequenceGenerators;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="seat_sequence")
public class SeatSequence {
	 @Id
	 private String id;
     
	 private int seq;
}
