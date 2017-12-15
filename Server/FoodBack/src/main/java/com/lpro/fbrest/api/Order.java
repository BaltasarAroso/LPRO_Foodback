package com.lpro.fbrest.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
	
	@JsonProperty
	private long id;
	
	@JsonProperty
	private List<Orders_meal> meals;
	
	@JsonProperty
	private long user_id;
	
	@JsonProperty
	private String state;
	
	public Order() {
		super();
	}

	public Order(long id, List<Orders_meal> meals, long user_id, String state) {
		super();
		this.id = id;
		this.meals = meals;
		this.user_id = user_id;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Orders_meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Orders_meal> meals) {
		this.meals = meals;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

}
