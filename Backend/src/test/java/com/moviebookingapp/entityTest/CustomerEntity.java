package com.moviebookingapp.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.moviebookingapp.entities.Customer;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class CustomerEntity {
	  Customer customer = new Customer();
	    @Test
	    public void setCustomerFirstName(){
	        customer.setFirstName("firstName");
	        assertEquals("firstName",customer.getFirstName());
	    }
	    @Test
	    public void setCustomerLastName(){
	        customer.setLastName("lastName");
	        assertEquals("lastName",customer.getLastName());
	    }
	    @Test
	    public void setCustomerEmailIdTest(){
	        customer.setEmail("user@email.com");
	        assertEquals("user@email.com",customer.getEmail());
	    }
	    @Test
	    public void setCustomerLoginId(){
	        customer.setLoginId(1);
	        assertEquals(1,customer.getLoginId()); 
	    }
	    @Test
	    public void setCustomerUserName(){
	        customer.setUserName("userName");
	        assertEquals("userName",customer.getUserName());
	    }
	    @Test
	    public void setCustomerPassword(){
	        customer.setPassword("pass");
	        assertEquals("pass",customer.getPassword());
	    }
	    @Test
	    public void setCustomerConfirmPassword(){
	        customer.setConfirmPassword("pass");
	        assertEquals("pass",customer.getConfirmPassword());
	    }
	    @Test
	    public void setCustomerContactNo(){
	        customer.setContactNumber("1234567890");
	        assertEquals("1234567890",customer.getContactNumber());
	    }

	    @Test
	    public void setCustomerRole(){
	        customer.setRole("USER");
	        assertEquals("USER",customer.getRole());
	    }
	    @Test
	    void notBlankTest(){
	        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	        Customer user1 = new Customer(1,"","","","","","","","");
	        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(user1);
	        assertEquals(6,constraintViolations.size());
	    }

}
