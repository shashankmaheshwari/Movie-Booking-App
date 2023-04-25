package com.moviebookingapp.sequenceGenerators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class DBSequenceGenerator {
	@Autowired
	private MongoOperations mongoOperations;

	public int getSequenceNumber(String sequenceName) {
		// get Sequence Number
		Query query = new Query(Criteria.where("id").is(sequenceName));

		// Update Sequence
		Update update = new Update().inc("seq", 1);

		// Modify Sequence
		DBSequence counter = mongoOperations.findAndModify(query, update,
				FindAndModifyOptions.options().returnNew(true).upsert(true), DBSequence.class);

		return !Objects.isNull(counter) ? counter.getSeq() : 1; 
	}
}
