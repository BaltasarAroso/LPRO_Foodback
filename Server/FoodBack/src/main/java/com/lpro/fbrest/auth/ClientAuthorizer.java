package com.lpro.fbrest.auth;

import io.dropwizard.auth.Authorizer;

public class ClientAuthorizer implements Authorizer<Client> {

	/* (non-Javadoc)
	 * @see io.dropwizard.auth.Authorizer#authorize(java.security.Principal, java.lang.String)
	 */
	@Override
	public boolean authorize(Client client, String role) {
		return client.getRole().equals(role);
	}

}
