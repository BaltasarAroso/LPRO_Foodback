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
	private long establishment_id;
	
	public Meal() {
		super();
	}

	public Meal(long id, String meal, int price, long establishment_id) {
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

	public long getEstablishment_id() {
		return establishment_id;
	}

	public void setEstablishment_id(long establishment_id) {
		this.establishment_id = establishment_id;
	}

	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Meal)) return false;
		
		Meal meal = (Meal)o;
		if(this.id == meal.getId() &&
				this.meal.equals(meal.getMeal()) &&
				this.price == meal.getPrice() &&
				this.establishment_id == meal.getEstablishment_id())
			return true;
		else return false;			
	}
}
