package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.UserService;

import io.dropwizard.auth.Auth;

/**
 * Resource to manage users
 */
@Path("/users")
public class UsersResource {

	/**
	 * Service for User Class
	 */
	private UserService userService;
	
	/**
	 * @param userService Service for User Class
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
	public User getUserByUsername(@PathParam("username") String username) {
		return userService.getUserByUsername(username);
	}
	
	/**
	 * @param id ID of the User to be found
	 * @return Returns a User if it was found
	 * 
	 * If there is no user with this ID a WebApplicationException is thrown with the http code 404 (not found)
	 */
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") long id) {
		return userService.getUserById(id);
	}
	
	/**
	 * @param client Client that authenticated
	 * @return User
	 */
	@GET
	@Path("/me")
	@RolesAllowed("USER")
	@Produces(MediaType.APPLICATION_JSON)
	public User getSignedUser(@Auth Client client) {
		return userService.getUserByUsername(client.getUsername());
	}
	
	/**
	 * @param client Client that authenticated
	 * @return Response with OK http status
	 */
	@PUT
	@Path("/upgrade")
	@RolesAllowed("USER")
	public Response upgradePremium(@Auth Client client) {
		userService.upgradePremium(client.getUsers_id());
		return Response.ok().build();
	}
	
	/**
	 * @param client Client that authenticated
	 * @param user New user object
	 * @return Response with ok http status
	 */
	@PUT
	@RolesAllowed({"USER","ADMIN"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editUser(@Auth Client client, User user) {
		if(client.getUsers_id() > 0 && client.getUsers_id() != user.getId()) throw new WebApplicationException(403);
		userService.editUser(user);
		return Response.ok().build();
	}

}
