package com.lpro.fbrest.api;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Featured {
	
	/**
	 * ID of the feature
	 */
	@JsonProperty
	private long id;
	
	/**
	 * id of meal 
	 */
	@JsonProperty
	private long meal_id;
	
	/**
	 * Date  
	 */
	@JsonProperty
	private Timestamp added_date;
	
	/**
	 *  Duration 
	 */
	@JsonProperty
	private int duration;
	
	/**
	 * Constructor of Jackson
	 */
	public Featured() {
		super();
	}

	/**
	 * @param id
	 * @param meal_id
	 * @param added_date
	 * @param duration
	 */
	public Featured(long id, long meal_id, Timestamp added_date, int duration) {
		super();
		this.id = id;
		this.meal_id = meal_id;
		this.added_date = added_date;
		this.duration = duration;
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
	public Timestamp getAdded_date() {
		return added_date;
	}

	/**
	 * @param added_date
	 */
	public void setAdded_date(Timestamp added_date) {
		this.added_date = added_date;
	}

	/**
	 * @return
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Featured)) return false;
		
		Featured featured = (Featured)o;
		if(this.id == featured.getId() &&
				this.meal_id == featured.getMeal_id() &&
				this.added_date.equals(featured.getAdded_date()) &&
				this.duration == featured.getDuration())
			return true;
		else return false;			
	}
}
