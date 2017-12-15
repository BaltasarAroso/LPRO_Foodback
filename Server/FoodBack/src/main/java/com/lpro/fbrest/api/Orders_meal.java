package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders_meal {
	
	@JsonProperty
	private long meal_id;
	
	@JsonProperty
	private int quantity;
	
	public Orders_meal() {
		super();
	}

	public Orders_meal(long meal_id, int quantity) {
		super();
		this.meal_id = meal_id;
		this.quantity = quantity;
	}

	public long getMeal_id() {
		return meal_id;
	}

	public void setMeal_id(long meal_id) {
		this.meal_id = meal_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	

}
