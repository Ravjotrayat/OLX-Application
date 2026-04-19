package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Global Exceptional Handler
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

<<<<<<< HEAD
//	For fetching the id
=======
>>>>>>> f458163ed9d3a3a5c4b794b11cf2750efb4d4dd2
	@ExceptionHandler(value = {InvalidAdvertiseIdException.class})
	public ResponseEntity<Object> handleInvalidid(Exception ex, WebRequest request ) throws Exception
	{
		String clientMessage=ex.toString();
		return handleExceptionInternal(ex, clientMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
<<<<<<< HEAD


//	For deleting the Id
	@ExceptionHandler(value = {IdNotFoundException.class})
	public ResponseEntity<Object> handleInvalididException(Exception exception, WebRequest request ) throws Exception
	{
		String ex=exception.toString();
		return handleExceptionInternal(exception, ex, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
=======
	
	

>>>>>>> f458163ed9d3a3a5c4b794b11cf2750efb4d4dd2
}
