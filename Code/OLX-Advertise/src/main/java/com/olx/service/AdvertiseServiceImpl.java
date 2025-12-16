package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.olx.dto.AdvertiseDto;
import com.olx.exception.InvalidAdvertiseIdException;

//Its optional to provide name in Service if connected with single DB, For >2 then naming is required
@Service("MySQl_DB")
public class AdvertiseServiceImpl implements AdvertiseService {

	@Override
	public List<AdvertiseDto> getAllAdvertise() {
		return obj;
	}

	@Override
	public AdvertiseDto getAdvertiseById(int id) {
		for(AdvertiseDto advertiseDto:obj)
		{
			if(advertiseDto.getId()==id) {
				return advertiseDto;
			}
		}
	    throw new InvalidAdvertiseIdException(" "+id);
	}

	@Override
	public AdvertiseDto createAdvertise(AdvertiseDto advertiseDto) {
		advertiseDto.setId(obj.size()+1);
		obj.add(advertiseDto);
		return advertiseDto;
	}

	@Override
	public AdvertiseDto updateAdvertise(int id, AdvertiseDto advertiseDto) {
		AdvertiseDto dto=getAdvertiseById(id);
		dto.setMarket(advertiseDto.getMarket());
		dto.setName(advertiseDto.getName());
		dto.setPrice(advertiseDto.getPrice());

		return dto;
	}

	@Override
	public boolean deleteAdvertiseByid(int id) {
		AdvertiseDto dto=getAdvertiseById(id);
		if(dto==null) {
			throw new InvalidAdvertiseIdException(" "+id);
		} else {
		obj.remove(dto);
		return true;}
	}

	private static List<AdvertiseDto> obj=new ArrayList<>();
	static {
	obj.add(new AdvertiseDto(1,"IBM","BSE",2300));
	obj.add(new AdvertiseDto(2,"Zensar","NSE",3200));
	obj.add(new AdvertiseDto(3,"Infosys","BSE",4500));

	}

}
