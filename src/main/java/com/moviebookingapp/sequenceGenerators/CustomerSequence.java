package com.moviebookingapp.sequenceGenerators;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "db_sequence")
public class CustomerSequence {

	@Id
	private String id;

	private int seq;
}
