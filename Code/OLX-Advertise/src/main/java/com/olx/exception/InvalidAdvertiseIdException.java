package com.olx.exception;


public class InvalidAdvertiseIdException extends RuntimeException { // Uncheked Exception

	private String message;

	public InvalidAdvertiseIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidAdvertiseIdException() {
		this.message="";
	}

	@Override
	public String toString() {
		return "Advertise Id is invalid" + this.message;
	}



}
