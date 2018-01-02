package com.lpro.fbrest.auth;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.dropwizard.auth.UnauthorizedHandler;

public class ClientUnauthorizedHandler implements UnauthorizedHandler {

	@Override
	public Response buildResponse(String arg0, String arg1) {
		return Response.status(Status.UNAUTHORIZED)
				.entity("{ \"code\": 401, \"message\": \"Credentials are required to access this resource.\" }")
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
