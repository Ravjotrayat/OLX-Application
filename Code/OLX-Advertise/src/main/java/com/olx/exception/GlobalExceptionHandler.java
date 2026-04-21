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

//	For fetching the id
	@ExceptionHandler(value = {InvalidAdvertiseIdException.class})
	public ResponseEntity<Object> handleInvalidid(Exception ex, WebRequest request ) throws Exception
	{
		String clientMessage=ex.toString();
		return handleExceptionInternal(ex, clientMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}


//	For deleting the Id
	@ExceptionHandler(value = {IdNotFoundException.class})
	public ResponseEntity<Object> handleInvalididException(Exception exception, WebRequest request ) throws Exception
	{
		String ex=exception.toString();
		return handleExceptionInternal(exception, ex, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {NewMarketNameAlreadyPresentException.class})
	public ResponseEntity<Object> handleNameException(Exception tt, WebRequest request ) throws Exception
	{
		String ex=tt.toString();
		return handleExceptionInternal(tt, ex, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}












