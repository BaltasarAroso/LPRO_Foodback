package com.lpro.fbrest.auth;

import java.security.Principal;

/**
 * Principal for the Authentication process
 */
public class Client implements Principal {
	
	/**
	 * Id of Client
	 */
	private long id;
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
	 * id of tmp establishment 
	 */
	private long tmp_establishment_id;
	
	/**
	 * @param id ID of client
	 * @param username Username of the client
	 * @param role Role of the client
	 * @param users_id Id of the user
	 * @param establishment_id Id of the establishment
	 * @param tmp_establishment_id ID of the tmp establishment
	 * 
	 * Constructor
	 */
	public Client(long id, String username, String role, long users_id, long establishment_id, long tmp_establishment_id) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.users_id = users_id;
		this.establishment_id = establishment_id;
		this.tmp_establishment_id = tmp_establishment_id;
	}

	/* (non-Javadoc)
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		return username;
	}

	
	/**
	 * @return ID of client
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id ID of client
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
	
	/**
	 * @return ID of tmp establishment
	 */
	public long getTmp_establishment_id() {
		return tmp_establishment_id;
	}
	
	/**
	 * @param tmp_establishment_id ID of tmp establishment
	 */
	public void setTmp_establishment_id(long tmp_establishment_id) {
		this.tmp_establishment_id = tmp_establishment_id;
	}
	
}
