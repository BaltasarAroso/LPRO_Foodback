package com.lpro.fbrest.auth;

import java.security.Principal;

/**
 * @author Daniel
 *
 * Principal for the Authentication process
 */
public class Client implements Principal {
	
	/**
	 * user name of the client
	 */
	private String username;
	/**
	 * role of the client (USER or ESTABLISHMENT or ADMIN)
	 */
	private String role;
	/**
	 * id of user if it is a user
	 */
	private long users_id;
	/**
	 * id of establishment if it is a establishment
	 */
	private long establishment_id;
	
	/**
	 * @param username Username of the client
	 * @param role Role of the client
	 * @param users_id Id of the user
	 * @param establishment_id Id of the establishment
	 * 
	 * Constructor
	 */
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

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username Username of the Client
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role Role of the client
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return user id
	 */
	public long getUsers_id() {
		return users_id;
	}

	/**
	 * @param users_id Id of the user
 	 */
	public void setUsers_id(long users_id) {
		this.users_id = users_id;
	}

	/**
	 * @return establishment_id
	 */
	public long getEstablishment_id() {
		return establishment_id;
	}

	/**
	 * @param establishment_id Id of the establishment
	 */
	public void setEstablishment_id(long establishment_id) {
		this.establishment_id = establishment_id;
	}
	
}
