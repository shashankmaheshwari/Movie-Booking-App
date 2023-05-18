package com.moviebookingapp.exception;

public class CustomerFoundException extends Exception{
 
	private static final long serialVersionUID = 1L;
	public CustomerFoundException() {
		
	}
	public CustomerFoundException(String message) {
		super(message); 
	}
}
