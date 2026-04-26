package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer> {

	public AdvertiseEntity findByMarket(String market);
	public List<AdvertiseEntity> findByOrderByName(); //By default in Ascending Order
	public List<AdvertiseEntity> findByOrderByNameDesc();
}
