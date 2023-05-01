package com.moviebookingapp.exception;

public class MovieFoundException extends Exception{
	  private static final long serialVersionUID=1L;
	   
	   public MovieFoundException() {
		   
	   }
	   public MovieFoundException(String message) {
		  super(message);
	   } 
}
