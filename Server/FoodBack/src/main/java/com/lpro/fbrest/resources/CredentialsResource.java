package com.lpro.fbrest.resources;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.db.ClientDAO;

import io.dropwizard.auth.Auth;

@Path("/credentials")
public class CredentialsResource {
	
	private ClientDAO clientdao;
	
	public CredentialsResource(ClientDAO clientdao) {
		this.clientdao = clientdao;
	}

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
	
	/**
	 * @param client Client that sent credentials
	 * @return OK if client can access resource
	 */
	@GET
	@Path("/admin")
	@RolesAllowed("ADMIN")
	public Response verifyAdminCredentials(@Auth Client client) {
		return Response.ok().build();
	}
	
	/**
	 * @return Response with OK status
	 */
	@GET
	public Response verifyConnectivity() {
		return Response.ok().build();
	}
	
	/**
	 * @param client Client that authenticated
	 * @param username New username
	 * @param password New password
	 * @return response to show that request was well handled
	 */
	@PUT 
	@PermitAll
	public Response changePassword(@Auth Client client, @FormParam("username") String username, @FormParam("password") String password) {
		try {
			if(password != null) clientdao.updateClientPassword(password, client.getUsername());
			if(username != null) clientdao.updateClientUsername(username, client.getUsername());
		} catch( Exception e ) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		return Response.ok().build();
	}
	
}
