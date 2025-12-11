package com.olx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RestController
public class Controller {

	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<String> getAuthenticate()
	{
		return new ResponseEntity<String>("Authenticate the User",HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/logout")
	public ResponseEntity<String> getLogout()
	{
		return new ResponseEntity<String>("Logout Successfully",HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<String> newUserLogin()
	{
		return new ResponseEntity<String>("User Login successfully",HttpStatus.CREATED);
	}
	
	@GetMapping(value = "")
	public ResponseEntity<String> getUserInformation()
	{
		return new ResponseEntity<String>("Get user details",HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/token/validate")
	public ResponseEntity<String> validateToken()
	{
		return new ResponseEntity<String>("Validated the token successfully",HttpStatus.OK);
	}
}
