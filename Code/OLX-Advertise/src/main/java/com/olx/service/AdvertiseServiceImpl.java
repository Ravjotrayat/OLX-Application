package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.olx.dto.AdvertiseDto;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.GlobalExceptionHandler;
import com.olx.exception.IdNotFoundException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidInputException;
import com.olx.exception.NewMarketNameAlreadyPresentException;
import com.olx.repository.AdvertiseRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

//Its optional to provide name in Service if connected with single DB, For >2 then naming is required
@Service("MySQl_DB")
public class AdvertiseServiceImpl implements AdvertiseService {

    private final GlobalExceptionHandler globalExceptionHandler;

    @Autowired
//    @Qualifier(value = "MySQl_DB")
    AdvertiseRepository advertiseRepository;
    
    @Autowired
    EntityManager entityManager; // heart of JPA

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

	@Override
	public List<AdvertiseDto> getOrderByName(String sortType) {
		List<AdvertiseEntity> advertiseEntity;
		if(sortType.equalsIgnoreCase("desc")) {
			advertiseEntity=advertiseRepository.findByOrderByNameDesc();
		}
		else if(sortType.equalsIgnoreCase("asc")){ 
			advertiseEntity= advertiseRepository.findByOrderByName();
		}
		else {
			throw new InvalidInputException(" "+sortType);
		}
		
		 List<AdvertiseDto> dto = new ArrayList<>();
		 for(AdvertiseEntity entity:advertiseEntity) 
		 {
			 AdvertiseDto obj = new AdvertiseDto();
		    obj.setId(entity.getId());
		    obj.setName(entity.getName());
		    obj.setPrice(entity.getPrice());
		    obj.setMarket(entity.getMarket());
		    
		    dto.add(obj);
		 }
		return dto;
	}

	@Override
	public List<AdvertiseDto> searchAdvertiseByFilterCriteria(String searchText, String name, String market,String sortedBy, int startIndex, 
																int records) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = builder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> root= criteriaQuery.from(AdvertiseEntity.class);
		
		Predicate searchTextPredicate = builder.and();
		Predicate namePredicate =  builder.and();
		Predicate marketPredicate = builder.and(); 
		
		if(searchText!=null && !"".equals(searchText)) // User has sent seachText value.
		{	
								//name LIKE "%searchText"
			Predicate nameSearchPredicate= builder.like(root.get("name"), "%" + searchText+ "%");
								// market
			Predicate marketSearchPredicate= builder.like(root.get("market"), "%" + searchText+ "%" );
		
			searchTextPredicate =builder.or(nameSearchPredicate,marketSearchPredicate);
		}
		
		if(name!=null && !"".equals(name)) {
			 namePredicate = builder.equal(root.get("name"),name);
			criteriaQuery=criteriaQuery.where(namePredicate);
		}
		
		if(market!=null && !"".equals(market)) {
			marketPredicate = builder.equal(root.get("market"),market);
			criteriaQuery=criteriaQuery.where(marketPredicate);
		}
		
		if (sortedBy!=null && !"".equals(sortedBy)){
			if("asc".equalsIgnoreCase(sortedBy)) {
				criteriaQuery.orderBy(builder.asc(root.get("name")));
			}
			
			else if("desc".equals(sortedBy)) {
				criteriaQuery.orderBy(builder.asc(root.get("name")));
			}
		}
		
		Predicate finalPredicate = builder.and(searchTextPredicate,namePredicate,marketPredicate);
		criteriaQuery.where(finalPredicate);
		
		TypedQuery<AdvertiseEntity> query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(startIndex);
		query.setMaxResults(records);
		List<AdvertiseEntity> entities = query.getResultList(); // Query is executed.
		
		 List<AdvertiseDto> dto = new ArrayList<>();
		 for(AdvertiseEntity entity:entities) 
		 {
			 AdvertiseDto obj = new AdvertiseDto();
		    obj.setId(entity.getId());
		    obj.setName(entity.getName());
		    obj.setPrice(entity.getPrice());
		    obj.setMarket(entity.getMarket());
		    
		    dto.add(obj);
		 }
		return dto;

	}



}







