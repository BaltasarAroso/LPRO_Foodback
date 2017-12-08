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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.User;
import com.lpro.fbrest.db.UserDAO;

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
	private UserDAO userdao;
	
	/**
	 * @param userdao DAO for User Class
	 */
	public UsersResource(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	/**
	 * @param user It's the new user that must be inserted in the database
	 * @return Returns a Response Object that tells the client if the user was inserted or not
	 */
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
			return Response.serverError().entity("Error inserting user, it may already exist!").build();
		}
		return Response.ok().build();
	}
	
	/**
	 * @param user Is the User that made the request
	 * @return	Returns a List with all users
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(@Auth User user) {
		return userdao.getAllUsers();
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
		User user = userdao.getUser(username);
		if(user == null) {
			throw new WebApplicationException(404);
		}
		user.setPassword(null);
		return user;
	}

}
