package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer> {

	public AdvertiseEntity findByMarket(String market);

}
