package com.moviebookingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CustomerNotFoundException;
import com.moviebookingapp.service.CustomerService;

@RestController
public class CustomerController {
     @Autowired
	private CustomerService customerService;
	@PostMapping("/register")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
        ResponseEntity<String> response=null;
        if(customer==null) {
        	response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
        	customer=customerService.addCustomer(customer);
        	response=new ResponseEntity<>("Customer is Added",HttpStatus.CREATED);
        }
		return response;

	}
}
