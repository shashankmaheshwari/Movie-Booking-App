package com.moviebookingapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.entities.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer>{
   boolean existsByLoginId(int loginId);
   boolean existsByEmail(String email);
   boolean existsByUserName(String userName);
   
   Optional<Customer> findByUserName(String userName);
   
}
