package com.shopapi.revature.Exceptions;

public class InvalidLoginException extends Exception { 
	
	private static final long serialVersionUID = 1L;

	public InvalidLoginException(String errorMessage) {
        super(errorMessage);
	}
}
