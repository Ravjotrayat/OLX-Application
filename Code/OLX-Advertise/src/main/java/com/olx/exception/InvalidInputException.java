package com.olx.exception;

public class InvalidInputException extends RuntimeException{

	private String message;

	public InvalidInputException(String message) {
		super();
		this.message = message;
	}

	public InvalidInputException() {
		this.message="";
	}

	@Override
	public String toString() {
		return "Invalid input for Sorting by name : " + this.message;
	}
}
