package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Meal {
	
	
	/**
	 * id of meal
	 */
	@JsonProperty
	private long id;

	/**
	 * Name of the meal
	 */
	@JsonProperty
	private String meal;
	
	/**
	 * Price of meal
	 */
	@JsonProperty
	private int price;
	
	/**
	 * Id of establishment
	 */
	@JsonProperty
	private long establishment_id;
	
	/**
	 * Constructor for Jackson
	 */
	public Meal() {
		super();
	}

	
	/**
	 * @param id
	 * @param meal
	 * @param price
	 * @param establishment_id
	 * 
	 * Constructor
	 */
	public Meal(long id, String meal, int price, long establishment_id) {
		super();
		this.id = id;
		this.meal = meal;
		this.price = price;
		this.establishment_id = establishment_id;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return meal
	 */
	public String getMeal() {
		return meal;
	}

	/**
	 * @param meal
	 */
	public void setMeal(String meal) {
		this.meal = meal;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return establishment_id
	 */
	public long getEstablishment_id() {
		return establishment_id;
	}

	/**
	 * @param establishment_id
	 */
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
