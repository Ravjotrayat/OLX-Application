package com.olx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/advertise")
@RestController
public class MasterDataController {
	
	@GetMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllCategory()
	{
		return new ResponseEntity<>("Fetching all the categories of advertisement",HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/status",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllCategoryStatus()
	{
		return new ResponseEntity<>("Fetching all the STATUS of categories of advertisement",
											HttpStatus.ACCEPTED);
		
	}

}
