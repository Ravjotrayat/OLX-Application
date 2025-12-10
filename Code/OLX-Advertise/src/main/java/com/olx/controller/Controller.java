package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.olx.dto.AdvertiseDto;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.service.AdvertiseService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/advertise")
public class Controller {

	@Autowired
	@Qualifier("MySQl_DB")
	AdvertiseService advertiseService;

	@Operation(description = "Get all advertise")
	@GetMapping(value = "/ad",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdvertiseDto>>  getAllAdvertise()
	{
		return new ResponseEntity<>(advertiseService.getAllAdvertise(), HttpStatus.OK);
	}

	@GetMapping(value = "/ad/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDto>  getAdvertiseById(@PathVariable("id") int id ) 
	{
		return new ResponseEntity<AdvertiseDto>(advertiseService.getAdvertiseById(id),HttpStatus.OK);
	}

	
	@PostMapping(value = "/ad/create",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDto> createAdvertises(@RequestBody AdvertiseDto advertiseDto)
	{
		AdvertiseDto advertiseDto2=advertiseService.createAdvertise(advertiseDto);
		return new ResponseEntity<AdvertiseDto>(advertiseDto2,HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/ad/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDto> updateAdvertises(@PathVariable("id") int id, 
							@RequestBody AdvertiseDto advertiseDto)
	{
		AdvertiseDto advertiseDto2=advertiseService.updateAdvertise(id,advertiseDto);
		return new ResponseEntity<AdvertiseDto>(advertiseDto2,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/ad/{id}")
	public ResponseEntity<Boolean> deleteAdvertiseByid(@PathVariable("id") int id )
	{
		if(advertiseService.deleteAdvertiseByid(id)==true)
		return new ResponseEntity<Boolean>(true,HttpStatus.ACCEPTED);
		else 
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
	}
	
//	Local Exceptional Handler
	@ExceptionHandler(value = {InvalidAdvertiseIdException.class})
	public ResponseEntity<Object> handleInvalidid(Exception ex, WebRequest request ) throws Exception
	{
		String clientMessage=ex.toString();
		return new ResponseEntity<Object>(clientMessage, HttpStatus.CONFLICT);
	}
	

}
