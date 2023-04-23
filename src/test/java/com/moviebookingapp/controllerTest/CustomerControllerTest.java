//package com.moviebookingapp.controllerTest;
//
//
//
//import org.junit.jupiter.api.Test;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.moviebookingapp.entities.Customer;
//import com.moviebookingapp.exception.CustomerFoundException;
//import com.moviebookingapp.service.CustomerService;
//import com.moviebookingapp.service.impl.CustomerServiceImpl;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.hamcrest.CoreMatchers.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@WebMvcTest
//public class CustomerControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private CustomerService customerService;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Test
//	 public void givenCustomerObject_whenCreateCustomer_thenReturnSavedCustomer() throws CustomerFoundException{
//	//	 int customerId=1;
//		 
//		 // given - precondition or setup
//		 Customer customer=Customer.builder()
//				                   .firstName("Shashank")
//				                   .lastName("Maheshwari")
//				                   .email("shashank@gmail.com")
//				                   .contactNumber("9898764568")
//				                   .password("abc")
//				                   .confirmPassword("abc")
//				                   .userName("shashanl010")
//				                   .role("USER")
//				                   .build();
//		 
//		given(customerService.addCustomer(any(Customer.class)))
//         .willAnswer((invocation)-> invocation.getArgument(0));
//                 
//	
//			
//		 // when - action or behavior that we are going test
//		 ResultActions response=mockMvc.perform(post("/register")
//				       .contentType(MediaType.APPLICATION_JSON)
//				       .content(objectMapper.writeValueAsString(customer)));
//		 
//		 
//		 // then - verify the result or output using assert statements
//		 response.andDo(print())
//		         .andExpect(status().isCreated())
//		         .andExpect(jsonPath("$.fistName",
//		        		 is(customer.getFirstName())))
//		         .andExpect(jsonPath("$.lastName",
//		        		 is(customer.getLastName())))
//		         .andExpect(jsonPath("$.email",
//		        		 is(customer.getEmail())))
//		         .andExpect(jsonPath("$.password",
//		        		 is(customer.getPassword())))
//		         .andExpect(jsonPath("$.contactNumber",
//		        		 is(customer.getContactNumber())));
//		        
//	 }
//
//}
