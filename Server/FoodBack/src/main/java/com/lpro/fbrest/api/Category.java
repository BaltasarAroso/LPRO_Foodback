package com.lpro.fbrest.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
	
	/**
	 * ID of category
	 */
	@JsonProperty
	private long id;
	
	/**
	 * Name of category
	 */
	@JsonProperty
	private String category;
	
	/**
	 * Constructor for Jackson
	 */
	public Category() {
		super();
	}

	/**
	 * @param id ID of category
	 * @param category Name of category
	 */
	public Category(long id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	/**
	 * @return ID of category
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id ID of category
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Name of category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category Name of category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}
