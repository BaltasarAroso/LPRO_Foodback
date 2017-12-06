package com.lpro.fbrest.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	@Produces(MediaType.TEXT_PLAIN)
	public String newUser(@NotNull @Valid User user) {
		
		
		userdao.insertUser(user.getUsername(),
							user.getPassword(),
							user.getName(),
							user.getEmail(),
							user.getAddress(),
							user.getBirth(),
							user.getPremium());
		
		return "User inserted!!";
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username) {
		System.out.println(username);
		return userdao.getUser(username);
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/secured_hello")
	public String getGreeting(@Auth User user) {
		return "Hello " + user.getName() + "!";
	}
	
}
