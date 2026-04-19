package com.olx.exception;

<<<<<<< HEAD

=======
//@ResponseStatus(HttpStatus.NOT_FOUND)
>>>>>>> f458163ed9d3a3a5c4b794b11cf2750efb4d4dd2
public class InvalidAdvertiseIdException extends RuntimeException { // Uncheked Exception

	private String message;

	public InvalidAdvertiseIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidAdvertiseIdException() {
		this.message="";
	}

<<<<<<< HEAD
=======
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

>>>>>>> f458163ed9d3a3a5c4b794b11cf2750efb4d4dd2
	@Override
	public String toString() {
		return "Advertise Id is invalid" + this.message;
	}



}
