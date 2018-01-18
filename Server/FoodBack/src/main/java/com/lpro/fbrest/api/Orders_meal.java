package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders_meal {
	
	@JsonProperty
	private long id;
	
	@JsonProperty
	private long meal_id;
	
	@JsonProperty
	private long orders_id;
	
	@JsonProperty
	private int quantity;
	
	@JsonProperty
	private String state;
	
	public Orders_meal() {
		super();
	}

	public Orders_meal(long id, long meal_id, long orders_id, int quantity, String state) {
		super();
		this.id = id;
		this.meal_id = meal_id;
		this.orders_id = orders_id;
		this.quantity = quantity;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMeal_id() {
		return meal_id;
	}

	public void setMeal_id(long meal_id) {
		this.meal_id = meal_id;
	}

	public long getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(long orders_id) {
		this.orders_id = orders_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
