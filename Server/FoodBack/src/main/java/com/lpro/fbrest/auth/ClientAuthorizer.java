package com.lpro.fbrest.auth;

import io.dropwizard.auth.Authorizer;

public class ClientAuthorizer implements Authorizer<Client> {

	@Override
	public boolean authorize(Client client, String role) {
		return client.getRole().equals(role);
	}

}
