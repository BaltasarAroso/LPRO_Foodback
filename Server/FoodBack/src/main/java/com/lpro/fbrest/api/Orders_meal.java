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
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Orders_meal)) return false;
		
		Orders_meal orders_meal = (Orders_meal)o;
		if(this.meal_id == orders_meal.getMeal_id() &&
				this.quantity == orders_meal.getQuantity())
			return true;
		else return false;			
	}
	
}
