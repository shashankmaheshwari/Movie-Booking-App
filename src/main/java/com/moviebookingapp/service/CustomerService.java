package com.moviebookingapp.service;


import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.exception.CustomerNotFoundException;

public interface CustomerService {

	public Customer addCustomer(Customer customer)throws CustomerFoundException;
	
    public String forgotPassword(String userName,String Password) throws CustomerNotFoundException, CommonException;	
	
	

}
