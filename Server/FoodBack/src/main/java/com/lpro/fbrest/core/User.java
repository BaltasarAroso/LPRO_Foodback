package com.lpro.fbrest.core;

import java.security.Principal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Principal{
	
	@JsonProperty
	private String username;
	@JsonProperty
	private String password;
	@JsonProperty
	private String name;
	@JsonProperty
	private String email;
	@JsonProperty
	private String address;
	@JsonProperty
	private Date birth;
	@JsonProperty
	private String premium;
	
	public User() {
		
	}
	
	public User(String username, String password, String name, String email, String address, Date birth,
			String premium) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.birth = birth;
		this.premium = premium;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date date) {
		this.birth = date;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
}
