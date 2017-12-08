package com.lpro.fbrest.resources;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.service.UserService;

import io.dropwizard.auth.Auth;

/**
 * @author Daniel
 *
 */
@Path("/users")
public class UsersResource {

	/**
	 * DAO for User Class
	 */
	private UserService userService;
	
	/**
	 * @param userdao DAO for User Class
	 */
	public UsersResource(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * @param user It's the new user that must be inserted in the database
	 * @return Returns a Response Object that tells the client if the user was inserted or not
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newUser(@NotNull @Valid User user) {
		userService.newUser(user);
		return Response.ok().build();
	}
	
	/**
	 * @param user Is the User that made the request
	 * @return	Returns a List with all users
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(@Auth User user) {
		return userService.getAllUsers();
	}
	
	/**
	 * @param username The name of the User to be found
	 * @return Returns a User if it was found
	 * 
	 * If there is no user with this username a WebApplicationException is thrown with the http code 404 (not found)
	 */
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username) {
		return userService.getUserByUsername(username);
	}

}
