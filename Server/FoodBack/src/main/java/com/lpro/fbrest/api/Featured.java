package com.lpro.fbrest.api;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Featured {
	
	@JsonProperty
	private long id;
	
	@JsonProperty
	private long meal_id;
	
	@JsonProperty
	private Timestamp added_date;
	
	@JsonProperty
	private int duration;
	
	public Featured() {
		super();
	}

	public Featured(long id, long meal_id, Timestamp added_date, int duration) {
		super();
		this.id = id;
		this.meal_id = meal_id;
		this.added_date = added_date;
		this.duration = duration;
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

	public Timestamp getAdded_date() {
		return added_date;
	}

	public void setAdded_date(Timestamp added_date) {
		this.added_date = added_date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
