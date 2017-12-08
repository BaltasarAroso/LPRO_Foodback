package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.db.ClientDAO;
import com.lpro.fbrest.db.UserDAO;

public abstract class UserService {
	
	@CreateSqlObject
	abstract ClientDAO clientdao();
	
	@CreateSqlObject
	abstract UserDAO userdao();
	
	public void newUser(User user) {
		long user_id;
		Client prev = clientdao().getClient(user.getUsername());
		if(prev != null) throw new WebApplicationException(Response.serverError()
													.entity("Username already taken!")
													.build());
		try {
			user_id = userdao().insertUser(user.getName(), user.getEmail(), user.getAddress(), user.getBirth(), user.getPremium());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		try {
			clientdao().insertUserClient(user.getUsername(), user.getPassword(), user_id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	public List<User> getAllUsers() {
		return userdao().getAllUsers();
	}
	
	public User getUserByUsername(String username) {
		User user = userdao().getUserByUsername(username);
		if(user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}
	

}
