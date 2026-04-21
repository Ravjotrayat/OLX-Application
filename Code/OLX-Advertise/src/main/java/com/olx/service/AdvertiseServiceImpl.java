package com.olx.service;

import java.util.ArrayList;
import java.util.List;

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

    // Done the changes for database 
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

	// Done the changes for database 
	@Override
	public AdvertiseDto createAdvertise(AdvertiseDto advertiseDto) {
		
		AdvertiseEntity advertiseEntity, dd;
	
		dd=advertiseRepository.findByMarket(advertiseDto.getMarket());
		System.out.println(dd);
		if(dd==null) {
			advertiseEntity=new AdvertiseEntity(advertiseDto.getName(),advertiseDto.getMarket(),advertiseDto.getPrice());
			advertiseEntity = advertiseRepository.save(advertiseEntity);
		
			advertiseDto = new AdvertiseDto(advertiseEntity.getId(),advertiseEntity.getName(),
										advertiseEntity.getMarket(),advertiseEntity.getPrice());
		return advertiseDto;
		}
		else 
			 throw new NewMarketNameAlreadyPresentException(" "+advertiseDto.getMarket());
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
			throw new IdNotFoundException(" "+id);
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
