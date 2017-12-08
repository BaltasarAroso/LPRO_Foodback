package com.lpro.fbrest.auth;

import java.util.Optional;

import com.lpro.fbrest.db.ClientDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class ClientAuthenticator implements Authenticator<BasicCredentials, Client>{
	
	private ClientDAO clientdao;
	
	public ClientAuthenticator(ClientDAO clientdao) {
		this.clientdao = clientdao;
	}

	@Override
	public Optional<Client> authenticate(BasicCredentials credentials) throws AuthenticationException {
		Client client = clientdao.getClientByNameAndPassword(credentials.getUsername(), credentials.getPassword());
		if (client == null) return Optional.empty();
		return Optional.of(client);
	}

}
