package com.lpro.fbrest.auth;

import java.security.Principal;

public class Client implements Principal {
	
	private String username;
	private String role;
	private int users_id;
	private int establishment_id;
	
	public Client(String username, String role, int users_id, int establishment_id) {
		super();
		this.username = username;
		this.role = role;
		this.users_id = users_id;
		this.establishment_id = establishment_id;
	}
	
	@Override
	public String getName() {
		return username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public int getEstablishment_id() {
		return establishment_id;
	}

	public void setEstablishment_id(int establishment_id) {
		this.establishment_id = establishment_id;
	}
	
}
