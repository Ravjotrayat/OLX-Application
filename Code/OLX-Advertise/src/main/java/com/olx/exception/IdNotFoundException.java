package com.olx.exception;

public class IdNotFoundException extends RuntimeException {

	private String message;

	public IdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public IdNotFoundException() {
		this.message="";
	}

	@Override
	public String toString() {
		return "Entered id is not present : " +this.message;
	}




}
