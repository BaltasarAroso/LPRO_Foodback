package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meal {
	
	@JsonProperty
	private long id;

	@JsonProperty
	private String meal;
	
	@JsonProperty
	private int price;
	
	@JsonProperty
	private int establishment_id;
	
	public Meal() {
		super();
	}

	public Meal(long id, String meal, int price, int establishment_id) {
		super();
		this.id = id;
		this.meal = meal;
		this.price = price;
		this.establishment_id = establishment_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getEstablishment_id() {
		return establishment_id;
	}

	public void setEstablishment_id(int establishment_id) {
		this.establishment_id = establishment_id;
	}


}
