package com.lpro.fbrest.api;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	/**
	 * id of the user
	 */
	@JsonProperty
	private long id;
	
	/**
	 *  username of the user
	 */
	@JsonProperty
	private String username;
	
	/**
	 * password of the user
	 */
	@JsonProperty
	private String password;
	
	/**
	 *  name of the user
	 */
	@JsonProperty
	private String name;
	
	/**
	 * email of the user
	 */
	@JsonProperty
	private String email;
	
	/**
	 * address of the user
	 */
	@JsonProperty
	private String address;
	
	/**
	 * birthdate of the user
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") 
	private LocalDate birth;
	
	/**
	 * if user is premium or not
	 */
	@JsonProperty
	private boolean premium;
	
	/**
	 * Zone of the user
	 */
	@JsonProperty
	private String zone;
	
	/**
	 * City of the user
	 */
	@JsonProperty
	private String city;

	
	/**
	 * Constructor for Jackson
	 */
	public User() {
		super();
	}
	
	/**
	 * @param id Id of user
	 * @param username Username of user
	 * @param password Password of user
	 * @param name Name of user
	 * @param email Email of user
	 * @param address Address of user
	 * @param birth Birth date of user
	 * @param premium If user is premium or not
	 * @param zone Zone of the user
	 * @param city City of the user
	 * 
	 * Constructor
	 */
	public User(long id, String username, String password, String name, String email, String address, LocalDate birth,
			boolean premium, String zone, String city) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.birth = birth;
		this.premium = premium;
		this.zone = zone;
		this.city = city;
	}

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id of user
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username username for user
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password Password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return name 
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name Name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email Email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address Address of the user
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return birth
	 */
	public LocalDate getBirth() {
		return birth;
	}
	/**
	 * @param birth Birth date of the user
	 */
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	/**
	 * @return premium
	 */
	public boolean getPremium() {
		return premium;
	}
	/**
	 * @param premium If user is premium or not
	 */
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	/**
	 * @return Zone of the user
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone Zone of the user
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return City of the user
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city City of the user
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof User)) return false;
		
		User user = (User)o;
		if(this.id == user.getId() &&
				this.username.equals(user.getUsername()) &&
				this.password.equals(user.getPassword()) &&
				this.name.equals(user.getName()) &&
				this.email.equals(user.getEmail()) &&
				this.address.equals(user.getAddress()) &&
				(this.premium == user.getPremium()) &&
				this.birth.equals(user.getBirth()) &&
				this.zone.equals(user.getZone()) &&
				this.city.equals(user.getCity()))
			return true;
		else return false;			
	}
}
