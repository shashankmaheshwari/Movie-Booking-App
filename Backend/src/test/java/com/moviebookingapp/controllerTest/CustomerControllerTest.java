package com.moviebookingapp.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.controller.CustomerController;
import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.ApplicationExceptionHandler;
import com.moviebookingapp.exception.CommonException;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.exception.CustomerNotFoundException;
import com.moviebookingapp.service.impl.CustomerServiceImpl;
import com.moviebookingapp.service.impl.JwtService;

import jakarta.servlet.ServletException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	@InjectMocks
	private CustomerController customerController;

	@Mock
	private CustomerServiceImpl customerService;
	
	@Mock
	private JwtService jwtService;

	private MockMvc mockMvc;

	private Customer customer;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Before
	public void setUp() {
		customer = new Customer(1, "shashank", "userFirst", "userLast", "abc@gmail.com", "pass123", "pass123",
				"1111111111", "user");
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(new  ApplicationExceptionHandler()).build();

	}
	@DisplayName("Junit test for addCustomer Controller")
	@Test
	public void givenCustomer_whenSaveCustomer_thenReturnCustomer() throws Exception {
		ResponseEntity<String> response = ResponseEntity.ok("Customer is Added");
		String s = "{  \r\n  \"loginId\":1, \r\n  \"userName\":\"shashankUser1\",\r\n  \"firstName\":\"shashankUsers\",\r\n  \"lastName\":\"a\",\r\n  \"email\":\"shashankusers@gmail.com\",\r\n  \"password\":\"abc\",\r\n  \"confirmPassword\":\"abc\",\r\n  \"contactNumber\":\"8323233431\",\r\n  \"role\":\"USER\"\r\n}";
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON).content(s))
				.andReturn();
		assertEquals(response.getBody(), result.getResponse().getContentAsString());
	}
	@DisplayName("Junit test for addCustomer Controller (Exception->Customer already Exist)")
	@Test
	public void givenCustomer_whenCustomerExist_thenThrowException() throws  Exception {
	    Mockito.when(customerService.addCustomer(any(Customer.class))).thenThrow(new CustomerFoundException("Customer with this LoginId " + customer.getLoginId() + " is already Exists"));
		 mockMvc.perform(MockMvcRequestBuilders.post("/register")
				           .contentType(MediaType.APPLICATION_JSON)
				           .content(objectMapper.writeValueAsString(customer)))
				            .andExpect(status().isBadRequest())
				            .andExpect(result->assertTrue(result.getResolvedException()instanceof CustomerFoundException))
				            .andExpect(jsonPath("$.errorMessage").value("Customer with this LoginId " + customer.getLoginId() + " is already Exists"));
				            
				            
	
	 	
				      
	}
	@DisplayName("Junit test for Forgot Password Controller (Success)")
	@Test
	public void givenAuthorizeCustomer_whenForgotPassword_ThenReturnPasswordReset() throws Exception {
		ResponseEntity<String> response=ResponseEntity.ok("Password is updated successfully");
		Mockito.when(jwtService.extractUsername(anyString())).thenReturn(customer.getUserName());
		Mockito.when(customerService.forgotPassword(customer.getUserName(),"password")).thenReturn("Password is updated successfully");
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/shashank/forgot")
				.header("Authorization", "Bearer token")
				.contentType(MediaType.APPLICATION_JSON)
				.content("password")).andReturn();
		         assertEquals(200,result.getResponse().getStatus());
		         assertEquals(response.getBody(),result.getResponse().getContentAsString());
	}
	@Test
	public void givenNotAuthorizeCustomer_whenForgotPassword_ThenReturnException() throws Exception {
		Mockito.when(jwtService.extractUsername(anyString())).thenReturn("");

		  mockMvc.perform(MockMvcRequestBuilders.post("/shashank/forgot")
				  .header("Authorization", "Bearer token")
				  .contentType(MediaType.APPLICATION_JSON)
				  .content("password"))
				       .andExpect(status().isBadRequest())
				       .andExpect(result-> assertTrue(result.getResolvedException() instanceof CommonException))
				       .andExpect(jsonPath("$.errorMessage").value("User is not authorize"));  
	}
	@Test
	public void forgotPassword_CustomerNotFoundException() throws Exception {
	    Mockito.when(jwtService.extractUsername(anyString())).thenReturn(customer.getUserName());
	    Mockito.when(customerService.forgotPassword(customer.getUserName(), "password")).thenThrow(new CustomerNotFoundException("Customer with userName " + customer.getUserName() + "does not exists"));
	    mockMvc.perform(MockMvcRequestBuilders.post("/shashank/forgot")
	    		.header("Authorization", "Bearer token")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content("password"))
	    		.andExpect(status().isBadRequest())
			       .andExpect(result-> assertTrue(result.getResolvedException() instanceof CustomerNotFoundException))
			       .andExpect(jsonPath("$.errorMessage").value("Customer with userName " + customer.getUserName() + "does not exists"));  
	    
	    
	}
	@Test
	public void forgotPassword_CommonException() throws Exception {
		Mockito.when(jwtService.extractUsername(anyString())).thenReturn(customer.getUserName());
	    Mockito.when(customerService.forgotPassword(customer.getUserName(), "password")).thenThrow(new CommonException("Password can't be empty or NULL"));
	    mockMvc.perform(MockMvcRequestBuilders.post("/shashank/forgot")
	    		.header("Authorization", "Bearer token")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content("password"))
	    		.andExpect(status().isBadRequest())
			       .andExpect(result-> assertTrue(result.getResolvedException() instanceof CommonException))
			       .andExpect(jsonPath("$.errorMessage").value("Password can't be empty or NULL"));   

	}
	
	
}
