package com.lpro.fbrest.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Meal {
	

	/**
	 * Meal's ID
	 */
	@JsonProperty
	private long id;

	/**
	 * Meal's name
	 */
	@JsonProperty
	private String meal;
	
	/**
	 * Meal's price
	 */
	@JsonProperty
	private BigDecimal price;
	
	/**
	 * ID of establishment
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
	 * @param id Meal's ID
	 * @param meal Meal's name
	 * @param price Meal's price
	 * @param establishment_id ID of establishment
	 */
	public Meal(long id, String meal, double price, long establishment_id) {
		super();
		this.id = id;
		this.meal = meal;
		this.price = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.establishment_id = establishment_id;
	}

	/**
	 * @return Meal's ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id Meal's ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Meal's name
	 */
	public String getMeal() {
		return meal;
	}

	/**
	 * @param meal Meal's name
	 */
	public void setMeal(String meal) {
		this.meal = meal;
	}

	/**
	 * @return Meal's price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price Meal's price
	 */
	public void setPrice(double price) {
		this.price = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @return ID of establishment
	 */
	public long getEstablishment_id() {
		return establishment_id;
	}

	/**
	 * @param establishment_id ID of establishment
	 */
	public void setEstablishment_id(long establishment_id) {
		this.establishment_id = establishment_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Meal)) return false;
		
		Meal meal = (Meal)o;
		if(this.id == meal.getId() &&
				this.meal.equals(meal.getMeal()) &&
				this.price.equals(meal.getPrice()) &&
				this.establishment_id == meal.getEstablishment_id())
			return true;
		else return false;			
	}
}
