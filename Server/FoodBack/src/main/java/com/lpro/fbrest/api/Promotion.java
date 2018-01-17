package com.lpro.fbrest.api;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Promotion {

	/**
	 * ID of promotion
	 */
	@JsonProperty
	private long id;
	
	/**
	 * Title of promotion
	 */
	@JsonProperty
	private String title;
	
	/**
	 * Description of promotion
	 */
	@JsonProperty
	private String description;
	
	/**
	 * Code of promotion
	 */
	@JsonProperty
	private String code;
	
	/**
	 * Date the promotion ends
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") 
	private LocalDate good_until;
	
	/**
	 * Constructor for Jackson
	 */
	public Promotion() {
		super();
	}

	/**
	 * @param id ID of promotion
	 * @param title Title of promotion
	 * @param description Description of promotion
	 * @param code Code of promotion
	 * @param good_until Date the promotion ends
	 */
	public Promotion(long id, String title, String description, String code, LocalDate good_until) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.code = code;
		this.good_until = good_until;
	}

	/**
	 * @return ID of promotion
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id ID of promotion
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return Title of promotion
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title Title of promotion
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Description of promotion
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description Description of promotion
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Code of promotion
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code Code of promotion
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return Date the promotion ends
	 */
	public LocalDate getGood_until() {
		return good_until;
	}

	/**
	 * @param good_until Date the promotion ends
	 */
	public void setGood_until(LocalDate good_until) {
		this.good_until = good_until;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Promotion)) return false;
		
		Promotion promotion = (Promotion)o;
		if(this.id == promotion.getId() &&
				this.title.equals(promotion.getTitle()) &&
				this.description.equals(promotion.getDescription()) &&
				this.code.equals(promotion.getCode()) &&
				this.good_until.equals(promotion.getGood_until()))
			return true;
		else return false;			
	}
}
