package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meal {
	
	@JsonProperty
	private int id;

	@JsonProperty
	private String meal;
	
	@JsonProperty
	private int price;
	
	@JsonProperty
	private int establishemnt_id;
	
	public Meal() {
		super();
	}

	public Meal(String meal, int price, int establishemnt_id) {
		super();
		this.id = id;
		this.meal = meal;
		this.price = price;
		this.establishemnt_id = establishemnt_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getEstablishemnt_id() {
		return establishemnt_id;
	}

	public void setEstablishemnt_id(int establishemnt_id) {
		this.establishemnt_id = establishemnt_id;
	}

}
