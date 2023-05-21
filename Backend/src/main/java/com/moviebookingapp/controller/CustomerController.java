package com.moviebookingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.exception.CustomerNotFoundException;
import com.moviebookingapp.service.CustomerService;
import com.moviebookingapp.service.impl.JwtService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<?> addCustomer(@RequestBody @Valid Customer customer) throws CustomerFoundException {
		ResponseEntity<?> response = null;
		if (customer == null) {
			logger.error("-------------Please Enter Customer Values--------");
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			customer = customerService.addCustomer(customer);
			response = new ResponseEntity<>("Customer is Added", HttpStatus.CREATED);
			logger.info("----------------Customer Created------------------");
		}
		return response;

	}

	@PostMapping("/{userName}/forgot")
	//@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> forgotPassword(
			@PathVariable String userName, @RequestBody String password)
			throws CustomerNotFoundException, CommonException {
		ResponseEntity<String> response = null;
		String username ="admin";
//		jwtService.extractUsername(token.substring(7));
		//@RequestHeader("Authorization") String token,
		if (username.equals(userName)) {

			String res = customerService.forgotPassword(userName, password);

			response = new ResponseEntity<>(res, HttpStatus.OK);
			logger.info("----------------Password Reset------------------");

		} else {
			throw new CommonException("User is not authorize");
		}

		return response;

	}

		}
