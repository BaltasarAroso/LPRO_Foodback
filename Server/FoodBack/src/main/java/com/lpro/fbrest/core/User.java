package com.lpro.fbrest.core;

import java.security.Principal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	//serialização da data não está a funcionar bem quando a data é 06-03-1996 passa para 05-03-1996
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") 
	private LocalDate birth;
	
	@JsonProperty
	private String premium;

	
	public User() {
		super();
	}
	
	public User(String username, String password, String name, String email, String address, LocalDate birth,
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
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate date) {
		this.birth = date;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof User)) return false;
		
		User user = (User)o;
		if(this.username.equals(user.getUsername()) &&
				this.password.equals(user.getPassword()) &&
				this.name.equals(user.getName()) &&
				this.email.equals(user.getEmail()) &&
				this.address.equals(user.getAddress()) &&
				this.premium.equals(user.getPremium()) &&
				this.birth.equals(user.getBirth()) )
			return true;
		else return false;			
	}
}
