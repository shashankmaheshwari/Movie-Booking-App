package com.moviebookingapp.service;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CustomerNotFoundException;

public interface CustomerService {

	public Customer addCustomer(Customer customer)throws CustomerNotFoundException;
}
