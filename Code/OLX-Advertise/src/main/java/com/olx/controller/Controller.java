package com.olx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDto;

import java.util.*;
@RestController
@RequestMapping("/advertise")
public class Controller {

//	@PostMapping
//	private String addAdvertise()
//	{
//		return null;
//	}
	
	@GetMapping("/ad")
	public List<AdvertiseDto> getAllAdvertise()
	{
		return obj;
	}
	
	private static List<AdvertiseDto> obj=new ArrayList<AdvertiseDto>();
	static {
	obj.add(new AdvertiseDto(1,"IBM","BSE",2300));
	obj.add(new AdvertiseDto(2,"Zensar","NSE",3200));
	obj.add(new AdvertiseDto(3,"Infosys","BSE",4500));
	
	}
}
