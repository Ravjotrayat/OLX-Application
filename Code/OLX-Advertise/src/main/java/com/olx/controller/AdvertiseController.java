package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDto;
import com.olx.service.AdvertiseService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/advertise")
public class AdvertiseController {

	@Autowired
	@Qualifier("MySQl_DB")
	AdvertiseService advertiseService;

	@Operation(description = "Get all Advertisement")
	@GetMapping(value = "/ad",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdvertiseDto>>  getAllAdvertise()
	{
		return new ResponseEntity<>(advertiseService.getAllAdvertise(), HttpStatus.OK);
	}

	@Operation(description = "Get Advertisement by Id")
	@GetMapping(value = "/ad/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDto>  getAdvertiseById(@PathVariable("id") int id )
	{
		return new ResponseEntity<>(advertiseService.getAdvertiseById(id),HttpStatus.OK);
	}

	@Operation(description = "Create a new Advertisement")
	@PostMapping(value = "/ad/create",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDto> createAdvertises(@RequestBody AdvertiseDto advertiseDto)
	{
		AdvertiseDto advertiseDto2=advertiseService.createAdvertise(advertiseDto);
		return new ResponseEntity<>(advertiseDto2,HttpStatus.CREATED);
	}

	@Operation(description = "Update the Advertisement by using advertiseId")
	@PutMapping(value = "/ad/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDto> updateAdvertises(@PathVariable("id") int id,
							@RequestBody AdvertiseDto advertiseDto)
	{
		AdvertiseDto advertiseDto2=advertiseService.updateAdvertise(id,advertiseDto);
		return new ResponseEntity<>(advertiseDto2,HttpStatus.ACCEPTED);
	}


	@Operation(description = "Only the advertisement owner can DELETE the advertise")
	@DeleteMapping(value = "/ad/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteAdvertiseByid(@PathVariable("id") int id )
	{
		if(advertiseService.deleteAdvertiseByid(id)) {
			return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
		}
	}
	
//	Sorting
	@GetMapping(value = "/ad/name/{sortType}") 
	public ResponseEntity<List<AdvertiseDto>> getOrderByName(@PathVariable("sortType") String sortType)
	{
		return new ResponseEntity<>(advertiseService.getOrderByName(sortType),HttpStatus.OK);
	}
	
//	Paging
	
//	In Spring Data JPA last part of the video
	
//	Searching Criteria
	@GetMapping(value = "/ad/search/filtercriteria", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AdvertiseDto> searchStockByFilterCriteria(
			@RequestParam(name="searchText",required = false) String searchText,
			@RequestParam(name="name",required = false)String name,
			@RequestParam(name="market",required = false)String market,
			@RequestParam(name="sortedBy",required = false)String sortedBy,
			@RequestParam(name="startIndex",defaultValue = "0",required = false)int startIndex,
			@RequestParam(name="records",defaultValue = "10",required = false)int records
			)
	{
	return advertiseService.searchAdvertiseByFilterCriteria(searchText, name, market, sortedBy, startIndex, records);
}
}
	
	
	
	
	
//-------------------------------------------------------------------------------------------------------------
//	//	Local Exceptional Handler
//	@ExceptionHandler(value = {InvalidAdvertiseIdException.class})
//	public ResponseEntity<Object> handleInvalidid(Exception ex, WebRequest request ) throws Exception
//	{
//		String clientMessage=ex.toString();
//		return new ResponseEntity<>(clientMessage, HttpStatus.CONFLICT);
//	}