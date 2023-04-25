package com.moviebookingapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "customer")
public class Customer {
	@Transient
	public static final String SEQUENCE_NAME = "customer_sequence";
	
	@Id
	private int loginId;
	
	@NotBlank(message="User Name cant be empty")
	private String userName;

	@NotBlank(message = " Customer First Name can't be empty ")
	private String firstName;

	@NotBlank(message = " Customer Last Name can't be empty ")
	private String lastName;

	@Email(message = "Invalid Email address")
	@Indexed(unique = true)
	private String email;

	@NotBlank(message = " Password  can't be empty ")
	private String password;

	@NotBlank(message = " Confirm Password  can't be empty ")
	private String confirmPassword;

	@Pattern(regexp = "^\\d{10}$", message = " Invalid Mobile Number Entered ")
	private String contactNumber;

	private String role;
}
