package com.moviebookingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CustomerNotFoundException;
import com.moviebookingapp.repository.CustomerRepository;
import com.moviebookingapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	// constructor based inject
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	@Autowired
    private SequenceGenerator sequenceGenerator;
	
	@Override
	public Customer addCustomer(Customer customer) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		
			if(customerRepository.existsByLoginId(customer.getLoginId())) {
				throw new CustomerNotFoundException("Customer with this LoginId"+customer.getLoginId()+ "is already Exists");
			}
			else if(customerRepository.existsByEmail(customer.getEmail())) {
				throw new CustomerNotFoundException("Cusomter with this Email"+customer.getEmail()+"is already Existing");
			}else {
				 customer.setLoginId(sequenceGenerator.getSequenceNumber(Customer.SEQUENCE_NAME));
				 Customer savedCustomer = customerRepository.save(customer);
				 return savedCustomer;
			}
		
		
	}

}
