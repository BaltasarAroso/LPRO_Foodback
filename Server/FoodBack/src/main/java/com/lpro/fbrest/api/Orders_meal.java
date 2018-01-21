package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders_meal {
	
	/**
	 * Id 
	 */
	@JsonProperty
	private long id;
	
	/**
	 * ID of a meal 
	 */
	@JsonProperty
	private long meal_id;
	
	/**
	 * OD of the order 
	 */
	@JsonProperty
	private long orders_id;
	
	/**
	 * Quantity of the meal in the order 
	 */
	@JsonProperty
	private int quantity;
	
	/**
	 * State of the meal  
	 */
	@JsonProperty
	private String state;
	
	/**
	 *  Constructor of Jackson
	 */
	public Orders_meal() {
		super();
	}

	/**
	 * @param id
	 * @param meal_id
	 * @param orders_id
	 * @param quantity
	 * @param state
	 */
	public Orders_meal(long id, long meal_id, long orders_id, int quantity, String state) {
		super();
		this.id = id;
		this.meal_id = meal_id;
		this.orders_id = orders_id;
		this.quantity = quantity;
		this.state = state;
	}

	/**
	 * @return
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
	 * @return
	 */
	public long getMeal_id() {
		return meal_id;
	}

	/**
	 * @param meal_id
	 */
	public void setMeal_id(long meal_id) {
		this.meal_id = meal_id;
	}

	/**
	 * @return
	 */
	public long getOrders_id() {
		return orders_id;
	}

	/**
	 * @param orders_id
	 */
	public void setOrders_id(long orders_id) {
		this.orders_id = orders_id;
	}

	/**
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
