package com.olx.service;

import java.util.List;

import com.olx.dto.AdvertiseDto;

public interface AdvertiseService {

	public List<AdvertiseDto> getAllAdvertise();
	public AdvertiseDto getAdvertiseById(int id);
	public AdvertiseDto createAdvertise(AdvertiseDto advertiseDto);
	public AdvertiseDto updateAdvertise(int id, AdvertiseDto advertiseDto);
	public boolean deleteAdvertiseByid(int id);
	public List<AdvertiseDto> getOrderByName(String sortType);
	public List<AdvertiseDto> searchAdvertiseByFilterCriteria(String searchText,String name,String market,String sortedBy,int startIndex,int records);
}
