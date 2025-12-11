package com.olx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AdvertiseDto {

//	private int id;
//	private String title;
//	private String category;
//	private String status;
//	private double price;
//	private String description;
//	private Blob photo;
//	private Date created_date;
//	private Date modified_date;
//	private Enum<Enum<E>> active;
//	private String posted_by;
//	private String username;

	private int id;

	@NotEmpty
	@NotNull
	@Schema(name = "name", description = "Name of the advertisement")
	private String name;

	@NotEmpty
	@NotNull
	@Schema(name = "", description = "")
	private String market;

	@NotEmpty
	@NotNull
	@Schema(name = "", description = "")
	private int price;


	public AdvertiseDto(int id, String name, String market, int price) {
		super();
		this.id = id;
		this.name = name;
		this.market = market;
		this.price=price;
	}
	public AdvertiseDto() {
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
	@Override
	public String toString() {
		return "AdvertiseDto [id=" + id + ", name=" + name + ", market=" + market + ", price=" + price + "]";
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


}
