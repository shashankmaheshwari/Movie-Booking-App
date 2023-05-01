package com.moviebookingapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.exception.CustomerNotFoundException;
import com.moviebookingapp.repository.CustomerRepository;
import com.moviebookingapp.sequenceGenerators.DBSequenceGenerator;
import com.moviebookingapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

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
		else if (customerRepository.existsByUserName(customer.getUserName())) {
			throw new CustomerFoundException("Customer with this User Name " + customer.getUserName()
					+ " is already Exists Please Choose new one..");
		}
		// check for givenPassword and confirmPassword Condition
		else if (!(customer.getPassword().equals(customer.getConfirmPassword()))) {
			throw new CustomerFoundException("Customer Passwords are not same");
		} else {
			customer.setLoginId(sequenceGenerator.getSequenceNumber(Customer.SEQUENCE_NAME));
			// Encoding the password
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			customer.setConfirmPassword(passwordEncoder.encode(customer.getConfirmPassword()));
			customer.setRole("USER");
			Customer savedCustomer = customerRepository.save(customer);
			return savedCustomer;
		}

	}

	@Override
	public String forgotPassword(String userName, String password) throws CustomerNotFoundException, CommonException {

		Optional<Customer> customer = customerRepository.findByUserName(userName);
		if (customer.isPresent()) {
			if (password == null || password == "") {
				throw new CommonException("Password can't be empty or NULL");
			} else {
				customer.get().setPassword(passwordEncoder.encode(password));
				customer.get().setConfirmPassword(passwordEncoder.encode(password));
				customerRepository.save(customer.get());
				return "Password is updated successfully";
			}

		} else {
			throw new CustomerNotFoundException("Customer with userName " + userName + "does not exists");
		}

	}

}
