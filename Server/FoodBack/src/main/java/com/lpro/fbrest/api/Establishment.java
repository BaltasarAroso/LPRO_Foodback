package com.lpro.fbrest.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Establishment {
	
	/**
	 *  id of establishment
	 */
	@JsonProperty
	private long id;
	
	/**
	 *  name of the establishment
	 */
	@JsonProperty
	private String name;

	/**
	 *  category of establishment
	 */
	@JsonProperty
	private String category;
	
	/**
	 * address of establishment
	 */
	@JsonProperty
	private String address;
	
	/**
	 * zone of the establishment
	 */
	@JsonProperty
	private String zone;
	
	/**
	 * city of the establishemnt
	 */
	@JsonProperty
	private String city;
	
	/**
	 * email of the establishment
	 */
	@JsonProperty
	private String email;
	
	/**
	 * contact of the establishemnt
	 */
	@JsonProperty
	private String contact;
	
	/**
	 * username of the establishemnt
	 */
	@JsonProperty
	private String username;
	
	/**
	 *  pass of the establishemnt
	 */
	@JsonProperty
	private String password;
	
	/**
	 * if establishment allows deliverys
	 */
	@JsonProperty
	private Boolean delivery;
	
	/**
	 * the average of the price
	 */
	@JsonProperty
	private int avg_price;
	
	/**
	 * Schedule of the establishment
	 */
	@JsonProperty
	private String schedule1;
	
	/**
	 * Schedule of the establishment
	 */
	@JsonProperty
	private String schedule2;
	
	/**
	 * Classification of the establishment
	 */
	@JsonProperty
	private BigDecimal rating;
	
	/**
	 * Constructor of Jackson
	 */
	public Establishment() {
		super();
	}
	
	/**
	 * @param id of the establishment
	 * @param name of the establishment
	 * @param category of the establishment
	 * @param address of the establishment
	 * @param zone of the establishment
	 * @param city of the establishment
	 * @param email of the establishment
	 * @param contact of the establishment
	 * @param username of the establishment
	 * @param password of the establishment
	 * @param delivery of the establishment
	 * @param avg_price of the establishment
	 * @param schedule1 of the establishment
	 * @param schedule2 of the establishment
	 * @param rating of the establishment

	 */
	public Establishment(long id, String name, String category, String address, String zone, String city, String email,
			String contact, String username, String password, Boolean delivery, int avg_price, String schedule1,
			String schedule2, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.address = address;
		this.zone = zone;
		this.city = city;
		this.email = email;
		this.contact = contact;
		this.username = username;
		this.password = password;
		this.delivery = delivery;
		this.avg_price = avg_price;
		this.schedule1 = schedule1;
		this.schedule2 = schedule2;
		this.rating = new BigDecimal(rating).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @return id of the establishment
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id of the establishment
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return name of the establishment
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name of the establishment
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return category of the establishment
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category of the establishment
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return address of the establishment
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address of the establishment
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return zone of the establishment
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone of the establishment
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return city of the establishment
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city of the establishment
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return email of the establishment
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email of the establishment
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return contact of the establishment
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact of the establishment
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return username of the establishment
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username of the establishment
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return pass of the establishment
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password of the establishment
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return delivery boolean of the establishment
	 */
	public Boolean getDelivery() {
		return delivery;
	}

	/**
	 * @param delivery boolean of the establishment
	 */
	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	/**
	 * @return average price of the establishment
	 */
	public int getAvg_price() {
		return avg_price;
	}

	/**
	 * @param avg_price average price of the establishment
	 */
	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}
	
	/**
	 * @return schedule1 of the establishment
	 */
	public String getSchedule1() {
		return schedule1;
	}

	/**
	 * @param schedule1 of the establishment
	 */
	public void setSchedule1(String schedule1) {
		this.schedule1 = schedule1;
	}

	/**
	 * @return schedule 2 of the establishment
	 */
	public String getSchedule2() {
		return schedule2;
	}

	/**
	 * @param schedule2 of the establishment
	 */
	public void setSchedule2(String schedule2) {
		this.schedule2 = schedule2;
	}
	
	/**
	 * @return rating of the establishment
	 */
	public BigDecimal getRating() {
		return rating;
	}
	
	/**
	 * @param rating of the establishment
	 */
	public void setRating(double rating) {
		this.rating = new BigDecimal(rating).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Establishment)) return false;
		
		Establishment restaurante = (Establishment)o;
		if(this.id == restaurante.getId() &&
				this.name.equals(restaurante.getName()) &&
				this.category.equals(restaurante.getCategory()) &&
				this.address.equals(restaurante.getAddress()) &&
				this.zone.equals(restaurante.getZone()) &&
				this.city.equals(restaurante.getCity()) &&
				this.email.equals(restaurante.getEmail()) &&
				this.contact.equals(restaurante.getContact()) &&
				this.username.equals(restaurante.getUsername()) &&
				this.password.equals(restaurante.getPassword()) &&
				this.delivery.equals(restaurante.getDelivery()) &&
				this.avg_price == restaurante.getAvg_price() &&
				this.schedule1.equals(restaurante.getSchedule1()) &&
				this.schedule2.equals(restaurante.getSchedule2()) &&
				this.rating.equals(restaurante.getRating())) 
			return true;
		else return false;			
	}
	

}
