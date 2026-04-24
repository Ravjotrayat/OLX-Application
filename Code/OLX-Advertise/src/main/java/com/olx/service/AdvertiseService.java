package com.olx.service;

import java.util.List;

import com.olx.dto.AdvertiseDto;

public interface AdvertiseService {

	public List<AdvertiseDto> getAllAdvertise();
	public AdvertiseDto getAdvertiseById(int id);
	public AdvertiseDto createAdvertise(AdvertiseDto advertiseDto);
	public AdvertiseDto updateAdvertise(int id, AdvertiseDto advertiseDto);
	public boolean deleteAdvertiseByid(int id);

}
