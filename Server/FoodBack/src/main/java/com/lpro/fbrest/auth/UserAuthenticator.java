package com.lpro.fbrest.auth;

import java.util.Optional;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.db.UserDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class UserAuthenticator implements Authenticator<BasicCredentials, User>{
	
	private UserDAO userdao;
	
	public UserAuthenticator(UserDAO userdao) {
		this.userdao = userdao;
	}

	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		User user = userdao.getUserByNameAndPassword(credentials.getUsername(), credentials.getPassword());
		if (user == null) return Optional.empty();
		return Optional.of(user);
	}

}
