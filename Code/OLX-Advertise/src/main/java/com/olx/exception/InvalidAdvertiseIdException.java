package com.olx.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidAdvertiseIdException extends RuntimeException { // Uncheked Exception

	private String message;

	public InvalidAdvertiseIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidAdvertiseIdException() {
		this.message="";
	}

	public InvalidAdvertiseIdException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidAdvertiseIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAdvertiseIdException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return "Advertise Id is invalid" + this.message;
	}



}
