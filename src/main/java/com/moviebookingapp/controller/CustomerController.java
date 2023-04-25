package com.moviebookingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {
	
	Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
     @Autowired
	private CustomerService customerService;
     
	@PostMapping("/register")
	public ResponseEntity<String> addCustomer(@RequestBody @Valid Customer customer) throws CustomerFoundException {
        ResponseEntity<String> response=null;
        if(customer==null) {
        	logger.error("-------------Please Enter Customer Values--------");
        	response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
        	customer=customerService.addCustomer(customer);
        	response=new ResponseEntity<>("Customer is Added",HttpStatus.CREATED);
        	logger.info("----------------Customer Created------------------");
        }
		return response;

	}
}
