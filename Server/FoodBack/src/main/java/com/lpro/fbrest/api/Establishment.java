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
	 * @param id
	 * @param name
	 * @param category
	 * @param address
	 * @param zone
	 * @param city
	 * @param email
	 * @param contact
	 * @param username
	 * @param password
	 * @param delivery
	 * @param avg_price
	 * @param schedule1
	 * @param schedule2
	 * @param rating
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
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public Boolean getDelivery() {
		return delivery;
	}

	/**
	 * @param delivery
	 */
	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	/**
	 * @return
	 */
	public int getAvg_price() {
		return avg_price;
	}

	/**
	 * @param avg_price
	 */
	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}
	
	/**
	 * @return
	 */
	public String getSchedule1() {
		return schedule1;
	}

	/**
	 * @param schedule1
	 */
	public void setSchedule1(String schedule1) {
		this.schedule1 = schedule1;
	}

	/**
	 * @return
	 */
	public String getSchedule2() {
		return schedule2;
	}

	/**
	 * @param schedule2
	 */
	public void setSchedule2(String schedule2) {
		this.schedule2 = schedule2;
	}
	
	/**
	 * @return
	 */
	public BigDecimal getRating() {
		return rating;
	}
	
	/**
	 * @param rating
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
