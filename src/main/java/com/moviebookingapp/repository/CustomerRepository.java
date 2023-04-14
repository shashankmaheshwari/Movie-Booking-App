package com.moviebookingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.entities.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer>{
   boolean existsByLoginId(int loginId);
   boolean existsByEmail(String email);
   
}
