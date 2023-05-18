package com.moviebookingapp.sequenceGenerators;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "DB_sequence")
public class DBSequence {
    @Id
	private String id;
	private int seq;
}
