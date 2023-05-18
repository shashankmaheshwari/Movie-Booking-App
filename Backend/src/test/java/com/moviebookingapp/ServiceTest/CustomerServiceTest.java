package com.moviebookingapp.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.moviebookingapp.entities.Customer;
import com.moviebookingapp.exception.CustomerFoundException;
import com.moviebookingapp.repository.CustomerRepository;
import com.moviebookingapp.sequenceGenerators.DBSequence;
import com.moviebookingapp.sequenceGenerators.DBSequenceGenerator;
import com.moviebookingapp.service.impl.CustomerServiceImpl;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private DBSequenceGenerator sequenceGenerator;

	private Customer customer;

	@Mock
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() {
		customer = new Customer(1, "user123", "userFirst", "userLast", "abc@gmail.com", "pass123", "pass123",
				"1111111111", "user");

	}
	@DisplayName("Junit test for addCustomer Method")
	@Test
	public void givenCustomer_whenSaveCustomer_thenReturnCustomer() throws Exception {
		when(customerRepository.existsByEmail(customer.getEmail())).thenReturn(false);
		when(customerRepository.existsByLoginId(customer.getLoginId())).thenReturn(false);

		when(customerRepository.save(customer)).thenReturn(customer);
		Customer customer1 = customerService.addCustomer(customer);
		assertThat(customer1).isNotNull(); 
	}

	// JUnit test for savedCustomer method
	@DisplayName("Junit test for addCustomer method throws Exception")
	@Test
	public void givenExistingEmail_whenSaveCustomer_thenThrowException() {
		// given
		given(customerRepository.existsByUserName(customer.getUserName())).willReturn(true);
		// when
		org.junit.jupiter.api.Assertions.assertThrows(CustomerFoundException.class, () -> {
			customerService.addCustomer(customer);
		});
		// then 
		verify(customerRepository, never()).save(any(Customer.class));  

	}
}
