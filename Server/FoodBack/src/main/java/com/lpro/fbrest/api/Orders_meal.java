package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders_meal {
	
	/**
	 * ID of order_meal
	 */
	@JsonProperty
	private long id;
	
	/**
	 * Meal name
	 */
	@JsonProperty
	private String meal;
	
	/**
	 * ID of order
	 */
	@JsonProperty
	private long orders_id;
	
	/**
	 * Quantity of meal's
	 */
	@JsonProperty
	private int quantity;
	
	/**
	 * State of this part of the order
	 */
	@JsonProperty
	private String state;
	
	/**
	 * Contructor for Jackson
	 */
	public Orders_meal() {
		super();
	}

	/**
	 * @param id ID of order_meal
	 * @param meal Meal name
	 * @param orders_id ID of order
	 * @param quantity Quantity of meals
	 * @param state State of this part of the order
	 */
	public Orders_meal(long id, String meal, long orders_id, int quantity, String state) {
		super();
		this.id = id;
		this.meal = meal;
		this.orders_id = orders_id;
		this.quantity = quantity;
		this.state = state;
	}

	/**
	 * @return ID of order_meal
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id ID of order_meal
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Meal name
	 */
	public String getMeal() {
		return meal;
	}

	/**
	 * @param meal Meal name
	 */
	public void setMeal(String meal) {
		this.meal = meal;
	}

	/**
	 * @return ID of order
	 */
	public long getOrders_id() {
		return orders_id;
	}

	/**
	 * @param orders_id ID of order
	 */
	public void setOrders_id(long orders_id) {
		this.orders_id = orders_id;
	}

	/**
	 * @return Quantity of meals
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity Quantity of meals
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return State of this part of the order
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state State of this part of the order
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
		if(this.meal.equals(orders_meal.getMeal()) &&
			this.quantity == orders_meal.getQuantity())
			return true;
		else return false;			
	}
	
}
