package com.lpro.fbrest.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.auth.Client;

import io.dropwizard.auth.Auth;

@Path("/credentials")
public class CredentialsResource {

	/**
	 * @param client Client that sent credentials
	 * @return OK if client can access resource
	 */
	@GET
	@Path("/user")
	@RolesAllowed("USER")
	public Response verifyUserCredentials(@Auth Client client) {
		return Response.ok().build();
	}
	
	/**
	 * @param client Client that sent credentials
	 * @return OK if client can access resource
	 */
	@GET
	@Path("/establishment")
	@RolesAllowed("ESTABLISHMENT")
	public Response verifyEstablishmentCredentials(@Auth Client client) {
		return Response.ok().build();
	}
	
}
