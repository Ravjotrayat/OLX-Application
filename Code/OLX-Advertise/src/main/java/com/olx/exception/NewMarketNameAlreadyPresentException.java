package com.olx.exception;

public class NewMarketNameAlreadyPresentException extends RuntimeException  {

	private String message;

	public NewMarketNameAlreadyPresentException(String message) {
		super();
		this.message = message;
	}

	public NewMarketNameAlreadyPresentException() {
		this.message="";
	}

	@Override
	public String toString() {
		return "Advertise Market name is already present" + this.message;
	}
}
