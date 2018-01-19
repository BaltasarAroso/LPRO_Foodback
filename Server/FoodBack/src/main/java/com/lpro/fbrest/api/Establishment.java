package com.lpro.fbrest.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Establishment {
	
	@JsonProperty
	private long id;
	
	@JsonProperty
	private String name;

	@JsonProperty
	private long category_id;
	
	@JsonProperty
	private String address;
	
	@JsonProperty
	private String zone;
	
	@JsonProperty
	private String city;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String contact;
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private Boolean delivery;
	
	@JsonProperty
	private int avg_price;
	
	@JsonProperty
	private String schedule1;
	
	@JsonProperty
	private String schedule2;
	
	@JsonProperty
	private BigDecimal rating;
	
	public Establishment() {
		super();
	}
	
	public Establishment(long id, String name, long category_id, String address, String zone, String city, String email,
			String contact, String username, String password, Boolean delivery, int avg_price, String schedule1,
			String schedule2, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.category_id = category_id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public int getAvg_price() {
		return avg_price;
	}

	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}
	
	public String getSchedule1() {
		return schedule1;
	}

	public void setSchedule1(String schedule1) {
		this.schedule1 = schedule1;
	}

	public String getSchedule2() {
		return schedule2;
	}

	public void setSchedule2(String schedule2) {
		this.schedule2 = schedule2;
	}
	
	public BigDecimal getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = new BigDecimal(rating).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Establishment)) return false;
		
		Establishment restaurante = (Establishment)o;
		if(this.id == restaurante.getId() &&
				this.name.equals(restaurante.getName()) &&
				this.category_id == restaurante.getCategory_id() &&
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
