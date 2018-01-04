package com.lpro.fbrest.auth;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.dropwizard.auth.UnauthorizedHandler;

/**
 * @author Daniel
 *
 */
public class ClientUnauthorizedHandler implements UnauthorizedHandler {

	/* (non-Javadoc)
	 * @see io.dropwizard.auth.UnauthorizedHandler#buildResponse(java.lang.String, java.lang.String)
	 */
	@Override
	public Response buildResponse(String arg0, String arg1) {
		return Response.status(Status.UNAUTHORIZED)
				.entity("{ \"code\": 401, \"message\": \"Invalid username/password combination.\" }")
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
