package com.olx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "ADVERTISE")
public class AdvertiseEntity {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")  // by default name, if wanted to provided different column name in database, the use this
	private String name;

	private String market;

	private int price;

	public AdvertiseEntity( String name, String market, int price) {
		super();
		this.name = name;
		this.market = market;
		this.price = price;
	}

	public AdvertiseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", name=" + name + ", market=" + market + ", price=" + price + "]";
	}



}
