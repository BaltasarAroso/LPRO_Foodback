package com.lpro.fbrest.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
	
	/**
	 * Id of the order 
	 */
	@JsonProperty
	private long id;
	
	/**
	 * List of the meals in a order 
	 */
	@JsonProperty
	private List<Orders_meal> meals;
	
	/**
	 * User Id  
	 */
	@JsonProperty
	private long user_id;
	
	/**
	 * State of the order
	 */
	@JsonProperty
	private String state;
	
	/**
	 * Constructor of Jackson 
	 */
	public Order() {
		super();
	}

	/**
	 * @param id
	 * @param meals
	 * @param user_id
	 * @param state
	 */
	public Order(long id, List<Orders_meal> meals, long user_id, String state) {
		super();
		this.id = id;
		this.meals = meals;
		this.user_id = user_id;
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
	public List<Orders_meal> getMeals() {
		return meals;
	}

	/**
	 * @param meals
	 */
	public void setMeals(List<Orders_meal> meals) {
		this.meals = meals;
	}

	/**
	 * @return
	 */
	public long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
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
		if(!(o instanceof Order)) return false;
		
		Order order = (Order)o;
		if(this.id == order.getId() &&
				this.meals.equals(order.getMeals()) &&
				this.user_id == order.getUser_id() &&
				this.state.equals(order.getState()))
			return true;
		else return false;			
	}

}
