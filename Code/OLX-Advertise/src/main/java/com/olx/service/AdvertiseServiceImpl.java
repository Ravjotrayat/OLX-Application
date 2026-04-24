package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.AdvertiseDto;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.GlobalExceptionHandler;
import com.olx.exception.IdNotFoundException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.NewMarketNameAlreadyPresentException;
import com.olx.repository.AdvertiseRepository;

//Its optional to provide name in Service if connected with single DB, For >2 then naming is required
@Service("MySQl_DB")
public class AdvertiseServiceImpl implements AdvertiseService {

    private final GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    AdvertiseRepository advertiseRepository;

    AdvertiseServiceImpl(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    // Done the changes for Database
	@Override
	public List<AdvertiseDto> getAllAdvertise() {
		List<AdvertiseEntity> advertiseEntity = advertiseRepository.findAll();
		List<AdvertiseDto> advertiseDtos=new ArrayList<>();
		for (AdvertiseEntity advertiseEntity2 : advertiseEntity) {
			AdvertiseDto advertiseDto=new AdvertiseDto(advertiseEntity2.getId(),advertiseEntity2.getName(), advertiseEntity2.getMarket()
					,advertiseEntity2.getPrice());
			advertiseDtos.add(advertiseDto);
		}
		return advertiseDtos;
	}

	// Done the changes for Database
	@Override
	public AdvertiseDto getAdvertiseById(int id) {
		Optional<AdvertiseEntity> advertiseEntity=advertiseRepository.findById(id);
		if(advertiseEntity.isPresent())
		{
			AdvertiseEntity entity = advertiseEntity.get();
			return new AdvertiseDto(entity.getId(),entity.getName(),
					entity.getMarket(),entity.getPrice());
		}
	    throw new InvalidAdvertiseIdException(" "+id);
	}

	// Done the changes for Database
	@Override
	public AdvertiseDto createAdvertise(AdvertiseDto advertiseDto) {

		AdvertiseEntity advertiseEntity, dd;
		dd=advertiseRepository.findByMarket(advertiseDto.getMarket());
		if(dd==null) {
			advertiseEntity=new AdvertiseEntity(advertiseDto.getName(),advertiseDto.getMarket(),advertiseDto.getPrice());
			advertiseEntity = advertiseRepository.save(advertiseEntity);

			advertiseDto = new AdvertiseDto(advertiseEntity.getId(),advertiseEntity.getName(),
										advertiseEntity.getMarket(),advertiseEntity.getPrice());
		return advertiseDto;
		} else {
			throw new NewMarketNameAlreadyPresentException(" "+advertiseDto.getMarket());
		}
	}

	// Done the changes for Database
	@Override
	public AdvertiseDto updateAdvertise(int id, AdvertiseDto advertiseDto) {

		AdvertiseEntity existingAdvertise = advertiseRepository.findById(id).orElseThrow(() ->
									new IdNotFoundException("Advertise not found with id: " + id));

		existingAdvertise.setName(advertiseDto.getName());
		existingAdvertise.setMarket(advertiseDto.getMarket());
	    existingAdvertise.setPrice(advertiseDto.getPrice());

	    AdvertiseEntity updatedAdvertise = advertiseRepository.save(existingAdvertise);

	    AdvertiseDto dto = new AdvertiseDto(existingAdvertise.getId(),existingAdvertise.getName(),
	    		existingAdvertise.getMarket(),existingAdvertise.getPrice());

	    return dto;
	}

	// Done the changes for Database
	@Override
	public boolean deleteAdvertiseByid(int id) {

		Optional<AdvertiseEntity> advertiseEntity =advertiseRepository.findById(id);
		if(advertiseEntity.isPresent()) {
		advertiseRepository.deleteById(id);

		return true;
		} else {
			return false;
		}
	}



}
