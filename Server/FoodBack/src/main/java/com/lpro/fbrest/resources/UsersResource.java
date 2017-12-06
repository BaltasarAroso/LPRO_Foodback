package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.core.User;
import com.lpro.fbrest.db.UserDAO;

import io.dropwizard.auth.Auth;

@Path("/users")
public class UsersResource {

	private UserDAO userdao;
	
	public UsersResource(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newUser(@NotNull @Valid User user) {
		try {
			userdao.insertUser(user.getUsername(),
					user.getPassword(),
					user.getName(),
					user.getEmail(),
					user.getAddress(),
					user.getBirth(),
					user.getPremium());
		} catch (Exception e) {
			return Response.serverError().entity("User already exists").build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username) {
		User user = userdao.getUser(username);
		if(user == null) {
			throw new WebApplicationException(404);
		}
		user.setPassword(null);
		return user;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(@Auth User user) {
		return userdao.getAllUsers();
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/secured_hello")
	public String getGreeting(@Auth User user) {
		return "Hello " + user.getName() + "!";
	}

}
