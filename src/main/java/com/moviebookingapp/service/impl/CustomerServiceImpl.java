package com.moviebookingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.repository.CustomerRepository;
import com.moviebookingapp.sequenceGenerators.DBSequenceGenerator;
import com.moviebookingapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	// constructor based inject

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Autowired
	private DBSequenceGenerator sequenceGenerator;
	
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerFoundException {

		// For unique Login Id
		if (customerRepository.existsByLoginId(customer.getLoginId())) {
			throw new CustomerFoundException(
					"Customer with this LoginId " + customer.getLoginId() + " is already Exists");
		}
		
		// For unique Email
		else if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new CustomerFoundException(
					"Cusomter with this Email " + customer.getEmail() + " is already Existing");
		}
		// for Unique UserName
		else if(customerRepository.existsByUserName(customer.getUserName())) {
			throw new CustomerFoundException("Customer with this User Name "+customer.getUserName()+" is already Exists Please Choose new one..");
		}
		// check for givenPassword and confirmPassword Condition
		else if (!(customer.getPassword().equals(customer.getConfirmPassword()))) {
			throw new CustomerFoundException("Customer Passwords are not same");
		} 
		else {
			customer.setLoginId(sequenceGenerator.getSequenceNumber(Customer.SEQUENCE_NAME));
			// Encoding the password
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			customer.setConfirmPassword(passwordEncoder.encode(customer.getConfirmPassword()));
			customer.setRole("USER");
			Customer savedCustomer = customerRepository.save(customer);
			return savedCustomer;
		}

	}

}
