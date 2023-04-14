package com.moviebookingapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="customer")
public class Customer {
	@Transient
	public static final String SEQUENCE_NAME="customer_sequence";
	@Id
	 private int loginId;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String password;
	 private String confirmPassword;
	 private int contactNumber;
	 private String role;
}
