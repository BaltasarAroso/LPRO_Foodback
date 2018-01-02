package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.db.ClientDAO;
import com.lpro.fbrest.db.UserDAO;

public abstract class UserService {
	
	private static final String SUCCESS = "Success...";
	
	@CreateSqlObject
	abstract ClientDAO clientdao();
	
	@CreateSqlObject
	abstract UserDAO userdao();
	
	/**
	 * @param user User to be created
	 * @return String SUCCESS if successful
	 */
	public String newUser(User user) {
		long user_id;
		Client prev = clientdao().getClient(user.getUsername());
		if(prev != null) throw new WebApplicationException(409);
		try {
			user_id = userdao().insertUser(user.getName(), 
										user.getEmail(), 
										user.getAddress(), 
										user.getBirth(), 
										user.getPremium(),
										user.getZone(),
										user.getCity());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		try {
			clientdao().insertUserClient(user.getUsername(), user.getPassword(), user_id);
		} catch(Exception e) {
			try {
				userdao().deleteUser(user_id);
			} catch(Exception err) {
				err.printStackTrace();
				throw new WebApplicationException(500);
			}
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		return SUCCESS;
	}
	
	/**
	 * @return list with all users
	 */
	public List<User> getAllUsers() {
		return userdao().getAllUsers();
	}
	
	/**
	 * @param username Username to be searched
	 * @return User if it exists
	 */
	public User getUserByUsername(String username) {
		User user = userdao().getUserByUsername(username);
		if(user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

	/**
	 * @param users_id Id of user to upgrade
	 */
	public void upgradePremium(long users_id) {
		try {
			userdao().upgradePremium(users_id);		
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	

}
