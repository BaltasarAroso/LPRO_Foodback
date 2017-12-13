package com.lpro.fbrest.auth;

import java.util.Optional;

import com.lpro.fbrest.db.ClientDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class ClientAuthenticator implements Authenticator<BasicCredentials, Client>{
	
	/**
	 *  DAO to access Client persistent data
	 */
	private ClientDAO clientdao;
	
	/**
	 * @param clientdao DAO for the client
	 */
	public ClientAuthenticator(ClientDAO clientdao) {
		this.clientdao = clientdao;
	}

	/* (non-Javadoc)
	 * @see io.dropwizard.auth.Authenticator#authenticate(java.lang.Object)
	 */
	@Override
	public Optional<Client> authenticate(BasicCredentials credentials) throws AuthenticationException {
		Client client = clientdao.getClientByNameAndPassword(credentials.getUsername(), credentials.getPassword());
		if (client == null) return Optional.empty();
		return Optional.of(client);
	}

}
