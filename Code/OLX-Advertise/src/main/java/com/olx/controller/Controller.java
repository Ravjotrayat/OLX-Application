package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDto;
import com.olx.service.AdvertiseService;
@RestController
@RequestMapping("/advertise")
public class Controller {

//	@PostMapping
//	private String addAdvertise()
//	{
//		return null;
//	}

	@Autowired
	@Qualifier("MySQl_DB")
	AdvertiseService advertiseService;

	@GetMapping("/ad")
	public ResponseEntity<List<AdvertiseDto>>  getAllAdvertise()
	{
		return new ResponseEntity<>(advertiseService.getAllAdvertise(), HttpStatus.OK);
	}

	@GetMapping("/ad/{id}")
	public ResponseEntity<AdvertiseDto>  getAdvertiseById(@PathVariable("id") int id )
	{
		return new ResponseEntity<>(advertiseService.getAdvertiseById(id),HttpStatus.OK);
	}

	@PostMapping("/ad/create")
	public ResponseEntity<AdvertiseDto> createAdvertises(AdvertiseDto advertiseDto)
	{
		AdvertiseDto advertiseDto2=advertiseService.createAdvertise(advertiseDto);
		return new ResponseEntity<AdvertiseDto>(advertiseDto2,HttpStatus.CREATED);
	}


}
